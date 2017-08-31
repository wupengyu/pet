package com.yf.pet.dao.user;

import com.yf.pet.entity.user.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息dao
 * Created by Infi on 17/3/22.
 */
public interface UserInfoDao {
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
     *
     * 修改紧急联系人电话
     * @param userId 用户id
     * @param contactsMobile 紧急联系人电话
     * @param contactNote 紧急联系人备注
     * @param phoneCode 紧急联系人电话国际区号，如中国的区号是+86
     * @param contactCountryCode 紧急联系儿电话国家区域代码
     */
    public void updateUserToContact(@Param("userId") Long userId, @Param("contactsMobile") String contactsMobile,
                                    @Param("contactNote") String contactNote, @Param("phoneCode") String phoneCode,
                                    @Param("contactCountryCode") String contactCountryCode);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    UserInfo findUserInfoByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id列表查询用户主要信息列表
     *
     * @param userIds 用户ID列表
     * @return 用户列表
     */
    List<UserInfo> findSimpleUserInfoListByUserId(@Param("list") List<Long> userIds);

    /**
     * 修改用户当前所在时区信息
     *
     * @param userId   用户信息
     * @param timezone APP上传的用户当前所在时区信息
     */
    void updateTimezoneByUserId(@Param("userId") Long userId, @Param("timezone") Integer timezone);
}
