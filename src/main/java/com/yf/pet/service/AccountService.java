package com.yf.pet.service;


import com.yf.pet.entity.user.Account;
import com.yf.pet.entity.user.UserSimpleInfo;
import com.yf.pet.entity.user.vo.UserInfoVO;

import java.io.IOException;
import java.util.Date;

/**
 * 帐号信息接口
 * Created by Infi on 17/3/22.
 */
public interface AccountService {
    /**
     * 新增帐号信息
     *
     * @param userInfoVO 新增帐号信息,包括account信息和userinfo信息
     * @return 帐号ID
     */
    Long addAccount(UserInfoVO userInfoVO) throws IOException;

    /**
     * 新增帐号信息
     *
     * @param account 帐号信息
     */
    void addAccount(Account account);

    /**
     * 修改帐号信息
     *
     * @param account 帐号信息
     * @return 帐号信息
     */
    void updateAccount(Account account);

    /**
     * 更新用户token信息,登录时候更新
     * @param accessToken  token
     * @param validityDate token 过期时间
     * @param userId       用户ID
     * @param loginType 登录类型,1:mobile,2:email,3:Facebook,4:微信扥登录
     */
    void updateAccountToken(String accessToken, Date validityDate, Long userId, Integer loginType);

    /**
     * 查询电话号码是否已经注册
     *
     * @param mobile 电话号码
     * @return 查到的电话号码的行数
     */
    Integer findMobileIsExist(String mobile);

    /**
     * 查询邮箱地址是否已经注册
     *
     * @param email 邮箱地址
     * @return 检查结果
     */
    Integer findEmailIsExist(String email);

    /**
     * 根据mobile或者email查询用户userId 登录是验证帐号密码是否正确。一次登录方式只能是mobile或者email
     *
     * @param mobile 电话号码
     * @param email  邮箱地址
     * @return 帐号信息
     */
    Account findAccountByMobileOrEmailAndPwd(String mobile, String email);

    /**
     * 查询用户帐号概要信息
     *
     * @param account     帐号
     * @param accountType 帐号类型,1:mobile,2:email,3:facebookId,4:weixinId
     * @return 帐号概要信息
     */
    Account findAccountSummary(String account, Integer accountType);

    /**
     * 根据OpenId查询用户userId 登录时验证openId是否正确, openId登录不能同时传facebookId和weixinId
     *
     * @param facebookId facebook帐号ID
     * @param weixinId   微信帐号ID
     * @return 帐号信息
     */
    Account findAccountByOpenId(String facebookId, String weixinId);

    /**
     * 验证accessToken是否有效
     *
     * @param accessToken accessToken
     * @return 用户ID, token过期时间信息
     */
    Account findAccessTokenIsValid(String accessToken);

    /**
     * 根据accessToken查询用户信息
     *
     * @param accessToken accessToken
     * @return 用户账户信息
     */
    Account findAccountByAccessToken(String accessToken);

    /**
     * 根据accessToken查询用户信息,包括密码
     *
     * @param accessToken token
     * @return 用户帐号信息
     */
    Account findAccountAndPwdByAccessToken(String accessToken);

    /**
     * 根据用户id查询帐号信息
     *
     * @param userId 用户ID
     * @return 帐号信息
     */
    Account findAccountByUserId(Long userId);

    /**
     * 通过邮箱地址查询用户信息
     *
     * @param email 邮箱地址
     * @return 用户帐号信息
     */
    Account findAccountByEmail(String email);

    /**
     * 用户登出是修改帐号信息
     *
     * @param accessToken accessToken
     */
    void updateAccessTokenByLogout(String accessToken);

    /**
     * 用户帐号绑定
     *
     * @param account    数据库中的帐号信息
     * @param userInfoVO 用户帐号
     */
    void bindAccount(Account account, UserInfoVO userInfoVO);

    /**
     * 用户帐号解除绑定
     *
     * @param account    数据库中的帐号信息
     * @param userInfoVO 用户帐号信息
     */
    void unbindAccount(Account account, UserInfoVO userInfoVO);


    /**
     * 密码重置<br>
     * 1. 使用mobile重置密码,APP要对密码进行aes加密<br>
     * 2. 使用email重置密码,邮件中的html页面,只对密码进行md5加密<br>
     * @param account    电话号码
     * @param accountType     邮箱地址
     * @param pwd       密码
     * @param checkCode 验证码
     * @param appKey    加密密钥
     * @param ipAddress 用户IP地址
     * @return 返回用户信息
     */
    UserInfoVO resetPassword(String account, Integer accountType, String pwd, String checkCode, String appKey, String ipAddress);

    /**
     * 根据mobile或者email查询用户userId
     *
     * @param mobile 电话号码
     * @param email  邮箱地址
     * @return 用户ID
     */
    Long findUserIdByMobileOrEmail(String mobile, String email);

    /**
     * 检查用户邮箱地址是否已经激活
     *
     * @return 邮箱激活状态
     * @param userId 用户ID
     * @param email 邮箱地址
     */
    boolean checkEmailIsActivated(Long userId, String email);

    /**
     * 查询用户简要信息,提供给其他模块使用
     *
     * @param accessToken 用户token
     * @return 返回用户简要信息
     */
    UserSimpleInfo findSimpleUserInfoByToken(String accessToken);

    /**
     * 邮箱激活修改邮箱地址和激活状态
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @param pwd    密码
     */
    void updateAccountEmailActivateStatus(Long userId, String email, String pwd);
}
