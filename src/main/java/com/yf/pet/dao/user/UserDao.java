package com.yf.pet.dao.user;

import com.twilio.sdk.resource.instance.Account;
import com.yf.pet.entity.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

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
     * @param account 帐号信息,根据mobile,email,facebookId,weixinId来查询
     * @return 查到的个数
     */
    Integer findAccountIsExist(User user);


}
