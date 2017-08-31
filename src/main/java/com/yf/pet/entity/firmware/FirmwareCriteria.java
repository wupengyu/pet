package com.yf.pet.entity.firmware;

/**
 * 固件条件类,查询或新增固件用到
 * Created by Infi on 17/6/28.
 */
public class FirmwareCriteria {
    private String firmwareType;
    private String firmwareVersion;
    private Integer releaseType;
    private Integer systemType;
    private String appVersion;
    private String deviceId;
    private String mac;
    private String uuid;

    /**
     * 获取手环的UUID
     *
     * @return uuid 手环的UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置手环的UUID
     *
     * @param uuid 手环的UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取设备mac
     *
     * @return mac 设备mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * 设置设备mac
     *
     * @param mac 设备mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 获取设备ID
     *
     * @return deviceId 设备ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备ID
     *
     * @param deviceId 设备ID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
     * 获取固件类型
     *
     * @return firmwareType 固件类型
     */
    public String getFirmwareType() {
        return firmwareType;
    }

    /**
     * 设置固件类型
     *
     * @param firmwareType 固件类型
     */
    public void setFirmwareType(String firmwareType) {
        this.firmwareType = firmwareType;
    }

    /**
     * 获取固件版本
     *
     * @return firmwareVersion 固件版本
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * 设置固件版本
     *
     * @param firmwareVersion 固件版本
     */
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }


    /**
     * 获取release类型
     *
     * @return releaseType release类型
     */
    public Integer getReleaseType() {
        return releaseType;
    }

    /**
     * 设置release类型
     *
     * @param releaseType release类型
     */
    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }


    /**
     * 获取操作系统类型
     *
     * @return systemType 操作系统类型
     */
    public Integer getSystemType() {
        return systemType;
    }

    /**
     * 设置操作系统类型
     *
     * @param systemType 操作系统类型
     */
    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

}
