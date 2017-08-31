/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.service.impl;

import com.yf.pet.dao.sossms.LookupNumberDao;
import com.yf.pet.entity.sossms.LookupNumber;
import com.yf.pet.service.LookupNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * twilio平台电话号码检查接口实现层
 *
 * @author Infi
 */
@Service("lookupNumberService")
public class LookupNumberServiceImpl implements LookupNumberService {
    @Autowired
    private LookupNumberDao lookupNumberDao;

    @Override
    public void addNumber(String number, Date createTime, String type, String carrierName, String countryCode) {
        lookupNumberDao.addNumber(number, createTime, type, carrierName, countryCode);
    }

    @Override
    public LookupNumber findNumber(String number) {
        return lookupNumberDao.findNumber(number);
    }

}
