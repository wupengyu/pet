/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.entity.sossms;

import java.util.Date;

/**
 * twilio平台电话号码检查
 *
 * @author Infi
 */
public class LookupNumber {

    private Date createTime;

    private String number;

    private String type;

    private String carrierName;

    private String countryCode;

    /**
     * 获取电话类型：LANDLINE(固定电话), MOBILE, VOIP(网络电话)
     *
     * @return type 电话类型：LANDLINE(固定电话), MOBILE, VOIP(网络电话)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置电话类型：LANDLINE(固定电话), MOBILE, VOIP(网络电话)
     *
     * @param type 电话类型：LANDLINE(固定电话), MOBILE, VOIP(网络电话)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取短信运营商,短信或电话是通过那个运营商发出去的，比如中国联通深圳分公司
     *
     * @return carrierName 短信运营商,短信或电话是通过那个运营商发出去的，比如中国联通深圳分公司
     */
    public String getCarrierName() {
        return carrierName;
    }

    /**
     * 设置短信运营商,短信或电话是通过那个运营商发出去的，比如中国联通深圳分公司
     *
     * @param carrierName 短信运营商,短信或电话是通过那个运营商发出去的，比如中国联通深圳分公司
     */
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    /**
     * 获取电话号码国家代码
     *
     * @return countryCode 电话号码国家代码
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置电话号码国家代码
     *
     * @param countryCode 电话号码国家代码
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 获取创建时间
     *
     * @return createTime 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取电话号码
     *
     * @return number 电话号码
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置电话号码
     *
     * @param number 电话号码
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
