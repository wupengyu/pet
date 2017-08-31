package com.yf.pet.dao.adminuser;

import com.yf.pet.entity.adminuser.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 管理员dao层
 * Created by Infi on 17/6/28.
 */
public interface AdminUserDao {
    /**
     * 查询管理员信息
     *
     * @param account 帐号
     * @param pwd     密码
     * @return 管理员帐号信息
     */
    AdminUser findAdminUser(@Param("account") String account, @Param("pwd") String pwd);

    /**
     * 查询管理员信息
     *
     * @param accessToken token
     * @return 管理员帐号信息
     */
    AdminUser findAdminUserByToken(@Param("accessToken") String accessToken);

    /**
     * 更新管理员token
     *
     * @param id           管理员ID
     * @param accessToken
     * @param validityDate
     */
    void updateAdminUser(@Param("id") Long id, @Param("accessToken") String accessToken, @Param("validityDate") Date validityDate);
}
