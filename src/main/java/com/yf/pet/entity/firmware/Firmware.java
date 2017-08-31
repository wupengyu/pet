package com.yf.pet.entity.firmware;

import java.util.Date;

/**
 * 固件信息对象
 * Created by Infi on 17/7/28.
 */
public class Firmware {
    private Long firmwareId;
    private String firmwareType;
    private String firmwareVersion;
    private Long firmwareVersionNumber;
    private Integer releaseType;
    private Integer systemType;
    private Date addTime;
    private String addUser;
    private String content;
    private Integer forceUpdate;
    private String remarks;
    private String idStr;

    /**
     * 获取id字符串形式
     *
     * @return idStr id字符串形式
     */
    public String getIdStr() {
        return idStr;
    }

    /**
     * 设置id字符串形式
     *
     * @param idStr id字符串形式
     */
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    /**
     * 获取主键ID
     *
     * @return firmwareId 主键ID
     */
    public Long getFirmwareId() {
        return firmwareId;
    }

    /**
     * 设置主键ID
     *
     * @param firmwareId 主键ID
     */
    public void setFirmwareId(Long firmwareId) {
        this.firmwareId = firmwareId;
    }

    /**
     * 获取固件类型，比如coros m1，区分大小写
     *
     * @return firmwareType 固件类型，比如coros m1，区分大小写
     */
    public String getFirmwareType() {
        return firmwareType;
    }

    /**
     * 设置固件类型，比如coros m1，区分大小写
     *
     * @param firmwareType 固件类型，比如coros m1，区分大小写
     */
    public void setFirmwareType(String firmwareType) {
        this.firmwareType = firmwareType;
    }

    /**
     * 获取固件版本号，格式：x.x.x.x
     *
     * @return firmwareVersion 固件版本号，格式：x.x.x.x
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * 设置固件版本号，格式：x.x.x.x
     *
     * @param firmwareVersion 固件版本号，格式：x.x.x.x
     */
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * 获取固件版本号，格式：x.x.x.x
     *
     * @return firmwareVersionNumber 固件版本号，格式：x.x.x.x
     */
    public Long getFirmwareVersionNumber() {
        return firmwareVersionNumber;
    }

    /**
     * 设置固件版本号，格式：x.x.x.x
     *
     * @param firmwareVersionNumber 固件版本号，格式：x.x.x.x
     */
    public void setFirmwareVersionNumber(Long firmwareVersionNumber) {
        this.firmwareVersionNumber = firmwareVersionNumber;
    }

    /**
     * 获取版本类型，1：发布版本，2：公测版本，3：内测版本
     *
     * @return releaseType 版本类型，1：发布版本，2：公测版本，3：内测版本
     */
    public Integer getReleaseType() {
        return releaseType;
    }

    /**
     * 设置版本类型，1：发布版本，2：公测版本，3：内测版本
     *
     * @param releaseType 版本类型，1：发布版本，2：公测版本，3：内测版本
     */
    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }

    /**
     * 获取操作系统类型。1：Android，2：iOS
     *
     * @return systemType 操作系统类型。1：Android，2：iOS
     */
    public Integer getSystemType() {
        return systemType;
    }

    /**
     * 设置操作系统类型。1：Android，2：iOS
     *
     * @param systemType 操作系统类型。1：Android，2：iOS
     */
    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    /**
     * 获取添加时间
     *
     * @return addTime 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取操作人，管理员
     *
     * @return addUser 操作人，管理员
     */
    public String getAddUser() {
        return addUser;
    }

    /**
     * 设置操作人，管理员
     *
     * @param addUser 操作人，管理员
     */
    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    /**
     * 获取更新日志说明
     *
     * @return content 更新日志说明
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置更新日志说明
     *
     * @param content 更新日志说明
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取强制更新资源库，0：可选更新, 1：强制更新，
     *
     * @return forceUpdate 强制更新资源库，0：可选更新, 1：强制更新，
     */
    public Integer getForceUpdate() {
        return forceUpdate;
    }

    /**
     * 设置强制更新资源库，0：可选更新, 1：强制更新，
     *
     * @param forceUpdate 强制更新资源库，0：可选更新, 1：强制更新，
     */
    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    /**
     * 获取备注信息
     *
     * @return remarks 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
