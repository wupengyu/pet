package com.yf.pet.dao.usertemporary;

import com.yf.pet.entity.user.Account;
import org.apache.ibatis.annotations.Param;

/**
 * 临时帐号帐号dao
 * Created by Infi on 17/3/22.
 */
public interface AccountTemporaryDao {
    /**
     * 新增帐号信息
     *
     * @param account 新增帐号信息
     * @return 帐号信息
     */
    void addAccountTemporary(Account account);

    /**
     * 修改帐号信息,重新绑定时修改邮箱地址和密码
     *
     * @param account 帐号信息<br>
     *                绑定帐号时可以修改: mobile,email,facebookId,weixinId<br>
     *                登录时可以修改: 只能修改token,validityDate,loginType,ipAddress<br>
     */
    void updateAccountTemporary(Account account);


    /**
     * 根据用户id查询帐号信息
     *
     * @param userId 用户ID
     * @param email
     * @return 帐号信息
     */
    Account findAccountTemporaryByUserId(@Param("userId") Long userId, String email);

    /**
     * 根据邮箱地址查询临时帐号信息
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @return 临时帐号信息
     */
    Account findAccountTemporaryByUserIdAndEmail(@Param("userId") Long userId, @Param("email") String email);

    /**
     * 删除临时帐号
     *
     * @param userId 用户ID
     */
    void deleteAccountTemporaryByUserId(@Param("userId") Long userId);

    /**
     * 查询最近一个未激活的邮箱地址
     *
     * @param userId 用户ID
     * @return 最近一个未激活的邮箱地址
     */
    String findLastEmailByUserId(@Param("userId") Long userId);
}
