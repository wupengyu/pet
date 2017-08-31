package com.yf.pet.service;


import com.yf.pet.entity.user.UserInfo;

/**
 * 用户信息接口类
 * Created by Infi on 17/3/26.
 */
public interface UserInfoTemporaryService {
    /**
     * 新增用户信息
     *
     * @param userInfo 用户信息
     */
    void addUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息,根据用户ID修改用户信息
     *
     * @param userInfo 用户信息
     */
    void updateUserInfoByUserId(UserInfo userInfo);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    UserInfo findUserInfoByUserId(Long userId);

    /**
     * 删除临时帐号
     *
     * @param userId 用户ID
     */
    void deleteUserInfoTemporaryByUserId(Long userId);
}
