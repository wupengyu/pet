/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.entity.sossms;

import java.util.Date;

/**
 * sos短信限制表
 *
 * @author Infi
 */
public class SmsLimit {

    private Long userId;

    private Date lastTime;

    private Integer times;

    private Integer sumTimes;

    private Integer smsType;

    /**
     * 获取smsType#短信类型，1：sos短信，2：绑定紧急联系人通知短信
     *
     * @return smsType smsType#短信类型，1：sos短信，2：绑定紧急联系人通知短信
     */
    public Integer getSmsType() {
        return smsType;
    }

    /**
     * 设置smsType#短信类型，1：sos短信，2：绑定紧急联系人通知短信
     *
     * @param smsType smsType#短信类型，1：sos短信，2：绑定紧急联系人通知短信
     */
    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    /**
     * 获取用户id
     *
     * @return userId 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取最近一次发送短信时间
     *
     * @return lastTime 最近一次发送短信时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 设置最近一次发送短信时间
     *
     * @param lastTime 最近一次发送短信时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取发送次数
     *
     * @return times 发送次数
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * 设置发送次数
     *
     * @param times 发送次数
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * 获取累计发送次数
     *
     * @return sumTimes 累计发送次数
     */
    public Integer getSumTimes() {
        return sumTimes;
    }

    /**
     * 设置累计发送次数
     *
     * @param sumTimes 累计发送次数
     */
    public void setSumTimes(Integer sumTimes) {
        this.sumTimes = sumTimes;
    }

}
