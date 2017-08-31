package com.yf.pet.service.impl;

import com.yf.pet.dao.usertemporary.AccountTemporaryDao;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.checkcode.CheckCode;
import com.yf.pet.entity.enums.*;
import com.yf.pet.entity.user.Account;
import com.yf.pet.entity.user.UserInfo;
import com.yf.pet.entity.user.vo.UserInfoVO;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.*;
import com.yf.pet.utils.*;
import com.yf.pet.utils.aes.AESUtil;
import com.yf.pet.utils.primary.YFPrimaryKeyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static com.yf.pet.utils.ApplicationConstants.SMS_VERIFY_CODE_VALID_BY_RESET_PWD;

/**
 * 用户目标值记录接口实现层
 * Created by Infi on 17/3/22.
 */
@Service("accountTemporaryService")
public class AccountTemporaryServiceImpl implements AccountTemporaryService {
    private static final Logger log = LoggerFactory.getLogger(AccountTemporaryServiceImpl.class);
    /**
     * 邮箱激活的页面地址
     */
    private static final String COROS_EMAIL_ACTIVATE = YFResourceUtil.getValueByKey("app-coros.properties", "coros.email.activate");
    @Autowired
    private AccountTemporaryDao accountTemporaryDao;

    @Autowired
    private UserInfoTemporaryService userInfoTemporaryService;

    @Autowired
    private DailyTargetService dailyTargetService;

    @Autowired
    private CheckCodeService checkCodeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public void registerAccountTemporary(UserInfoVO userInfoVO) throws IOException {
        // 1. 检查帐号信息,和用户信息
        checkAccountInfoIsComplete(userInfoVO);

        // 2. 帐号解密
        String email = AESUtil.decryptAndXOR(userInfoVO.getAccount(), userInfoVO.getAppKey());
        // 3. 查看邮箱地址是否已经激活
        checkEmailActivateStatus(email, null);

        // 7. 保存临时帐号
        Account accountObj = new Account();
        accountObj.setEmail(email);
        accountObj.setPwd(AESUtil.decryptAndXOR(userInfoVO.getPwd(), userInfoVO.getAppKey()));
        accountObj.setClientType(userInfoVO.getClientType());
        accountObj.setIpAddress(userInfoVO.getIpAddress());
        accountObj.setRegisterType(userInfoVO.getAccountType());
        Date nowDate = new Date();
        accountObj.setCreateDate(nowDate);

        // 7. 保存头像文件
        // 获得主键
        Long userId = YFPrimaryKeyUtils.getId(ServiceModeType.USER);
        if (userInfoVO.getHeadPicFile() != null) {
            //获取存储路径
            String uploadPath = YFEnumFileKey.HEAD_PIC.getLocalPath(String.valueOf(userId));
            //生成文件名
            String tmpfileName = CodeGenerator.getFileNameCodeString();
//            String saveFilePath = uploadPath + tmpfileName;
            //将标签byte数据保存到本地文件
            FileProecssUtil.saveLocalFile(userInfoVO.getHeadPicFile().getBytes(), tmpfileName, uploadPath);
            //存储路径是帐号加斜杠加文件名: 13710637126/1490681576S8WNUA79L3P1SLQR7S20
            userInfoVO.setHeadPic(userId + "/" + tmpfileName);
        }

        // 8. 得到userinfo的数据
        UserInfo userInfo = new UserInfo();
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
        accountTemporaryDao.addAccountTemporary(accountObj);
        userInfo.setUserId(userId);
        // 目前默认所有用户的周首日都是周日
        userInfo.setFirstDayOfWeek(Calendar.SUNDAY);
        userInfoTemporaryService.addUserInfo(userInfo);
        // 10. 保存目标值
        if ((userInfoVO.getTargetCalorie() != null && userInfoVO.getTargetCalorie() > 0)
                || (userInfoVO.getTargetMotionTime() != null && userInfoVO.getTargetMotionTime() > 0)) {
            dailyTargetService.addDailyTarget(userId, userInfo.getTimezone(),
                    userInfoVO.getTargetMotionTime(), userInfoVO.getTargetCalorie());
        }
        // 2. 发送激活邮件
        checkCodeService.sendCheckCodeByEmailActivate(userId, email, userInfoVO.getIpAddress(), ActivateTypeEnum.REGISTER_ACTIVATE);
    }

