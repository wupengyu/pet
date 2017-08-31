package com.yf.pet.service.impl;

import com.yf.pet.dao.user.UserTimezoneModifyDao;
import com.yf.pet.entity.user.UserTimezoneModify;
import com.yf.pet.service.UserTimezoneModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户时区变更记录信息service
 * Created by Infi on 17/3/22.
 */
@Service("userTimezoneModifyService")
public class UserTimezoneModifyServiceImpl implements UserTimezoneModifyService {

    @Autowired
    private UserTimezoneModifyDao userTimezoneModifyDao;

    @Override
    public void addUserTimezone(UserTimezoneModify userTimezoneModify) {
        userTimezoneModifyDao.addUserTimezone(userTimezoneModify);
    }
}
