package com.yf.pet.entity.checkcode;

import java.io.Serializable;
import java.util.Date;

/**
 * 概述: 验证码请求日志DTO对象<br>
 * 背景: <br>
 * Created by Infi on 17/3/9.
 */
public class CodeRequestLog implements Serializable {
    private static final long serialVersionUID = 9208468857995128573L;
    private String uuid;
    private String remoteIPAddress;
    private Long remoteIPNum;
    private Date createDate;
    private Integer days;
    private String account;
    private Integer tag;


    /**
     * 获取主键
     *
     * @return uuid 主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置主键
     *
     * @param uuid 主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取调用远程IP地址
     *
     * @return remoteIPAddress 调用远程IP地址
     */
    public String getRemoteIPAddress() {
        return remoteIPAddress;
    }

    /**
     * 设置调用远程IP地址
     *
     * @param remoteIPAddress 调用远程IP地址
     */
    public void setRemoteIPAddress(String remoteIPAddress) {
        this.remoteIPAddress = remoteIPAddress;
    }

    /**
     * 获取调用远程IP
     *
     * @return remoteIPNum 调用远程IP
     */
    public Long getRemoteIPNum() {
        return remoteIPNum;
    }

    /**
     * 设置调用远程IP
     *
     * @param remoteIPNum 调用远程IP
     */
    public void setRemoteIPNum(Long remoteIPNum) {
        this.remoteIPNum = remoteIPNum;
    }

    /**
     * 获取调用日期
     *
     * @return createDate 调用日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置调用日期
     *
     * @param createDate 调用日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取调用天
     *
     * @return days 调用天
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 设置调用天
     *
     * @param days 调用天
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * 获取调用远程IP地址
     *
     * @return account 调用远程IP地址
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置调用远程IP地址
     *
     * @param account 调用远程IP地址
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取调用类型(0：获取验证码,1:调用登录接口)
     *
     * @return tag 调用类型(0：获取验证码,1:调用登录接口)
     */
    public Integer getTag() {
        return tag;
    }

    /**
     * 设置调用类型(0：获取验证码,1:调用登录接口)
     *
     * @param tag 调用类型(0：获取验证码,1:调用登录接口)
     */
    public void setTag(Integer tag) {
        this.tag = tag;
    }
}
