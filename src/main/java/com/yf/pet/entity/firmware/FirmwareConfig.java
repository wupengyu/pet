package com.yf.pet.entity.firmware;

/**
 * 固件版本配置信息
 * Created by Infi on 17/7/28.
 */
public class FirmwareConfig {
    private Long appVersionNumber;
    private String firmwareVersion;
    private Long firmwareVersionNumber;
    private String firmwareType;
    private Integer releaseType;
    private Integer systemType;
    private Long id;
    private String appVersion;
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
     * 获取APP版本号
     *
     * @return appVersion APP版本号
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * 设置APP版本号
     *
     * @param appVersion APP版本号
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * 获取固件版本配置主键
     *
     * @return id 固件版本配置主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置固件版本配置主键
     *
     * @param id 固件版本配置主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取APP版本号int值，把x.x.x.x版本号转换为64为int，每个x占两个字节
     *
     * @return appVersionNumber APP版本号int值，把x.x.x.x版本号转换为64为int，每个x占两个字节
     */
    public Long getAppVersionNumber() {
        return appVersionNumber;
    }

    /**
     * 设置APP版本号int值，把x.x.x.x版本号转换为64为int，每个x占两个字节
     *
     * @param appVersionNumber APP版本号int值，把x.x.x.x版本号转换为64为int，每个x占两个字节
     */
    public void setAppVersionNumber(Long appVersionNumber) {
        this.appVersionNumber = appVersionNumber;
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
     * 获取固件版本号int值，把x.x.x.x版本号转换为64为int，每个x占两个字节
     *
     * @return firmwareVersionNumber 固件版本号int值，把x.x.x.x版本号转换为64为int，每个x占两个字节
     */
    public Long getFirmwareVersionNumber() {
        return firmwareVersionNumber;
    }

    /**
     * 设置固件版本号int值，把x.x.x.x版本号转换为64为int，每个x占两个字节
     *
     * @param firmwareVersionNumber 固件版本号int值，把x.x.x.x版本号转换为64为int，每个x占两个字节
     */
    public void setFirmwareVersionNumber(Long firmwareVersionNumber) {
        this.firmwareVersionNumber = firmwareVersionNumber;
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
     * 获取发布类型 1:正式版本2:公测版本 3:内侧版本
     *
     * @return releaseType 发布类型 1:正式版本2:公测版本 3:内侧版本
     */
    public Integer getReleaseType() {
        return releaseType;
    }

    /**
     * 设置发布类型 1:正式版本2:公测版本 3:内侧版本
     *
     * @param releaseType 发布类型 1:正式版本2:公测版本 3:内侧版本
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
}
