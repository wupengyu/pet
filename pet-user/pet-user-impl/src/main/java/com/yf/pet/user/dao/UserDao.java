package com.yf.pet.user.dao;

import com.yf.pet.user.api.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 帐号dao
 * Created by Infi on 17/3/22.
 */
public interface UserDao {
    /**
     * 新增帐号信息
     *
     * @param user 新增帐号信息
     * @return 帐号信息
     */
    Integer addUser(User user);

    /**
     * 查询帐号是否已经注册过
     *
     * @param email
     * @param openId
     * @return
     */
    Integer findAccountIsExist(@Param("email") String email, @Param("openId")String openId );

    /**
     * 验证accessToken是否有效
     *
     * @param accessToken
     * @return 用户ID, token过期时间信息
     */
    User findAccessTokenIsValid(@Param("accessToken") String accessToken);

    /**
     * 通过accessToken查找用户信息
     *
     * @param accessToken
     * @return
     */
    User findByAccessToken(@Param("accessToken")String accessToken);

    /**
     * 通过email查找用户信息
     *
     * @param email
     * @return
     */
    User findByEmail(@Param("email")String email);

    /**
     * 通过第三方账户查找用户信息
     *
     * @param openId
     * @param openType
     * @return
     */
    User findByOpenId(@Param("openId")String openId,@Param("openType") Integer openType);

    /**
     * 更新用户登录信息
     *
     * @param user
     */
    void updateUserLoginInfo(User user);

    /**
     * 密码重置
     *
     * @param user
     */
    void pwdReset(User user);

    /**
     * 登出
     */
    void updateTokenIsNull(@Param("accessToken")String accessToken);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUserInfo(User user);
}
