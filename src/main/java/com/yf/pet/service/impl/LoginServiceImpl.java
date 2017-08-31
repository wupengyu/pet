package com.yf.pet.service.impl;

import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.enums.AccountTypeEnum;
import com.yf.pet.entity.user.Account;
import com.yf.pet.entity.user.vo.UserInfoVO;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.AccountService;
import com.yf.pet.service.LoginService;
import com.yf.pet.service.UserInfoService;
import com.yf.pet.utils.ApplicationConstants;
import com.yf.pet.utils.CodeGenerator;
import com.yf.pet.utils.aes.AESUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * 用户登录接口实现层
 * Created by Infi on 17/3/26.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public Long loginByAccount(UserInfoVO userInfo) {
        // 1. 参数校验
        if (StringUtils.isBlank(userInfo.getAccount()) || userInfo.getAccountType() == null
                || StringUtils.isBlank(userInfo.getAppKey()) || userInfo.getClientType() == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if ((AccountTypeEnum.MOBILE == userInfo.getAccountType() || AccountTypeEnum.EMAIL == userInfo.getAccountType())
                && StringUtils.isBlank(userInfo.getPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_IS_NULL);
        }
        //2. 帐号解密
        String account = AESUtil.decryptAndXOR(userInfo.getAccount(), userInfo.getAppKey());

        // 3. 查询帐号概要信息
        Account accountObj = accountService.findAccountSummary(account, userInfo.getAccountType());
        // 4. 判断帐号是否存在
        if (accountObj == null || accountObj.getUserId() == null) {
            throw new YFException(ReturnMessageEnum.USER_NO_EXIST);
        }
        // 5. 判断密码是否正确
        if (AccountTypeEnum.MOBILE == userInfo.getAccountType() || AccountTypeEnum.EMAIL == userInfo.getAccountType()) {
            String pwd = AESUtil.decryptAndXOR(userInfo.getPwd(), userInfo.getAppKey());
            if (!pwd.equals(accountObj.getPwd())) {
                throw new YFException(ReturnMessageEnum.LOGIN_ERROR);
            }
        }
        // 6. 更新用户token
        String accessToken = CodeGenerator.getAccessToken();
        Date nowDate = new Date();
        Date validityDate = DateUtils.addDays(DateUtils.truncate(nowDate, Calendar.DAY_OF_MONTH),
                ApplicationConstants.TOKEN_VALID_DAY_COUNT);
        accountService.updateAccountToken(accessToken, validityDate, accountObj.getUserId(), userInfo.getAccountType());

        // 7. 返回用户信息
        return accountObj.getUserId();
    }

    @Override
    public void logout(String accessToken) {
        accountService.updateAccessTokenByLogout(accessToken);
    }
}
