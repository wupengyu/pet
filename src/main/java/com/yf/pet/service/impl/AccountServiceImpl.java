package com.yf.pet.service.impl;


import com.yf.pet.dao.user.AccountDao;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.enums.*;
import com.yf.pet.entity.user.Account;
import com.yf.pet.entity.user.UserInfo;
import com.yf.pet.entity.user.UserSimpleInfo;
import com.yf.pet.entity.user.vo.UserInfoVO;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.*;
import com.yf.pet.utils.*;
import com.yf.pet.utils.aes.AESUtil;
import com.yf.pet.utils.primary.YFPrimaryKeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户目标值记录接口实现层
 * Created by Infi on 17/3/22.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    /**
     * 邮箱激活的页面地址
     */
    private static final String COROS_EMAIL_ACTIVATE = YFResourceUtil.getValueByKey("app-coros.properties", "coros.email.activate");
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DailyTargetService dailyTargetService;

    @Autowired
    private CheckCodeService checkCodeService;

    @Autowired
    private AccountTemporaryService accountTemporaryService;

    @Override
    public Long addAccount(UserInfoVO userInfoVO) throws IOException {
        // 1. 检查帐号信息,和用户信息
        checkAccountInfoIsComplete(userInfoVO);

        // 2. 帐号解密
        final String account = AESUtil.decryptAndXOR(userInfoVO.getAccount(), userInfoVO.getAppKey());


        // 2. 转换帐号信息
        Account accountObj = new Account();
        switch (userInfoVO.getAccountType()) {
            case AccountTypeEnum.MOBILE:
                accountObj.setMobile(account);
                accountObj.setPwd(AESUtil.decryptAndXOR(userInfoVO.getPwd(), userInfoVO.getAppKey()));
                break;
            case AccountTypeEnum.EMAIL:
                accountObj.setEmail(account);
                accountObj.setPwd(AESUtil.decryptAndXOR(userInfoVO.getPwd(), userInfoVO.getAppKey()));
                break;
            case AccountTypeEnum.FACEBOOK:
                accountObj.setFacebookId(account);
                accountObj.setFacebookName(userInfoVO.getOpenNickname());
                break;
            case AccountTypeEnum.WEIXIN:
                accountObj.setWeixinId(account);
                accountObj.setWeixinName(userInfoVO.getOpenNickname());
                break;
            default:
                break;
        }

        accountObj.setClientType(userInfoVO.getClientType());
        accountObj.setLoginType(userInfoVO.getAccountType());
        accountObj.setIpAddress(userInfoVO.getIpAddress());
        Date nowDate = new Date();
        accountObj.setCreateDate(nowDate);
        accountObj.setRegisterType(userInfoVO.getAccountType());

        // 6. 检查帐号是否已经被注册
        Integer findAccountCount = accountDao.findAccountIsExist(accountObj);
        if (findAccountCount > 0) {
            throw new YFException(ReturnMessageEnum.USER_NAME_IS_EXIST);
        }
        // 7. 保存头像文件
        // 10. 获得主键
        Long userId = YFPrimaryKeyUtils.getId(ServiceModeType.USER);
        if (userInfoVO.getHeadPicFile() != null) {
            //获取存储路径,根据日期生成文件夹名称
            Integer dateFileDir = YFDateUtil.getTodayNumber();
            String uploadPath = YFEnumFileKey.HEAD_PIC.getLocalPath(String.valueOf(dateFileDir));
            //生成文件名
            String tmpfileName = CodeGenerator.getFileNameCodeString();
            // 扩展名
            String fileExt = StringUtils.substringAfterLast(userInfoVO.getHeadPicFile().getOriginalFilename(), ".");
            // 文件名加上扩展名
            if (StringUtils.isNotBlank(fileExt)) {
                tmpfileName = tmpfileName + "." + fileExt;
            }

            //将标签byte数据保存到本地文件
            File fileData = FileProecssUtil.saveLocalFile(userInfoVO.getHeadPicFile().getBytes(), tmpfileName, uploadPath);
            //存储路径是帐号加斜杠加文件名: 13710637126/1490681576S8WNUA79L3P1SLQR7S20
            userInfoVO.setHeadPic(dateFileDir + "/" + tmpfileName);
        }

        // 8. 得到userinfo的数据
        final UserInfo userInfo = new UserInfo();
        userInfo.setNickname(userInfoVO.getNickname());
        userInfo.setHeadPic(userInfoVO.getHeadPic());
        userInfo.setSex(userInfoVO.getSex());
        userInfo.setStature(userInfoVO.getStature());
        userInfo.setWeight(userInfoVO.getWeight());
        userInfo.setBirthday(userInfoVO.getBirthday());
        userInfo.setLongitude(userInfoVO.getLongitude());
        userInfo.setLatitude(userInfoVO.getLatitude());
        userInfo.setRegisterTimezone(userInfoVO.getRegisterTimezone());
        userInfo.setRegisterTimestamp(userInfoVO.getRegisterTimestamp());
        userInfo.setTimezone(userInfoVO.getRegisterTimezone());
        userInfo.setCountryCode(userInfoVO.getCountryCode());
        userInfo.setUnit(userInfoVO.getUnit());
        userInfo.setCreateDate(nowDate);

        // 9. 保存帐号信息
        accountObj.setAccessToken(CodeGenerator.getAccessToken());
        Date validityDate = DateUtils.addDays(DateUtils.truncate(nowDate, Calendar.DAY_OF_MONTH),
                ApplicationConstants.TOKEN_VALID_DAY_COUNT);
        accountObj.setValidityDate(validityDate);

        accountObj.setUserId(userId);
        accountObj.setActivateStatus(ActivateStatusEnum.INACTIVE);
        accountDao.addAccount(accountObj);
        userInfo.setUserId(userId);
        // 目前默认所有用户的周首日都是周日
        userInfo.setFirstDayOfWeek(Calendar.SUNDAY);
        userInfoService.addUserInfo(userInfo);
        // 10. 保存目标值
        if ((userInfoVO.getTargetCalorie() != null && userInfoVO.getTargetCalorie() > 0)
                || (userInfoVO.getTargetMotionTime() != null && userInfoVO.getTargetMotionTime() > 0)) {
            dailyTargetService.addDailyTarget(userId, userInfo.getTimezone(),
                    userInfoVO.getTargetMotionTime(), userInfoVO.getTargetCalorie());
        }
        // 2. 邮箱注册,异步发送激活邮件
        if (AccountTypeEnum.EMAIL == userInfoVO.getAccountType()) {
            // 异步发送邮件
            Long userIdFinal = userId;
            String accountFinal = account;
            String ipAddressFinal = userInfoVO.getIpAddress();
            checkCodeService.sendCheckCodeByEmailActivate(userIdFinal, accountFinal, ipAddressFinal,
                    ActivateTypeEnum.REGISTER_ACTIVATE);
        }
        // 10. 查询用户信息
        return userId;
    }

    @Override
    public void addAccount(Account account) {
        accountDao.addAccount(account);
    }

    /**
     * 判断帐号信息是否完整
     *
     * @param userInfoVO APP上传的用户信息
     */
    private void checkAccountInfoIsComplete(UserInfoVO userInfoVO) {
        // 1. mobile、email、facebookId、weixin不能同时为空
        if (StringUtils.isBlank(userInfoVO.getAccount()) && userInfoVO.getAccountType() == null) {
            throw new YFException(ReturnMessageEnum.USER_NAME_IS_NULL);
        }
        // 2. mobile和email注册的时候,密码不能为空
        if ((AccountTypeEnum.MOBILE == userInfoVO.getAccountType()
                || AccountTypeEnum.EMAIL == userInfoVO.getAccountType())
                && StringUtils.isBlank(userInfoVO.getPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_IS_NULL);
        }
        // 3. 昵称、性别、身高、体重、生日不能为空
        if (StringUtils.isBlank(userInfoVO.getNickname())) {
            throw new YFException(ReturnMessageEnum.NICKNAME_IS_NULL);
        }
        if (userInfoVO.getSex() == null) {
            throw new YFException(ReturnMessageEnum.SEX_IS_NULL);
        }
        if (userInfoVO.getStature() == null) {
            throw new YFException(ReturnMessageEnum.STATURE_IS_NULL);
        }
        if (userInfoVO.getWeight() == null) {
            throw new YFException(ReturnMessageEnum.WEIGHT_IS_NULL);
        }
        if (userInfoVO.getBirthday() == null) {
            throw new YFException(ReturnMessageEnum.BIRTHDAY_IS_NULL);
        }
        // 4. 时区信息不能为空
        if (userInfoVO.getRegisterTimezone() == null || userInfoVO.getRegisterTimestamp() == null
                || userInfoVO.getRegisterTimestamp() == 0) {
            throw new YFException(ReturnMessageEnum.TIMEZONE_IS_NULL);
        }
        // 5. appKey不能为空
        if (StringUtils.isBlank(userInfoVO.getAppKey())) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void updateAccountToken(String accessToken, Date validityDate, Long userId, Integer loginType) {
        accountDao.updateAccountToken(accessToken, validityDate, userId, loginType);
    }

    @Override
    public Integer findMobileIsExist(String mobile) {
        return accountDao.findMobileIsExist(mobile);
    }

    @Override
    public Integer findEmailIsExist(String email) {
        return accountDao.findEmailIsExist(email);
    }

    @Override
    public Account findAccountByMobileOrEmailAndPwd(String mobile, String email) {
        return accountDao.findAccountByMobileOrEmailAndPwd(mobile, email);
    }

    @Override
    public Account findAccountSummary(String account, Integer accountType) {
        return accountDao.findAccountSummary(account, accountType);
    }

    @Override
    public Account findAccountByOpenId(String facebookId, String weixinId) {
        return accountDao.findAccountByOpenId(facebookId, weixinId);
    }

    @Override
    public Account findAccessTokenIsValid(String accessToken) {
        return accountDao.findAccessTokenIsValid(accessToken);
    }

    @Override
    public Account findAccountByAccessToken(String accessToken) {
        return accountDao.findAccountByAccessToken(accessToken);
    }

    @Override
    public Account findAccountAndPwdByAccessToken(String accessToken) {
        return accountDao.findAccountAndPwdByAccessToken(accessToken);
    }

    @Override
    public Account findAccountByUserId(Long userId) {
        return accountDao.findAccountByUserId(userId);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountDao.findAccountByEmail(email);
    }

    @Override
    public void updateAccessTokenByLogout(String accessToken) {
        accountDao.updateAccessTokenByLogout(accessToken);
    }

    @Override
    public void bindAccount(Account accountInDB, UserInfoVO userInfoVO) {
        // 1. 解密帐号
        String account = AESUtil.decryptAndXOR(userInfoVO.getAccount(), userInfoVO.getAppKey());


        // 2. 判断用户提交的电话号码、facebookId、weixinId是否已经被注册。邮箱地址走另外逻辑
        if (userInfoVO.getAccountType() != AccountTypeEnum.EMAIL) {
            Integer accountCount = accountDao.findAccountIsExistByAccountType(account, userInfoVO.getAccountType());
            if (accountCount != null && accountCount > 0) {
                throw new YFException(ReturnMessageEnum.ACCOUNT_IS_BIND);
            }
        }

        // 3. 按帐号类型处理绑定逻辑
        switch (userInfoVO.getAccountType()) {
            case AccountTypeEnum.MOBILE:
                // 查看帐号是否已经绑定电话号码
                if (StringUtils.isNotBlank(accountInDB.getMobile())) {
                    throw new YFException(ReturnMessageEnum.ACCOUNT_IS_BIND);
                }
                // 判断验证码是否正确
                if (!checkCodeService.verifyCheckCodeByAccount(account, userInfoVO.getCheckCode(),
                        CheckCodeActionTypeEnum.MOBILE_BIND, null)) {
                    throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_INVALID);
                }
                // 4. 获得需要绑定的帐号类型,完成绑定
                accountDao.bindAccount(accountInDB.getUserId(), account, userInfoVO.getAccountType(), null);
                break;
            case AccountTypeEnum.EMAIL:
                bindEmail(account, userInfoVO.getPwd(), userInfoVO.getAppKey(), accountInDB.getUserId(), userInfoVO.getIpAddress());
                break;
            case AccountTypeEnum.FACEBOOK:
                // 将要绑定的facebookId和目前帐号中的facebookId不能相同
                if (StringUtils.isNotBlank(accountInDB.getFacebookId()) && accountInDB.getFacebookId().equals(userInfoVO.getFacebookId())) {
                    throw new YFException(ReturnMessageEnum.ACCOUNT_IS_BIND);
                }
                // 4. 获得需要绑定的帐号类型,完成绑定
                accountDao.bindAccount(accountInDB.getUserId(), account, userInfoVO.getAccountType(), userInfoVO.getOpenNickname());
                break;
            case AccountTypeEnum.WEIXIN:
                // 将要绑定的weixinId和目前帐号中的weixinId不能相同
                if (StringUtils.isNotBlank(accountInDB.getWeixinId()) && accountInDB.getWeixinId().equals(userInfoVO.getWeixinId())) {
                    throw new YFException(ReturnMessageEnum.ACCOUNT_IS_BIND);
                }
                // 4. 获得需要绑定的帐号类型,完成绑定
                accountDao.bindAccount(accountInDB.getUserId(), account, userInfoVO.getAccountType(), userInfoVO.getOpenNickname());
                break;
        }
    }

    /**
     * 绑定邮箱地址逻辑
     *
     * @param email     用户要绑定的邮箱地址
     * @param pwd       用户输入的密码
     * @param appKey    加密解密的appKey
     * @param userId    本用户的ID
     * @param ipAddress 用户本次请求的IP地址
     */
    private void bindEmail(String email, String pwd, String appKey, Long userId, String ipAddress) {

        Account findAccount = accountDao.findAccountByEmail(email);
        if (findAccount != null) {
            if (findAccount.getActivateStatus() == ActivateStatusEnum.ACTIVATED
                    || !findAccount.getUserId().equals(userId)) {
                // 1. email已经被激活或者查询到的邮箱地址已经被别人注册,就不做任何处理
                throw new YFException(ReturnMessageEnum.ACCOUNT_IS_BIND);
            } else {
                // 2. 如果是本人注册的email,但是没有激活,就发送一份激活邮件,相反,激活了就不用再发邮件
                checkCodeService.sendCheckCodeByEmailActivate(userId, email, ipAddress, ActivateTypeEnum.REGISTER_ACTIVATE);
                return;
            }
        }
        // 4. 如果没有email没有注册和激活,就走绑定流程,保存email和密码,并发送绑定激活邮件
        if (StringUtils.isNotBlank(pwd)) {
            // 密码不为空,就要解密
            pwd = AESUtil.decryptAndXOR(pwd, appKey);
        }
        // 5. 保存临时帐号和密码,并发送激活邮件
        accountTemporaryService.bindAccountTemporary(userId, email, ipAddress, pwd);

    }

    @Override
    public void unbindAccount(Account accountObj, UserInfoVO userInfoVO) {
//        // 1. 如果帐号中只用一种帐号存在,就不能解除绑定
        Integer accountNum = 0;
        if (StringUtils.isNotBlank(accountObj.getMobile())) {
            accountNum++;
        }
        if (StringUtils.isNotBlank(accountObj.getEmail())) {
            accountNum++;
        }
        if (StringUtils.isNotBlank(accountObj.getFacebookId())) {
            accountNum++;
        }
        if (StringUtils.isNotBlank(accountObj.getWeixinId())) {
            accountNum++;
        }
        if (accountNum <= 1) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_ONLY_ONE);
        }

        // 2. 解密帐号
        String account = AESUtil.decryptAndXOR(userInfoVO.getAccount(), userInfoVO.getAppKey());


        // 3. 根据帐号类型执行逻辑
        switch (userInfoVO.getAccountType()) {
            case AccountTypeEnum.MOBILE:
                // 4. 如果当前用户信息被解除的帐号为空,或者被解除的帐号和用户提交需要解除的帐号不相同,就不允许解除帐号
                if (StringUtils.isBlank(accountObj.getMobile()) || !accountObj.getMobile().equals(account)) {
                    throw new YFException(ReturnMessageEnum.ACCOUNT_NOT_BIND);
                }
                // 验证码不能为空
//                if (StringUtils.isBlank(userInfoVO.createCheckCode())) {
//                    throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_NULL);
//                }
                // 判断验证码是否正确
//                if (!checkCodeService.verifyCheckCodeByAccount(account, userInfoVO.createCheckCode(),
//                        CheckCodeActionTypeEnum.MOBILE_BIND)) {
//                    throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_INVALID);
//                }
                break;
            case AccountTypeEnum.EMAIL:
                if (StringUtils.isBlank(accountObj.getEmail()) || !accountObj.getEmail().equals(account)) {
                    throw new YFException(ReturnMessageEnum.ACCOUNT_NOT_BIND);
                }
                break;
            case AccountTypeEnum.FACEBOOK:
                if (StringUtils.isBlank(accountObj.getFacebookId()) || !accountObj.getFacebookId().equals(account)) {
                    throw new YFException(ReturnMessageEnum.ACCOUNT_NOT_BIND);
                }
                break;
            case AccountTypeEnum.WEIXIN:
                if (StringUtils.isBlank(accountObj.getWeixinId()) || !accountObj.getWeixinId().equals(account)) {
                    throw new YFException(ReturnMessageEnum.ACCOUNT_NOT_BIND);
                }
                break;
        }
        // 4. 获得需要绑定的帐号类型,完成绑定
        accountDao.unbindAccount(accountObj.getUserId(), account, userInfoVO.getAccountType());
    }

    @Override
    public UserInfoVO resetPassword(String account, Integer accountType, String pwd, String checkCode, String appKey, String ipAddress) {
        // 1. 查看帐号是否存在
        Long userId = 0L;
        if (AccountTypeEnum.MOBILE == accountType) {
            account = AESUtil.decrypt(account, appKey);
            account = AESUtil.encrypt4InitStr(account, appKey);
            userId = accountDao.findUserIdByMobileOrEmail(account, null);
            if (userId == null || userId == 0) {
                throw new YFException(ReturnMessageEnum.MOBILE_IS_NOT_REGISTERED);
            }
            // 3. 如果使用电话号码修改用户密码,APP要对密码加密后上传到服务器
            pwd = AESUtil.decrypt(pwd, appKey);
            pwd = AESUtil.encrypt4InitStr(pwd, appKey);
            // 如果使用电话号码重置密码,就要把邮箱地址清空
        } else {
            userId = accountDao.findUserIdByMobileOrEmail(null, account);
            if (userId == null || userId == 0) {
                throw new YFException(ReturnMessageEnum.EMAIL_IS_NOT_REGISTERED);
            }
        }
        // 3. 判断验证码是否正确
        boolean result = checkCodeService.verifyCheckCodeByAccount(account, checkCode, CheckCodeActionTypeEnum.RESET_PWD, null);
        if (!result) {
            throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_INVALID);
        }
        // 4. 修改用户密码
        accountDao.updateAccountPasswordByUserId(userId, pwd);
        // 5. 重置密码以后,生成新的token,返回给APP,APP直接登录系统,不再需要让用户输入用户密码来登录
        String accessToken = CodeGenerator.getAccessToken();
        Date nowDate = new Date();
        Date validityDate = DateUtils.addDays(DateUtils.truncate(nowDate, Calendar.DAY_OF_MONTH),
                ApplicationConstants.TOKEN_VALID_DAY_COUNT);
        accountDao.updateAccountToken(accessToken, validityDate, userId, accountType);

        // 6. 把验证码状态修改为无效
        checkCodeService.updateCheckCodeValidByAccount(account, accountType, CheckCodeStatusEnum.INVALID, null);
        // 6. 返回用户信息
        return userInfoService.findAccountAndUserInfoAndTargetByUserId(userId);
    }

    @Override
    public Long findUserIdByMobileOrEmail(String mobile, String email) {
        return accountDao.findUserIdByMobileOrEmail(mobile, email);
    }

    @Override
    public boolean checkEmailIsActivated(Long userId, String email) {
        // 1. 通过token查询用户帐号信息和用户信息,根据用户判断,加上用户ID作为判断条件
        Account account = accountDao.findAccountByEmail(email);
        if (account != null && account.getUserId().equals(userId) && account.getActivateStatus() != null
                && ActivateStatusEnum.ACTIVATED == account.getActivateStatus()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserSimpleInfo findSimpleUserInfoByToken(String accessToken) {
        return accountDao.findSimpleUserInfoByToken(accessToken);
    }

    @Override
    public void updateAccountEmailActivateStatus(Long userId, String email, String pwd) {
        accountDao.updateAccountEmailActivateStatus(userId, email, pwd);
    }

}
