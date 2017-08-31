/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.service;

import com.yf.pet.entity.sossms.SmsLimit;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 短信发送次数管理接口类
 *
 * @author Infi
 */
public interface SmsService {

    /**
     * 添加短信发送统计信息
     *
     * @param sosLimit sos短信发送统计数据
     */
    void addSmsLimit(SmsLimit sosLimit);

    /**
     * 当天内短信发送次数累加
     *
     * @param userId   用户id
     * @param lastTime 最后一次发送sos短信的时间
     * @param smsType  短信类型，1：sos短信，2：绑定紧急联系人通知短信
     */
    void updateSmsTimesAddUp(@Param("userId") Long userId, @Param("lastTime") Date lastTime, @Param("smsType") Integer smsType);

    /**
     * 初始化短信发送次数，因为第一天的发送次数，到了第二天用户发送sos短信的时候次数要初始化
     *
     * @param userId   用户id
     * @param lastTime 最后一次发送sos短信的时间
     * @param smsType  短信类型，1：sos短信，2：绑定紧急联系人通知短信
     */
    void updateSmsInit(@Param("userId") Long userId, @Param("lastTime") Date lastTime, @Param("smsType") Integer smsType);

    /**
     * 根据用户id查询用户发送sos短信的统计数据
     *
     * @param userId  用户id
     * @param smsType 短信类型，1：sos短信，2：绑定紧急联系人通知短信
     * @return sos短信发送统计信息
     */
    SmsLimit findSmsTimsByUserId(@Param("userId") Long userId, @Param("smsType") Integer smsType);

}
