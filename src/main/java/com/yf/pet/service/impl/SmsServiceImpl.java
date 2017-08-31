/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.service.impl;

import com.yf.pet.dao.sossms.SmsDao;
import com.yf.pet.entity.sossms.SmsLimit;
import com.yf.pet.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * sos短信发送接口实现层
 *
 * @author Infi
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {
    private static final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private SmsDao smsDao;

    @Override
    public void addSmsLimit(SmsLimit sosLimit) {
        smsDao.addSmsLimit(sosLimit);
    }

    @Override
    public void updateSmsTimesAddUp(Long userId, Date lastTime, Integer smsType) {
        smsDao.updateSmsTimesAddUp(userId, lastTime, smsType);
    }

    @Override
    public void updateSmsInit(Long userId, Date lastTime, Integer smsType) {
        smsDao.updateSmsInit(userId, lastTime, smsType);
    }

    @Override
    public SmsLimit findSmsTimsByUserId(Long userId, Integer smsType) {
        return smsDao.findSmsTimsByUserId(userId, smsType);
    }

}
