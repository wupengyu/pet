package com.yf.pet.service.impl;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.yf.pet.dao.checkcode.AccessControllerDao;
import com.yf.pet.dao.checkcode.CheckCodeDao;
import com.yf.pet.dao.checkcode.CodeRequestLogDao;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.checkcode.AccessController;
import com.yf.pet.entity.checkcode.CheckCode;
import com.yf.pet.entity.checkcode.CheckCodeCriteria;
import com.yf.pet.entity.checkcode.CodeRequestLog;
import com.yf.pet.entity.enums.CheckCodeActionTypeEnum;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.AccountService;
import com.yf.pet.service.CheckCodeService;
import com.yf.pet.service.CorosEmailService;
import com.yf.pet.utils.*;
import com.yf.pet.utils.aes.AESUtil;
import com.yf.pet.utils.guava.YFGuavaExecutors;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 概述: 验证码判断接口实现层<br>
 * 背景: 同一个验证码错误3次就不再进行判断<br>
 * Created by Infi on 17/3/8.
 */
@Service("checkCodeService")
public class CheckCodeServiceImpl implements CheckCodeService {
    private static final Logger log = LoggerFactory.getLogger(CheckCodeServiceImpl.class);
    /**
     * 邮箱激活的页面地址
     */
    private static final String COROS_EMAIL_ACTIVATE = YFResourceUtil.getValueByKey("app-coros.properties", "coros.email.activate");
    /**
     * 密码重置的页面地址
     */
    private static final String COROS_EMAIL_RESET_PASSWORD = YFResourceUtil.getValueByKey("app-coros.properties", "coros.email.reset.password");

    @Autowired
    private CheckCodeDao checkCodeDao;

    @Autowired
    private CodeRequestLogDao codeRequestLogDao;

    @Autowired
    private AccessControllerDao accessControllerDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CorosEmailService corosEmailService;

    @Override
    public void createCheckCode(CheckCodeCriteria checkCode) {
        // 1. 电话号码和邮箱地址同时为空,就抛出异常
        if (StringUtils.isBlank(checkCode.getMobile()) || checkCode.getActionType() == null
                || StringUtils.isBlank(checkCode.getIpAddress()) || StringUtils.isBlank(checkCode.getAppKey())) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 2. 首先看电话号码不为空就发短信,邮箱地址不为空就发邮件
        String account = AESUtil.decryptAndXOR(checkCode.getMobile(), checkCode.getAppKey());

        // 4. 如果用户请求的是注册短信,那他的手机号必需是系统中不存在的,
        switch (checkCode.getActionType()) {
            case CheckCodeActionTypeEnum.REGISTER:
            case CheckCodeActionTypeEnum.MOBILE_BIND:
                // 获取注册或绑定帐号验证码,帐号必需不是注册帐号
                if (accountService.findMobileIsExist(account) > 0) {
                    throw new YFException(ReturnMessageEnum.USER_NAME_IS_EXIST);
                }
                break;
            case CheckCodeActionTypeEnum.RESET_PWD:
                // 重置密码,帐号必需是注册帐号,数据库中存在的帐号
                Integer count = accountService.findMobileIsExist(account);
                if (count == null || count == 0) {
                    throw new YFException(ReturnMessageEnum.USER_NAME_IS_NULL);
                }
                break;
        }
        // 5. 发送验证码,电话号码就发短信
        String verifyCode = createVerifyCode(account, checkCode.getActionType(), checkCode.getIpAddress(), null);
        sendSMS(account, verifyCode);
    }

    @Override
    public boolean verifyCheckCodeByAccountAES(CheckCodeCriteria checkCodeCriteria) {
        // 1. 解密帐号
        String account = AESUtil.decryptAndXOR(checkCodeCriteria.getAccount(), checkCodeCriteria.getAppKey());
        // 2. 验证码校验
        return verifyCheckCode(account, checkCodeCriteria.getCheckCode(), checkCodeCriteria.getActionType(), null);
    }

