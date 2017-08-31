package com.yf.pet.service;


import com.yf.pet.entity.user.UserInfo;
import com.yf.pet.entity.user.vo.UserInfoVO;

import java.io.IOException;
import java.util.List;

/**
 * 用户信息接口类
 * Created by Infi on 17/3/26.
 */
public interface UserInfoService {
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
     * 根据用户token查询用户信息
     *
     * @param accessToken 用户token
     * @return 用户信息
     */
    UserInfo findUserInfoByToken(String accessToken);



    /**
     * 根据token修改用户信息
     *
     * @param accessToken accessToken
     * @param userInfoVO  用户信息
     * @return 用户ID
     */
    Long updateUserInfoByToken(String accessToken, UserInfoVO userInfoVO) throws IOException;

    /**
     * 查询帐号信息、用户信息和目标值信息
     *
     * @param userId 用户ID
     * @return 帐号信息和用户信息
     */
    UserInfoVO findAccountAndUserInfoAndTargetByUserId(Long userId);

    /**
     * 查询帐号信息和用户信息
     *
     * @param accessToken 用户token
     * @return 帐号信息和用户信息
     */
    UserInfoVO findAccountAndUserInfoByAccessToken(String accessToken);

    /**
     * 根据用户id列表查询用户主要信息列表
     *
     * @param userIds 用户ID列表
     * @return 用户列表
     */
    List<UserInfo> findSimpleUserInfoListByUserId(List<Long> userIds);

    /**
     * 修改用户当前所在时区信息
     *
     * @param userInfo 用户信息
     * @param timezone APP上传的用户当前所在时区信息
     */
    void updateTimezone(UserInfo userInfo, Integer timezone);

    /**
     * 保存用户紧急联系人电话
     *
     * @param userId             用户id
     * @param contactsMobile     紧急联系人电话
     * @param nickName           用户昵称
     * @param contactNote        紧急联系人电话备注
     * @param phoneCode          紧急联系人电话国际区号，如中国的区号是+86
     * @param contactCountryCode 紧急联系人电话国家代码
     */
    void saveUserToContact(Long userId, String contactsMobile, String nickName, String contactNote, String phoneCode, String contactCountryCode);

}
