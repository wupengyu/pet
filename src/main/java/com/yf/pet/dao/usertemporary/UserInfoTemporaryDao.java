package com.yf.pet.dao.usertemporary;

import com.yf.pet.entity.user.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息dao
 * Created by Infi on 17/3/22.
 */
public interface UserInfoTemporaryDao {
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
    UserInfo findUserInfoByUserId(@Param("userId") Long userId);

    /**
     * 删除临时帐号信息
     *
     * @param userId 用户id
     */
    void deleteUserInfoTemporaryByUserId(@Param("userId") Long userId);

}
