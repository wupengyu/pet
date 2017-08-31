/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.dao.sossms;

import com.yf.pet.entity.sossms.SmsLimit;
import com.yf.pet.entity.sossms.SmsRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * sos短信管理dao层
 *
 * @author Infi
 */
public interface SosDao {

    /**
     * 新增sos短信发送记录
     *
     * @param sosRecord sos短信记录
     */
    void addSosRecord(SmsRecord sosRecord);

    /**
     * 修改用户跌倒地点信息
     *
     * @param sosId      sos短信记录id
     * @param sosAddress 用户跌倒的地址
     */
    void updateSosRecordAddress(@Param("sosId") Integer sosId, @Param("sosAddress") String sosAddress);

    /**
     * 查询用户跌倒后发送的sos信息
     *
     * @param sosId sos短信id
     * @return sos短信记录列表
     */
    SmsRecord findSosRecordById(@Param("sosId") Long sosId);

    /**
     * 添加短信发送统计信息
     *
     * @param sosLimit sos短信发送统计数据
     */
    void addSosLimit(SmsLimit sosLimit);

    /**
     * 当天内短信发送次数累加
     *
     * @param userId   用户id
     * @param lastTime 最后一次发送sos短信的时间
     * @param smsType  短信类型，1：sos短信，2：绑定紧急联系人通知短信
     */
    void updateSosTimesAddUp(@Param("userId") Long userId, @Param("lastTime") Date lastTime, @Param("smsType") Integer smsType);

    /**
     * 初始化短信发送次数，因为第一天的发送次数，到了第二天用户发送sos短信的时候次数要初始化
     *
     * @param userId   用户id
     * @param lastTime 最后一次发送sos短信的时间
     * @param smsType  短信类型，1：sos短信，2：绑定紧急联系人通知短信
     */
    void updateSosInit(@Param("userId") Long userId, @Param("lastTime") Date lastTime, @Param("smsType") Integer smsType);

    /**
     * 根据用户id查询用户发送sos短信的统计数据
     *
     * @param userId  用户id
     * @param smsType 短信类型，1：sos短信，2：绑定紧急联系人通知短信
     * @return sos短信发送统计信息
     */
    SmsLimit findSosTimsByUserId(@Param("userId") Long userId, @Param("smsType") Integer smsType);

}
