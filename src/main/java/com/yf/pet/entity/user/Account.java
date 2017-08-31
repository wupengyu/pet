package com.yf.pet.entity.user;

import java.util.Date;

/**
 * 用户实体对象
 * Created by Infi on 17/3/22.
 */
public class Account {

    private Long userId;
    private String mobile;
    private String email;
    private String facebookId;
    private String weixinId;
    //start 20170710 edit by dinghui 新增微信和facebook昵称
    private String weixinName;//微信昵称
    private String facebookName;//facebook昵称
    //end
    private String pwd;
    private String accessToken;
    private Date validityDate;
    private Integer clientType;
    private Integer loginType;
    private String ipAddress;
    private Date createDate;
    private Date updateDate;
    private Integer registerType;
    private Date registerDate;
    private Integer temporaryType;
    private Integer activateStatus;

    /**
     * 获取是否激活，用于邮箱激活功能
     *
     * @return activateStatus 是否激活，用于邮箱激活功能
     */
    public Integer getActivateStatus() {
        return activateStatus;
    }

    /**
     * 设置是否激活，用于邮箱激活功能
     *
     * @param activateStatus 是否激活，用于邮箱激活功能
     */
    public void setActivateStatus(Integer activateStatus) {
        this.activateStatus = activateStatus;
    }


    /**
     * 获取临时帐号类型，1：邮箱地址注册临时帐号，2：绑定邮箱地址未激活
     *
     * @return temporaryType 临时帐号类型，1：邮箱地址注册临时帐号，2：绑定邮箱地址未激活
     */
    public Integer getTemporaryType() {
        return temporaryType;
    }

    /**
     * 设置临时帐号类型，1：邮箱地址注册临时帐号，2：绑定邮箱地址未激活
     *
     * @param temporaryType 临时帐号类型，1：邮箱地址注册临时帐号，2：绑定邮箱地址未激活
     */
    public void setTemporaryType(Integer temporaryType) {
        this.temporaryType = temporaryType;
    }

    /**
     * 获取注册时间
     *
     * @return registerDate 注册时间
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 设置注册时间
     *
     * @param registerDate 注册时间
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 获取用户注册方式，1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     *
     * @return registerType 用户注册方式，1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     */
    public Integer getRegisterType() {
        return registerType;
    }

    /**
     * 设置用户注册方式，1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     *
     * @param registerType 用户注册方式，1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     */
    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }


    /**
     * 获取用户ID
     *
     * @return userId 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取电话号码
     *
     * @return mobile 电话号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置电话号码
     *
     * @param mobile 电话号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取email
     *
     * @return email email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取facebook open userId
     *
     * @return facebookId facebook open userId
     */
    public String getFacebookId() {
        return facebookId;
    }

    /**
     * 设置facebook open userId
     *
     * @param facebookId facebook open userId
     */
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    /**
     * 获取微信open userId
     *
     * @return weixinId 微信open userId
     */
    public String getWeixinId() {
        return weixinId;
    }

    /**
     * 设置微信open userId
     *
     * @param weixinId 微信open userId
     */
    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    /**
     * 获取加密后的密码，
     *
     * @return pwd 加密后的密码，
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置加密后的密码，
     *
     * @param pwd 加密后的密码，
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取登陆成功，返回访问的令牌
     *
     * @return accessToken 登陆成功，返回访问的令牌
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置登陆成功，返回访问的令牌
     *
     * @param accessToken 登陆成功，返回访问的令牌
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取令牌的有效期
     *
     * @return validityDate 令牌的有效期
     */
    public Date getValidityDate() {
        return validityDate;
    }

    /**
     * 设置令牌的有效期
     *
     * @param validityDate 令牌的有效期
     */
    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }


    /**
     * 获取客户端类型。1-Android，2-iOS
     *
     * @return clientType 客户端类型。1-Android，2-iOS
     */
    public Integer getClientType() {
        return clientType;
    }

    /**
     * 设置客户端类型。1-Android，2-iOS
     *
     * @param clientType 客户端类型。1-Android，2-iOS
     */
    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    /**
     * 获取登录类型 1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     *
     * @return loginType 登录类型 1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * 设置登录类型 1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     *
     * @param loginType 登录类型 1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * 获取最后登录的IP地址
     *
     * @return ipAddress 最后登录的IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置最后登录的IP地址
     *
     * @param ipAddress 最后登录的IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取注册日期
     *
     * @return createDate 注册日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置注册日期
     *
     * @param createDate 注册日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新时间
     *
     * @return updateDate 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getWeixinName() {
        return weixinName;
    }

    public void setWeixinName(String weixinName) {
        this.weixinName = weixinName;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }
}
