package com.yf.pet.dao.user;


import com.yf.pet.entity.user.UserTimezoneModify;

/**
 * 用户时区变更记录信息dao
 * Created by Infi on 17/3/22.
 */
public interface UserTimezoneModifyDao {
    /**
     * 新增用户时区信息
     *
     * @param userTimezoneModify 用户时区信息
     */
    void addUserTimezone(UserTimezoneModify userTimezoneModify);

}
