package com.yf.pet.common.yfheader;

/**
 * http请求头部信息-固件信息
 * Created by Infi on 17/4/7.
 */
public class DeviceInfo {

    private String algorithmVersion;
    private String mac;
    private String deviceId;
    private String firmwareVersion;
    private String firmwareType;
    private String gpsFirmwareVersion;
    private Long lastSyncTimestamp;


    /**
     * 获取设备ID
     *
     * @return algorithmVersion 设备ID
     */
    public String getAlgorithmVersion() {
        return algorithmVersion;
    }

    /**
     * 设置设备ID
     *
     * @param algorithmVersion 设备ID
     */
    public void setAlgorithmVersion(String algorithmVersion) {
        this.algorithmVersion = algorithmVersion;
    }

    /**
     * 获取12位mac
     *
     * @return mac 12位mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * 设置12位mac
     *
     * @param mac 12位mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 获取6位蓝牙设备号，如A6BCDE
     *
     * @return deviceId 6位蓝牙设备号，如A6BCDE
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置6位蓝牙设备号，如A6BCDE
     *
     * @param deviceId 6位蓝牙设备号，如A6BCDE
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
     * 获取设备类型
     *
     * @return firmwareType 设备类型
     */
    public String getFirmwareType() {
        return firmwareType;
    }

    /**
     * 设置设备类型
     *
     * @param firmwareType 设备类型
     */
    public void setFirmwareType(String firmwareType) {
        this.firmwareType = firmwareType;
    }

    /**
     * 获取gps固件版本号
     *
     * @return gpsFirmwareVersion gps固件版本号
     */
    public String getGpsFirmwareVersion() {
        return gpsFirmwareVersion;
    }

    /**
     * 设置gps固件版本号
     *
     * @param gpsFirmwareVersion gps固件版本号
     */
    public void setGpsFirmwareVersion(String gpsFirmwareVersion) {
        this.gpsFirmwareVersion = gpsFirmwareVersion;
    }

    /**
     * 获取固件与APP最后同步的时间，秒时间戳
     *
     * @return lastSyncTimestamp 固件与APP最后同步的时间，秒时间戳
     */
    public Long getLastSyncTimestamp() {
        return lastSyncTimestamp;
    }

    /**
     * 设置固件与APP最后同步的时间，秒时间戳
     *
     * @param lastSyncTimestamp 固件与APP最后同步的时间，秒时间戳
     */
    public void setLastSyncTimestamp(Long lastSyncTimestamp) {
        this.lastSyncTimestamp = lastSyncTimestamp;
    }
}
