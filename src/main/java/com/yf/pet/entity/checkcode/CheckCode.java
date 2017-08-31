package com.yf.pet.entity.checkcode;

import java.io.Serializable;
import java.util.Date;

/**
 * 概述: 验证码DTO实体<br>
 * 背景: <br>
 * Created by Infi on 17/3/9.
 */
public class CheckCode implements Serializable {

    private static final long serialVersionUID = -8619649336163420301L;
    private Integer id;
    private String account;
    private String checkCode;
    private Date createDate;
    private Integer type;
    private Integer valid;
    private Long userId;

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
     * 获取有效，1：表示有效，0：表示无效
     *
     * @return valid 有效，1：表示有效，0：表示无效
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * 设置有效，1：表示有效，0：表示无效
     *
     * @param valid 有效，1：表示有效，0：表示无效
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    /**
     * 获取验证码类型，1: 手机号码注册，2：邮箱地址激活，3：重置密码（手机短信或邮件）
     *
     * @return type 验证码类型，1: 手机号码注册，2：邮箱地址激活，3：重置密码（手机短信或邮件）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置验证码类型，1: 手机号码注册，2：邮箱地址激活，3：重置密码（手机短信或邮件）
     *
     * @param type 验证码类型，1: 手机号码注册，2：邮箱地址激活，3：重置密码（手机短信或邮件）
     */
    public void setType(Integer type) {
        this.type = type;
    }

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
