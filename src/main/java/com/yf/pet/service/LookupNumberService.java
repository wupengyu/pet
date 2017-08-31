/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.service;


import com.yf.pet.entity.sossms.LookupNumber;

import java.util.Date;

/**
 * twilio平台电话号码检查，接口层
 *
 * @author Infi
 */
public interface LookupNumberService {
    /**
     * 新增验证通过的电话号码
     *
     * @param number      电话号码
     * @param createTime  创建时间
     * @param type        电话类型：LANDLINE(固定电话), MOBILE, VOIP(网络电话)
     * @param carrierName 短信运营商,短信或电话是通过那个运营商发出去的，比如中国联通深圳分公司
     * @param countryCode 电话号码国家代码
     */
    void addNumber(String number, Date createTime, String type, String carrierName, String countryCode);

    /**
     * 查询电话号码，如果在数据库中存在，就说明这个号码是正确的，已经验证通过的号码。
     *
     * @param number 电话号码
     * @return 验证通过的电话号码
     */
    LookupNumber findNumber(String number);

}
