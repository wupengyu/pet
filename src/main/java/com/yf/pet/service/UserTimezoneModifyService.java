package com.yf.pet.service;


import com.yf.pet.entity.user.UserTimezoneModify;

/**
 * 用户时区变更记录信息service
 * Created by Infi on 17/3/22.
 */
public interface UserTimezoneModifyService {
    /**
     * 新增用户时区信息
     *
     * @param userTimezoneModify 用户时区信息
     */
    void addUserTimezone(UserTimezoneModify userTimezoneModify);

}
