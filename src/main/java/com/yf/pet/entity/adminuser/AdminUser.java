package com.yf.pet.entity.adminuser;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类
 * Created by Infi on 17/6/28.
 */
public class AdminUser implements Serializable {
    private static final long serialVersionUID = -8193009038462604309L;
    private Long id;
    private String account;
    private String pwd;
    private String name;
    private String role;
    private String notes;
    private String roleName;
    private Date createDate;
    private String accessToken;
    private Date validityDate;

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
     * 获取管理员ID
     *
     * @return id 管理员ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置管理员ID
     *
     * @param id 管理员ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取管理员帐号
     *
     * @return account 管理员帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置管理员帐号
     *
     * @param account 管理员帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取管理员密码
     *
     * @return pwd 管理员密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置管理员密码
     *
     * @param pwd 管理员密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取管理员名词
     *
     * @return name 管理员名词
     */
    public String getName() {
        return name;
    }

    /**
     * 设置管理员名词
     *
     * @param name 管理员名词
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色
     *
     * @return role 角色
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置角色
     *
     * @param role 角色
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取说明
     *
     * @return notes 说明
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 设置说明
     *
     * @param notes 说明
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 获取角色名称
     *
     * @return roleName 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
