/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.entity.sossms;

import java.util.Date;

/**
 * sos短信记录表
 *
 * @author Infi
 */
public class SmsRecord {

    private Long sosId;
    private Long userId;
    private String sosLogLat;
    private String sosAddress;
    private String msgContent;
    private Date createTime;
    private String nickname;
    private String contactsMobile;
    private String accessToken;
    private Long tumbleTime;
    private Integer smsType;
    private Integer tumbleTimezone;
    private Date tumbleDate;
    private String timezoneName;
    private String sosShowTime;

    /**
     * 获取sos搜索页面显示时间格式，比如：Asia/Shanghai (GMT+08:45) 16:35:23, 07/11/2017
     *
     * @return sosShowTime sos搜索页面显示时间格式，比如：Asia/Shanghai (GMT+08:45) 16:35:23, 07/11/2017
     */
    public String getSosShowTime() {
        return sosShowTime;
    }

    /**
     * 设置sos搜索页面显示时间格式，比如：Asia/Shanghai (GMT+08:45) 16:35:23, 07/11/2017
     *
     * @param sosShowTime sos搜索页面显示时间格式，比如：Asia/Shanghai (GMT+08:45) 16:35:23, 07/11/2017
     */
    public void setSosShowTime(String sosShowTime) {
        this.sosShowTime = sosShowTime;
    }

    /**
     * 获取时区名称
     *
     * @return timezoneName 时区名称
     */
    public String getTimezoneName() {
        return timezoneName;
    }

    /**
     * 设置时区名称
     *
     * @param timezoneName 时区名称
     */
    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    /**
     * 获取摔倒时间
     *
     * @return tumbleDate 摔倒时间
     */
    public Date getTumbleDate() {
        return tumbleDate;
    }

    /**
     * 设置摔倒时间
     *
     * @param tumbleDate 摔倒时间
     */
    public void setTumbleDate(Date tumbleDate) {
        this.tumbleDate = tumbleDate;
    }

    /**
     * 获取摔倒是地区的时区,15分钟制
     *
     * @return tumbleTimezone 摔倒是地区的时区,15分钟制
     */
    public Integer getTumbleTimezone() {
        return tumbleTimezone;
    }

    /**
     * 设置摔倒是地区的时区,15分钟制
     *
     * @param tumbleTimezone 摔倒是地区的时区,15分钟制
     */
    public void setTumbleTimezone(Integer tumbleTimezone) {
        this.tumbleTimezone = tumbleTimezone;
    }

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
     * 获取摔倒时间戳(秒)
     *
     * @return tumbleTime 摔倒时间戳(秒)
     */
    public Long getTumbleTime() {
        return tumbleTime;
    }

    /**
     * 设置摔倒时间戳(秒)
     *
     * @param tumbleTime 摔倒时间戳(秒)
     */
    public void setTumbleTime(Long tumbleTime) {
        this.tumbleTime = tumbleTime;
    }

    /**
     * 获取登陆成功，返回访问的令牌.
     *
     * @return accessToken 登陆成功，返回访问的令牌
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置登陆成功，返回访问的令牌.
     *
     * @param accessToken 登陆成功，返回访问的令牌
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取昵称
     *
     * @return nickname 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取紧急联系人电话
     *
     * @return contactsMobile 紧急联系人电话
     */
    public String getContactsMobile() {
        return contactsMobile;
    }

    /**
     * 设置紧急联系人电话
     *
     * @param contactsMobile 紧急联系人电话
     */
    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    /**
     * 获取短信发送id
     *
     * @return sosId 短信发送id
     */
    public Long getSosId() {
        return sosId;
    }

    /**
     * 设置短信发送id
     *
     * @param sosId 短信发送id
     */
    public void setSosId(Long sosId) {
        this.sosId = sosId;
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
     * 获取摔倒地点经纬度，用逗号隔开
     *
     * @return sosLogLat 摔倒地点经纬度，用逗号隔开
     */
    public String getSosLogLat() {
        return sosLogLat;
    }

    /**
     * 设置摔倒地点经纬度，用逗号隔开
     *
     * @param sosLogLat 摔倒地点经纬度，用逗号隔开
     */
    public void setSosLogLat(String sosLogLat) {
        this.sosLogLat = sosLogLat;
    }

    /**
     * 获取摔倒地点地理位置
     *
     * @return sosAddress 摔倒地点地理位置
     */
    public String getSosAddress() {
        return sosAddress;
    }

    /**
     * 设置摔倒地点地理位置
     *
     * @param sosAddress 摔倒地点地理位置
     */
    public void setSosAddress(String sosAddress) {
        this.sosAddress = sosAddress;
    }

    /**
     * 获取sos短信内容
     *
     * @return msgContent sos短信内容
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 设置sos短信内容
     *
     * @param msgContent sos短信内容
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * 获取发送时间
     *
     * @return createTime 发送时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置发送时间
     *
     * @param createTime 发送时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
