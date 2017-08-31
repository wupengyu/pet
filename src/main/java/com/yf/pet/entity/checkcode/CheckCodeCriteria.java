package com.yf.pet.entity.checkcode;

import java.io.Serializable;

/**
 * 概述: 验证码查询条件<br>
 * 背景: <br>
 * Created by Infi on 17/3/9.
 */
public class CheckCodeCriteria implements Serializable {

    private static final long serialVersionUID = -7862307555472873747L;
    private String mobile;
    private Integer actionType;
    private String checkCode;
    private String appKey;
    private String ipAddress;
    private String account;
    private String email;
    private Integer accountType;


    /**
     * 获取帐号，电话号码、邮箱地址、Facebook帐号ID，微信帐号ID
     *
     * @return account 帐号，电话号码、邮箱地址、Facebook帐号ID，微信帐号ID
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置帐号，电话号码、邮箱地址、Facebook帐号ID，微信帐号ID
     *
     * @param account 帐号，电话号码、邮箱地址、Facebook帐号ID，微信帐号ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取帐号类型，1：电话号码、2：邮箱地址、3：Facebook帐号ID，4：微信帐号ID
     *
     * @return accountType 帐号类型，1：电话号码、2：邮箱地址、3：Facebook帐号ID，4：微信帐号ID
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 设置帐号类型，1：电话号码、2：邮箱地址、3：Facebook帐号ID，4：微信帐号ID
     *
     * @param accountType 帐号类型，1：电话号码、2：邮箱地址、3：Facebook帐号ID，4：微信帐号ID
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }


    /**
     * 获取邮箱地址
     *
     * @return email 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱地址
     *
     * @param email 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户IP地址
     *
     * @return ipAddress 用户IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置用户IP地址
     *
     * @param ipAddress 用户IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取电话号码加密密钥
     *
     * @return appKey 电话号码加密密钥
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 设置电话号码加密密钥
     *
     * @param appKey 电话号码加密密钥
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
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
     * 获取1-表示注册短信 2-表示密码重置短信
     *
     * @return actionType 1-表示注册短信 2-表示密码重置短信
     */
    public Integer getActionType() {
        return actionType;
    }

    /**
     * 设置1-表示注册短信 2-表示密码重置短信
     *
     * @param actionType 1-表示注册短信 2-表示密码重置短信
     */
    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    /**
     * 获取调用远程IP地址
     *
     * @return mobile 调用远程IP地址
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置调用远程IP地址
     *
     * @param mobile 调用远程IP地址
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
