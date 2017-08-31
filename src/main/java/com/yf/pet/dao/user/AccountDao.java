package com.yf.pet.dao.user;

import com.yf.pet.entity.user.Account;
import com.yf.pet.entity.user.UserSimpleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 帐号dao
 * Created by Infi on 17/3/22.
 */
public interface AccountDao {
    /**
     * 新增帐号信息
     *
     * @param account 新增帐号信息
     * @return 帐号信息
     */
    void addAccount(Account account);

    /**
     * 修改帐号信息
     *
     * @param account 帐号信息<br>
     *                绑定帐号时可以修改: mobile,email,facebookId,weixinId<br>
     *                登录时可以修改: 只能修改token,validityDate,loginType,ipAddress<br>
     */
    void updateAccount(Account account);

    /**
     * 更新用户token信息,登录时候更新
     * @param accessToken  token
     * @param validityDate token 过期时间
     * @param userId       用户ID
     * @param loginType 登录类型,1:mobile,2:email,3:Facebook,4:微信扥登录
     */
    void updateAccountToken(@Param("accessToken") String accessToken, @Param("validityDate") Date validityDate,
                            @Param("userId") Long userId, @Param("loginType") Integer loginType);

    /**
     * 查询帐号是否已经注册过
     *
     * @param account 帐号信息,根据mobile,email,facebookId,weixinId来查询
     * @return 查到的个数
     */
    Integer findAccountIsExist(Account account);

    /**
     * 根据帐号和帐号类型查看帐号是否已经被注册
     *
     * @param account     帐号
     * @param accountType 帐号类型,1:mobile,2:email,3:facebookId,4:weixinId
     * @return 帐号个数
     */
    Integer findAccountIsExistByAccountType(@Param("account") String account, @Param("accountType") Integer accountType);

    /**
     * 根据用户id查询帐号信息
     *
     * @param userId 用户ID
     * @return 帐号信息
     */
    Account findAccountByUserId(@Param("userId") Long userId);

    /**
     * 通过邮箱地址查询用户信息
     *
     * @param email 邮箱地址
     * @return 用户帐号信息
     */
    Account findAccountByEmail(@Param("email") String email);

    /**
     * 查询电话号码是否已经注册
     *
     * @param mobile 电话号码
     * @return 查到的电话号码的行数
     */
    Integer findMobileIsExist(@Param("mobile") String mobile);

    /**
     * 查询邮箱地址是否已经注册
     *
     * @param email 邮箱地址
     * @return 检查结果
     */
    Integer findEmailIsExist(@Param("email") String email);

    /**
     * 根据mobile或者email查询用户userId 登录是验证帐号密码是否正确。一次登录方式只能是mobile或者email
     *
     * @param mobile 电话号码
     * @param email  邮箱地址
     * @return 账户信息
     */
    Account findAccountByMobileOrEmailAndPwd(@Param("mobile") String mobile, @Param("email") String email);

    /**
     * 根据mobile或者email查询用户userId
     *
     * @param mobile 电话号码
     * @param email  邮箱地址
     * @return 用户ID
     */
    Long findUserIdByMobileOrEmail(@Param("mobile") String mobile, @Param("email") String email);

    /**
     * 根据OpenId查询用户userId 登录时验证openId是否正确, openId登录不能同时传facebookId和weixinId
     *
     * @param facebookId facebook帐号ID
     * @param weixinId   微信帐号ID
     * @return 账户信息
     */
    Account findAccountByOpenId(@Param("facebookId") String facebookId, @Param("weixinId") String weixinId);


    /**
     * 查询用户帐号概要信息
     *
     * @param account     帐号
     * @param accountType 帐号类型,1:mobile,2:email,3:facebookId,4:weixinId
     * @return 帐号概要信息
     */
    Account findAccountSummary(@Param("account") String account, @Param("accountType") Integer accountType);

    /**
     * 验证accessToken是否有效
     *
     * @param accessToken accessToken
     * @return 用户ID, token过期时间信息
     */
    Account findAccessTokenIsValid(@Param("accessToken") String accessToken);

    /**
     * 根据accessToken查询用户信息
     *
     * @param accessToken accessToken
     * @return 用户账户信息
     */
    Account findAccountByAccessToken(@Param("accessToken") String accessToken);

    /**
     * 根据accessToken查询用户信息,包括密码
     *
     * @param accessToken token
     * @return 用户帐号信息
     */
    Account findAccountAndPwdByAccessToken(@Param("accessToken") String accessToken);

    /**
     * 用户登出是修改帐号token信息
     *
     * @param accessToken
     */
    void updateAccessTokenByLogout(@Param("accessToken") String accessToken);

    /**
     * 帐号绑定,如果已经绑定过,就不能再绑定
     * @param userId     用户ID
     * @param account     帐号,mobile、email、FacebookId、weixinId
     * @param accountType     帐号类型 1:mobile、2:email、3:FacebookId、4:weixinId
     * @param nickname 昵称
     */
    void bindAccount(@Param("userId") Long userId, @Param("account") String account, @Param("accountType") Integer accountType,
                     @Param("nickname") String nickname);

    /**
     * 解除帐号绑定,如果只有一种帐号类型,就不能绑定
     * @param userId     用户ID
     * @param account     帐号,mobile、email、FacebookId、weixinId
     * @param accountType      帐号类型 1:mobile、2:email、3:FacebookId、4:weixinId
     */
    void unbindAccount(@Param("userId") Long userId, @Param("account") String account, @Param("accountType") Integer accountType);

    /**
     * 根据电话号码或者邮箱地址修改密码
     *
     * @param userId 用户ID
     * @param pwd    密码
     */
    void updateAccountPasswordByUserId(@Param("userId") Long userId, @Param("pwd") String pwd);

    /**
     * 查询用户简要信息,提供给其他模块使用
     *
     * @param accessToken 用户token
     * @return 返回用户简要信息
     */
    UserSimpleInfo findSimpleUserInfoByToken(@Param("accessToken") String accessToken);


    /**
     * 邮箱激活修改邮箱地址和激活状态
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @param pwd    密码
     */
    void updateAccountEmailActivateStatus(@Param("userId") Long userId, @Param("email") String email, @Param("pwd") String pwd);
}
