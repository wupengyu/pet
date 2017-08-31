package com.yf.pet.service;


import com.yf.pet.entity.user.vo.UserInfoVO;

/**
 * 用户登录接口类
 * Created by Infi on 17/3/26.
 */
public interface LoginService {
    /**
     * 根据电话号码、邮箱地址、FacebookID、微信登录
     *
     * @param userInfo 帐号密码
     * @return 用户ID
     */
    Long loginByAccount(UserInfoVO userInfo);


    /**
     * 登出
     *
     * @param accessToken accessToken
     * @return 登出操作结果
     */
    void logout(String accessToken);
}
