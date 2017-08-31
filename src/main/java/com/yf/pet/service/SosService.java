/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.service;


import com.yf.pet.entity.sossms.SmsLimit;
import com.yf.pet.entity.sossms.SmsRecord;

/**
 * sos短信接口层
 *
 * @author Infi
 */
public interface SosService {

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
    void updateSosRecordAddress(Integer sosId, String sosAddress);

    /**
     * 查询用户跌倒后发送的sos信息
     *
     * @param sosId sos短信id
     * @return sos短信记录列表
     */
    SmsRecord findSosRecordById(Long sosId);

    /**
     * 保存紧急联系人验证短信的短信记录
     *
     * @param userId 用户id
     * @return 短信记录信息
     */
    SmsLimit saveSmsContact(Long userId);

}