    @Override
    public void bindAccountTemporary(Long userId, String email, String ipAddress, String pwd) {
        // 1. 查看要绑定的邮箱是否是重复绑定,如果邮箱已经绑定并且激活,就不再重复绑定
        Account findAccountTemporary = accountTemporaryDao.findAccountTemporaryByUserIdAndEmail(userId, email);
//        // 邮箱已绑定,激活链接为过期(48小时),就不用重新发送邮件
//        if (findAccountTemporary != null && email.equals(findAccountTemporary.getEmail())
//                && nowDate.getTime() - findAccountTemporary.getUpdateDate().getTime() < SMS_VERIFY_CODE_VALID_BY_RESET_PWD * 1000) {
//            throw new YFException(ReturnMessageEnum.EMAIL_IS_BIND_NOT_ACTIVATED);
//        }

        // 3. 新增临时邮箱帐号
        Account accountTemporary = new Account();
        accountTemporary.setUserId(userId);
        accountTemporary.setEmail(email);
        accountTemporary.setPwd(pwd);
        Date nowDate = new Date();
        accountTemporary.setCreateDate(nowDate);
        accountTemporary.setUpdateDate(nowDate);
        if (findAccountTemporary == null) {
            accountTemporaryDao.addAccountTemporary(accountTemporary);
        } else {
            accountTemporaryDao.updateAccountTemporary(accountTemporary);
        }
        // 4. 异步发送激活邮件
        Long userIdFinal = userId;
        String accountFinal = email;
        String ipAddressFinal = ipAddress;
        checkCodeService.sendCheckCodeByEmailActivate(userIdFinal, accountFinal, ipAddressFinal,
                ActivateTypeEnum.BIND_ACTIVATE);

    }

    /**
     * 检查邮箱地址是否被注册、被激活、激活邮件是否过期
     *
     * @param email  要绑定或注册的邮箱地址
     * @param userId
     */
    private void checkEmailActivateStatus(String email, Long userId) {
        // 3. 查看邮箱地址是否已经激活
        Account accountInActivate = accountService.findAccountByEmail(email);
        if (accountInActivate != null && ActivateStatusEnum.ACTIVATED == accountInActivate.getActivateStatus()) {
            throw new YFException(ReturnMessageEnum.EMAIL_IS_ACTIVATED);
        }
        // 4. 查询邮箱地址是否已经注册
        Account accountTemporary = accountTemporaryDao.findAccountTemporaryByUserIdAndEmail(userId, email);
        // 5. 查询发送的激活邮件验证码是否已经过期
        CheckCode checkCode = checkCodeService.findLastCheckCodeByAccount(email, CheckCodeActionTypeEnum.EMAIL_ACTIVATE, userId);
        Date nowDate = new Date();
        // 6. 如果临时帐号存在,并且激活邮件已经过
        if (accountTemporary != null
                && nowDate.getTime() - checkCode.getCreateDate().getTime() >= SMS_VERIFY_CODE_VALID_BY_RESET_PWD * 1000) {
            //邮箱未激活,激活邮件已过期,请重新发送邮件
            throw new YFException(ReturnMessageEnum.EMIL_NOT_ACTIVATED_MAIL_EXPIRED);
        } else if (accountInActivate != null
                && nowDate.getTime() - checkCode.getCreateDate().getTime() < SMS_VERIFY_CODE_VALID_BY_RESET_PWD * 1000) {
            //邮箱已注册,邮件未过期,请打开激活邮件进行激活
            throw new YFException(ReturnMessageEnum.EMIL_NOT_ACTIVATED_MAIL_NOT_EXPIRED);
        }
    }

    /**
     * 判断帐号信息是否完整
     *
     * @param userInfoVO APP上传的用户信息
     */
    private void checkAccountInfoIsComplete(UserInfoVO userInfoVO) {
        // 1. mobile、email、facebookId、weixin不能同时为空
        if (StringUtils.isBlank(userInfoVO.getAccount()) || userInfoVO.getAccountType() == null) {
            throw new YFException(ReturnMessageEnum.USER_NAME_IS_NULL);
        }
        // 不支持电话号码修改
        if (AccountTypeEnum.MOBILE == userInfoVO.getAccountType()) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }

