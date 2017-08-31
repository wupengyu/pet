package com.yf.pet.service.impl;

import com.yf.pet.dao.usertemporary.UserInfoTemporaryDao;
import com.yf.pet.entity.user.UserInfo;
import com.yf.pet.service.AccountTemporaryService;
import com.yf.pet.service.DailyTargetService;
import com.yf.pet.service.UserInfoTemporaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现类
 * Created by Infi on 17/3/26.
 */
@Service("userInfoTemporaryService")
public class UserInfoTemporaryServiceImpl implements UserInfoTemporaryService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoTemporaryServiceImpl.class);
    @Autowired
    private UserInfoTemporaryDao userInfoTemporaryDao;

    @Autowired
    private AccountTemporaryService accountTemporaryService;

    @Autowired
    private DailyTargetService dailyTargetService;


    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoTemporaryDao.addUserInfo(userInfo);
    }

    @Override
    public void updateUserInfoByUserId(UserInfo userInfo) {
        userInfoTemporaryDao.updateUserInfoByUserId(userInfo);
    }

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoTemporaryDao.findUserInfoByUserId(userId);
    }


    @Override
    public void deleteUserInfoTemporaryByUserId(Long userId) {
        userInfoTemporaryDao.deleteUserInfoTemporaryByUserId(userId);
    }


}
