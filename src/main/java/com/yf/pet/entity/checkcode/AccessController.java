package com.yf.pet.entity.checkcode;

import java.io.Serializable;
import java.util.Date;

/**
 * 概述: 判断验证码dto<br>
 * 背景:<br>
 * Created by Infi on 17/3/9.
 */
public class AccessController implements Serializable {

    private static final long serialVersionUID = -1945867960286889808L;

    private Integer id;
    private String account;
    private String checkCode;
    private Integer actionType;
    private String ip;
    private Date createDate;


    /**
     * 获取ID
     *
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取手机号码
     *
     * @return account 手机号码
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置手机号码
     *
     * @param account 手机号码
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取验证码
     *
     * @return checkCode 验证码
     */
    public String getCheckCode() {
        return checkCode;
    }

    /**
     * 设置验证码
     *
     * @param checkCode 验证码
     */
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    /**
     * 获取动作类型，0-重置密码，1-验证合法性
     *
     * @return actionType 动作类型，0-重置密码，1-验证合法性
     */
    public Integer getActionType() {
        return actionType;
    }

    /**
     * 设置动作类型，0-重置密码，1-验证合法性
     *
     * @param actionType 动作类型，0-重置密码，1-验证合法性
     */
    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    /**
     * 获取IP地址
     *
     * @return ip IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取创建时间
     *
     * @return createDate 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