    @Override
    public boolean verifyCheckCodeByAccount(String account, String checkCode, Integer actionType, Long userId) {
        // 2. 验证码校验
        return verifyCheckCode(account, checkCode, actionType, userId);
    }

    /**
     * 验证码验证
     *
     * @param account    帐号信息,mobile或者email
     * @param checkCode  验证码
     * @param actionType 验证码类型,1:注册,2:密码重置,3:邮箱激活
     * @param userId     用户ID,激活邮箱地址用到
     * @return 验证码验证结果
     */
    private boolean verifyCheckCode(String account, String checkCode, Integer actionType, Long userId) {
        // 1. 检查参数是否正确
        if (StringUtils.isBlank(account) || actionType == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isBlank(checkCode)) {
            throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_NULL);
        }

        Date nowDate = new Date();
        // 2. 查询当天验证码校验次数,当天校验验证码的次数超过规定次数,目前是不能超过20次,就提示用户请求次数已经达到上限
        Integer accessCount = accessControllerDao.findAccessControllerCountByMobileToday(account, actionType);
        if (accessCount > ApplicationConstants.UP_ACCESS_COUNT) {
            throw new YFException(ReturnMessageEnum.UP_ACCESS_COUNT);
        }

        // 3. 校验验证码
        CheckCode lastCheckCode = checkCodeDao.findLastCheckCodeByAccount(account, actionType, userId);
        if (lastCheckCode == null || !BooleanUtils.toBoolean(lastCheckCode.getValid())) {
            throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_INVALID);
        }
        // 4. 如果验证码校验已经超过30分钟,就要用户重新获取验证码
        AccessController accessController = new AccessController();
        accessController.setCheckCode(checkCode);
        accessController.setCreateDate(nowDate);
        accessController.setAccount(account);
        accessController.setActionType(actionType);

        // 5. 如果是重置密码、邮箱激活的验证码,要48小时内有效,其他验证码都是30分钟内有效
        Integer overdueTime = ApplicationConstants.SMS_VERIFY_CODE_VALID;
        if (CheckCodeActionTypeEnum.RESET_PWD == actionType || CheckCodeActionTypeEnum.EMAIL_ACTIVATE == actionType) {
            overdueTime = ApplicationConstants.SMS_VERIFY_CODE_VALID_BY_RESET_PWD;
        }
        // 3. 密码重置连接的验证码和其他类型的验证码过期时间不同
        if (YFDateUtil.getSecondOf2Date(lastCheckCode.getCreateDate(), nowDate) > overdueTime) {
            // 邮件已过期错误不记录错误日志
            // accessControllerDao.addAccessController(accessController);
            throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_INVALID);
        }
        // 5. 判断验证码是否正确
        if (lastCheckCode.getCheckCode().equals(checkCode)) {
            // 6. 重置密码链接修改过密码以后,就把验证码状态修改为无效
            if (CheckCodeActionTypeEnum.RESET_PWD == actionType) {
                checkCodeDao.updateCheckCodeValid(lastCheckCode.getId(), 0);
            }
            return true;
        } else {
            // 6. 添加验证码信息,只有验证码输入错误才会记录在t_access_controller表中
            accessControllerDao.addAccessController(accessController);
            return false;
        }
    }


    @Override
    public void sendCheckCodeByEmailActivate(Long userId, String email, String ipAddress, Integer activateType) {
        final String account = email;
        // 1. 生成邮箱激活的验证码
        final String verifyCode = createVerifyCode(account, CheckCodeActionTypeEnum.EMAIL_ACTIVATE, ipAddress, userId);
        // 2. 发送邮箱以后把之前发送的激活邮件修改为无效状态,根据本次生成的验证码作为条件修改
        updateCheckCodeInValidBeforeSendMail(account, CheckCodeActionTypeEnum.EMAIL_ACTIVATE);
        // 3. 异步发送邮件
        final String activateHtmlStr = createEmailActivateHtml(userId, email, verifyCode, activateType);
        ListenableFuture<String> stringTask = YFGuavaExecutors.getDefaultCompletedExecutorService()
                .submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        log.info("开始发送邮件:" + account);
                        corosEmailService.sendMail(account, "COROS:Please verify your email address",
                                activateHtmlStr);
                        return account;
                    }
                });

        Futures.addCallback(stringTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                log.info("邮件发送完成,email:" + result);
                // 可以写邮件发送成功以后的逻辑
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("邮件发送失败,失败信息:" + t.getMessage());
                // 可以写邮件发送失败以后的逻辑
            }
        });
    }

    @Override
    public void sendCheckCodeOrMailByRestPwd(String email, String ipAddress, String appKey) {
        // 1. 确定密码重置方式,电话号码或者邮箱地址。帐号解密
        final String account = AESUtil.decryptAndXOR(email, appKey);

        // 2. 检查用户是否存在
        Integer isExistEmailCount = accountService.findEmailIsExist(account);
        if (isExistEmailCount == null || isExistEmailCount == 0) {
            throw new YFException(ReturnMessageEnum.EMAIL_IS_NOT_REGISTERED);
        }

        // 3. 发送验证码,电话号码就发短信,邮箱地址就发邮件
        final String verifyCode = createVerifyCode(account, CheckCodeActionTypeEnum.RESET_PWD, ipAddress, null);
        // 4. 把之前发送的邮件设置为无效
        updateCheckCodeInValidBeforeSendMail(account, CheckCodeActionTypeEnum.RESET_PWD);

        // 5. 密码重置邮件,异步发送激活邮件
        ListenableFuture<String> stringTask = YFGuavaExecutors.getDefaultCompletedExecutorService()
                .submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        corosEmailService.sendMail(account, "Reset your COROS password", createResetPwdHtml(account, verifyCode));

                        return null;
                    }
                });

        Futures.addCallback(stringTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(String email) {
                log.info("邮件发送完成,email:" + email);
                // 可以写邮件发送成功以后的逻辑
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("邮件发送失败,失败信息:" + t.getMessage());
                // 可以写邮件发送失败以后的逻辑
            }
        });
    }

    /**
     * 本次发送激活邮件以后,把之前发送的邮件设置为失效
     *
     * @param account             帐号信息
     * @param checkCodeActionType 验证码类型,邮件激活类型或者密码重置
     */
    private void updateCheckCodeInValidBeforeSendMail(String account, Integer checkCodeActionType) {
        // 1. 先查询最后一份邮件的验证码ID
        CheckCode lastCheckCode = checkCodeDao.findLastCheckCodeByAccount(account, checkCodeActionType, null);
        if (lastCheckCode == null) {
            return;
        }
        // 2. 修改其他验证码无效,
        checkCodeDao.updateCheckCodeInValidBeforeSendMail(account, checkCodeActionType, lastCheckCode.getId());
    }

    @Override
    public CheckCode findLastCheckCodeByAccount(String account, Integer type, Long userId) {
        return checkCodeDao.findLastCheckCodeByAccount(account, type, userId);
    }

    @Override
    public void updateCheckCodeValidByAccount(String account, Integer actionType, Integer valid, Long userId) {
        checkCodeDao.updateCheckCodeValidByAccount(account, actionType, valid, userId);
    }

    /**
     * 创建一个验证码
     *
     * @param account    帐号
     * @param actionType 验证码类型,1:注册、2:重置密码、3:邮箱激活、4:手机号码绑定
     * @param ipAddress  用户请求的IP地址
     * @param userId     用户ID(发送激活邮件时用到)
     * @return 新的验证码
     */
    private String createVerifyCode(String account, Integer actionType, String ipAddress, Long userId) {
        // 1. 查询验证码获取次数,当天获取验证码的次数超过10就抛出异常
        Integer getCheckCodeNum = checkCodeDao.findCheckCodeCountToday(account, actionType);
        if (getCheckCodeNum >= ApplicationConstants.SMS_VERIFY_CODE_COUNT_OF_DAY) {
            throw new YFException(ReturnMessageEnum.REQUEST_MORE_BY_MOBILE);
        }
        // 2. 同一个IP地址一天只能发送10次短信验证码请求
        Long remoteIPNum = IPUtils.ipToLong(ipAddress);
        Integer getCheckCodeNumByIp = codeRequestLogDao.findCountByIPNumAndDay(remoteIPNum, actionType);
        if (getCheckCodeNumByIp >= ApplicationConstants.UP_ACCESS_COUNT) {
            throw new YFException(ReturnMessageEnum.UP_ACCESS_COUNT);
        }
        // 3. 如果某个用户某个类型的验证码错误次数达到10次以上,就不能再获取新的验证码,TODO 这里是新加的逻辑
        Integer checkFailureNum = accessControllerDao.findAccessControllerCountByMobileToday(account, actionType);
        if (checkFailureNum >= ApplicationConstants.UP_ACCESS_COUNT) {
            throw new YFException(ReturnMessageEnum.UP_ACCESS_COUNT);
        }

        // 3. 查询用户最后一次获取到的验证码
        CheckCode lastCheckCode = checkCodeDao.findLastCheckCodeByAccount(account, actionType, userId);
        // 4. 如果最后一次验证码没有超过半小时,就发同样的验证码到用户手机
        Date nowDate = new Date();
        String verifyCode = CodeGenerator.getIntCodeString(ApplicationConstants.SMS_VERIFY_CODE_LEN);
        // TODO 因为产生验证码以后,后面的验证码会失效,所以每次产生的验证码要不一样
//        if (lastCheckCode != null && YFDateUtil.getSecondOf2Date(lastCheckCode.getCreateDate(), nowDate)
//                <= ApplicationConstants.SMS_VERIFY_CODE_VALID) {
//            verifyCode = lastCheckCode.getCheckCode();
//        } else {
//            // 5. 如果时间已经超过半小时,就发送新的验证码到用户手机
//            verifyCode = CodeGenerator.getIntCodeString(ApplicationConstants.SMS_VERIFY_CODE_LEN);
//        }
        // 11. 保存验证码数据
        checkCodeDao.addCheckCode(account, verifyCode, nowDate, actionType, userId);

        // 12. 保存验证码发送记录
        CodeRequestLog codeRequestLog = new CodeRequestLog();
        codeRequestLog.setUuid(UUIDGenerateUtil.generate());
        codeRequestLog.setAccount(account);
        codeRequestLog.setRemoteIPAddress(ipAddress);
        codeRequestLog.setRemoteIPNum(remoteIPNum);
        codeRequestLog.setCreateDate(nowDate);
        codeRequestLog.setTag(actionType);
        codeRequestLogDao.addCodeRequestLog(codeRequestLog);
        return verifyCode;
    }

    /**
     * 发送短信验证码
     *
     * @param account    电话号码
     * @param verifyCode 验证码
     */
    private void sendSMS(String account, String verifyCode) {
        // 1. 发送验证码,电话号码就发短信,邮箱地址就发邮件
        StringBuilder smsString = new StringBuilder(32);
        smsString.append(ApplicationConstants.MESSAGE_SUFFIX);
        smsString.append("您的验证码是：");
        smsString.append(verifyCode);
        smsString.append(",验证码的有效期是");
        smsString.append(ApplicationConstants.SMS_VERIFY_CODE_VALID / 60);
        smsString.append("分钟。");
        SMSProxyUtil.sendVerifiCode(account, smsString.toString());
    }

    /**
     * 创建邮箱激活邮件
     * @param userId    用户ID
     * @param email     用户原邮箱地址
     * @param validCode 随机验证码  @return 邮件html内容
     * @param activateType 邮箱激活类型。1: 邮箱注册激活,2: 邮箱绑定激活
     */
    private String createEmailActivateHtml(Long userId, String email, String validCode, Integer activateType) {
        Date nowDate = new Date();
        // 1. 设置邮件发送时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        // 3. 设置邮件内容
        StringBuffer htmlMsg = new StringBuffer();
        htmlMsg.append("<html><head><meta charset=\"UTF-8\"><title>COROS</title></head><body>");
        htmlMsg.append(
                "<div style=\"padding: 0 5% 18px; font: 300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif; color: #333; position: relative; font-size: 14px; height: auto; padding: 15px 15px 10px 15px; z-index: 1; zoom: 1; line-height: 1.7;\">");
        htmlMsg.append("<p>Hello ");
        // 4.  显示用户邮箱前缀前的名字
        String nickname = StringUtils.substringBefore(email, "@");
        htmlMsg.append(nickname);
        htmlMsg.append("!</p>");
        htmlMsg.append("<p>Thanks for joining Coros! We really appreciate it. Please click the button below to verify your account: </p>");
        // 5. 激活连接
        htmlMsg.append("<p>");
        htmlMsg.append("<a href=\"");
        htmlMsg.append(COROS_EMAIL_ACTIVATE);
        htmlMsg.append("?userId=");
        htmlMsg.append(userId);

        htmlMsg.append("&email=");
        htmlMsg.append(email);

        htmlMsg.append("&checkCode=");
        htmlMsg.append(validCode);

        htmlMsg.append("&activateType=");
        htmlMsg.append(activateType);

        htmlMsg.append("\">Verify account</a>");
        htmlMsg.append("</p>");
        htmlMsg.append("<p>This link will only be valid for 48 hours. </p>");
        // 5. 获取密码重置页面地址
        htmlMsg.append("<p>Best wishes！</p>");
        htmlMsg.append("<p>COROS Crew</p>");
        htmlMsg.append("</div></body></html>");
        return htmlMsg.toString();
    }

    /**
     * 创建密码重置邮件内容
     *
     * @param sendTo    收件人
     * @param validCode 随机验证码
     * @return 邮件html内容
     */
    private String createResetPwdHtml(String sendTo, String validCode) {
        Date nowDate = new Date();
        // 1. 设置邮件发送时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        // 3. 设置邮件内容
        StringBuffer htmlMsg = new StringBuffer();
        htmlMsg.append("<html><head><meta charset=\"UTF-8\"><title>COROS</title></head><body>");
        htmlMsg.append(
                "<div style=\"padding: 0 5% 18px; font: 300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif; color: #333; position: relative; font-size: 14px; height: auto; padding: 15px 15px 10px 15px; z-index: 1; zoom: 1; line-height: 1.7;\">");
        htmlMsg.append("<p>Hello ");
        // 4.  显示用户邮箱前缀前的名字
        String nickname = StringUtils.substringBefore(sendTo, "@");
        htmlMsg.append(nickname);
        htmlMsg.append("！</p>");
        htmlMsg.append("<p>We received a request to reset your password. Click the link below to choose a new one: </p>");
        // 5. 链接地址
        htmlMsg.append("<p>");
        htmlMsg.append("<a href=\"");
        htmlMsg.append(COROS_EMAIL_RESET_PASSWORD);
        htmlMsg.append("?email=");
        htmlMsg.append(sendTo);
        htmlMsg.append("&checkCode=");
        htmlMsg.append(validCode);
        htmlMsg.append("\">Reset password</a>");
        htmlMsg.append("</p>");
        // 5. 获取密码重置页面地址
        htmlMsg.append("<p>This link will only be valid for 48 hours.Your password won’t be changed if you ignore this email. </p>");
        htmlMsg.append("<p>Best wishes! </p>");
        htmlMsg.append("<p>COROS Crew</p>");
        htmlMsg.append("</div></body></html>");
        return htmlMsg.toString();
    }


}