        // 2. mobile和email注册的时候,密码不能为空
        if (StringUtils.isBlank(userInfoVO.getPwd())) {
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
    public void activateRegisterAccountTemporary(Long userId, String email, String checkCode, String ipAddress) {
        // 1. 检查用户ID没有被激活
        Account findAccountByUserId = accountService.findAccountByUserId(userId);
        if (findAccountByUserId != null && ActivateStatusEnum.ACTIVATED == findAccountByUserId.getActivateStatus()) {
            throw new YFException(ReturnMessageEnum.EMAIL_IS_ACTIVATED);
        }
        // 2. 检查邮件是否已经被激活
        Account findAccountByEmail = accountService.findAccountByEmail(email);
        if (findAccountByEmail != null && ActivateStatusEnum.ACTIVATED == findAccountByEmail.getActivateStatus()) {
            throw new YFException(ReturnMessageEnum.EMAIL_IS_ACTIVATED);
        }
        // 3. 检查验证码
        boolean result = checkCodeService.verifyCheckCodeByAccount(email, checkCode, CheckCodeActionTypeEnum.EMAIL_ACTIVATE, userId);
        if (!result) {
            throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_INVALID);
        }

        // 4. 修改帐号的激活状态,已激活
        accountService.updateAccountEmailActivateStatus(userId, null, null);

        // 6. 把验证码设置为无效
        checkCodeService.updateCheckCodeValidByAccount(email, CheckCodeActionTypeEnum.EMAIL_ACTIVATE, CheckCodeStatusEnum.INVALID, userId);
    }

    @Override
    public void activateBindAccountTemporary(Long userId, String email, String checkCode, String ipAddress) {
        // 1. 绑定帐号,帐号必需不能为空
        Account findAccountByUserId = accountService.findAccountByUserId(userId);
        if (findAccountByUserId == null) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_INVALID);
        }
        // 2. 检查邮件是否已经被激活
        Account findAccountByEmail = accountService.findAccountByEmail(email);
        if (findAccountByEmail != null && ActivateStatusEnum.ACTIVATED == findAccountByEmail.getActivateStatus()) {
            throw new YFException(ReturnMessageEnum.EMAIL_IS_ACTIVATED);
        }
        // 3. 检查验证码
        boolean result = checkCodeService.verifyCheckCodeByAccount(email, checkCode, CheckCodeActionTypeEnum.EMAIL_ACTIVATE, userId);
        if (!result) {
            throw new YFException(ReturnMessageEnum.CHECK_CODE_IS_INVALID);
        }

        // 4. 把临时帐号新增到正式帐号
        Account accountTemporary = accountTemporaryDao.findAccountTemporaryByUserIdAndEmail(userId, email);
//        UserInfo userInfoTemporary = userInfoTemporaryService.findUserInfoByUserId(userId);
        if (accountTemporary == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 5. 保存用户信息
        accountService.updateAccountEmailActivateStatus(accountTemporary.getUserId(), accountTemporary.getEmail(),
                accountTemporary.getPwd());
//        userInfoService.addUserInfo(userInfoTemporary);

        //6. 删除临时帐号
        accountTemporaryDao.deleteAccountTemporaryByUserId(userId);
//        userInfoTemporaryService.deleteUserInfoTemporaryByUserId(userId);
        // 7. 把验证码设置为无效
        checkCodeService.updateCheckCodeValidByAccount(email, CheckCodeActionTypeEnum.EMAIL_ACTIVATE, CheckCodeStatusEnum.INVALID, userId);
    }

    @Override
    public String findLastEmailByUserId(Long userId) {
        return accountTemporaryDao.findLastEmailByUserId(userId);
    }

    @Override
    public Account findAccountTemporaryByUserIdAndEmail(Long userId, String email) {
        return accountTemporaryDao.findAccountTemporaryByUserIdAndEmail(userId, email);
    }

}
