package com.yf.pet.entity.firmware;

import java.util.Date;

/**
 * 用户下载固件记录实体类
 * Created by Infi on 17/6/28.
 */
public class UserDownloadLog {

    private Long id;
    private Long userId;
    private String appVersion;
    private Byte status;
    private Date createTime;


    /**
     * 获取主键
     *
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 用户ID
     *
     * @return userId 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置 用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取 APP版本号
     *
     * @return appVersion APP版本号
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * 设置 APP版本号
     *
     * @param appVersion APP版本号
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * 获取 状态1:安装完成
     *
     * @return status 状态1:安装完成
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置 状态1:安装完成
     *
     * @param status 状态1:安装完成
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取 创建时间
     *
     * @return createTime 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
