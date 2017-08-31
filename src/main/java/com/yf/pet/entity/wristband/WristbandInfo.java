package com.yf.pet.entity.wristband;

import java.util.Date;

/**
 * 手环绑定、激活、使用信息实体类
 * Created by Infi on 17/6/26.
 */
public class WristbandInfo {
    private Long id;
    private Long userId;
    private Integer clientType;
    private String appVersion;
    private String mobileName;
    private String systemVersion;
    private Integer releaseType;
    private String mac;
    private String deviceId;
    private String algorithmVersion;
    private String firmwareVersion;
    private String firmwareType;
    private String gpsFirmwareVersion;
    private String gpsChipVersion;
    private Long lastSyncTimestamp;
    private String timezone;
    private Date activateTime;
    private Date createDate;
    private Date updateDate;
    private String uuid;


    /**
     * 获取设备的uuid
     *
     * @return uuid 设备的uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置设备的uuid
     *
     * @param uuid 设备的uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
     * 获取客户端系统类型，1：Android，2：iOS
     *
     * @return clientType 客户端系统类型，1：Android，2：iOS
     */
    public Integer getClientType() {
        return clientType;
    }

    /**
     * 设置客户端系统类型，1：Android，2：iOS
     *
     * @param clientType 客户端系统类型，1：Android，2：iOS
     */
    public void setClientType(Integer clientType) {
        this.clientType = clientType;
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
     * 获取手机名称
     *
     * @return mobileName 手机名称
     */
    public String getMobileName() {
        return mobileName;
    }

    /**
     * 设置手机名称
     *
     * @param mobileName 手机名称
     */
    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    /**
     * 获取手机系统版本号
     *
     * @return systemVersion 手机系统版本号
     */
    public String getSystemVersion() {
        return systemVersion;
    }

    /**
     * 设置手机系统版本号
     *
     * @param systemVersion 手机系统版本号
     */
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    /**
     * 获取发布类型 1:正式版本 2:公测版本 3:内侧版本
     *
     * @return releaseType 发布类型 1:正式版本 2:公测版本 3:内侧版本
     */
    public Integer getReleaseType() {
        return releaseType;
    }

    /**
     * 设置发布类型 1:正式版本 2:公测版本 3:内侧版本
     *
     * @param releaseType 发布类型 1:正式版本 2:公测版本 3:内侧版本
     */
    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }

    /**
     * 获取手环的蓝牙mac地址
     *
     * @return mac 手环的蓝牙mac地址
     */
    public String getMac() {
        return mac;
    }

    /**
     * 设置手环的蓝牙mac地址
     *
     * @param mac 手环的蓝牙mac地址
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 获取手环本身的一个唯一的ID号
     *
     * @return deviceId 手环本身的一个唯一的ID号
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置手环本身的一个唯一的ID号
     *
     * @param deviceId 手环本身的一个唯一的ID号
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取算法版本号
     *
     * @return algorithmVersion 算法版本号
     */
    public String getAlgorithmVersion() {
        return algorithmVersion;
    }

    /**
     * 设置算法版本号
     *
     * @param algorithmVersion 算法版本号
     */
    public void setAlgorithmVersion(String algorithmVersion) {
        this.algorithmVersion = algorithmVersion;
    }

    /**
     * 获取固件版本号
     *
     * @return firmwareVersion 固件版本号
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * 设置固件版本号
     *
     * @param firmwareVersion 固件版本号
     */
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * 获取固件设备类型
     *
     * @return firmwareType 固件设备类型
     */
    public String getFirmwareType() {
        return firmwareType;
    }

    /**
     * 设置固件设备类型
     *
     * @param firmwareType 固件设备类型
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
     * 获取固件芯片版本的结构体
     *
     * @return gpsChipVersion 固件芯片版本的结构体
     */
    public String getGpsChipVersion() {
        return gpsChipVersion;
    }

    /**
     * 设置固件芯片版本的结构体
     *
     * @param gpsChipVersion 固件芯片版本的结构体
     */
    public void setGpsChipVersion(String gpsChipVersion) {
        this.gpsChipVersion = gpsChipVersion;
    }

    /**
     * 获取最后一次同步数据的时间戳
     *
     * @return lastSyncTimestamp 最后一次同步数据的时间戳
     */
    public Long getLastSyncTimestamp() {
        return lastSyncTimestamp;
    }

    /**
     * 设置最后一次同步数据的时间戳
     *
     * @param lastSyncTimestamp 最后一次同步数据的时间戳
     */
    public void setLastSyncTimestamp(Long lastSyncTimestamp) {
        this.lastSyncTimestamp = lastSyncTimestamp;
    }

    /**
     * 获取用户当前所在时区
     *
     * @return timezone 用户当前所在时区
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * 设置用户当前所在时区
     *
     * @param timezone 用户当前所在时区
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * 获取激活时间
     *
     * @return activateTime 激活时间
     */
    public Date getActivateTime() {
        return activateTime;
    }

    /**
     * 设置激活时间
     *
     * @param activateTime 激活时间
     */
    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    /**
     * 获取创建时间，绑定时间，开始使用时间
     *
     * @return createDate 创建时间，绑定时间，开始使用时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间，绑定时间，开始使用时间
     *
     * @param createDate 创建时间，绑定时间，开始使用时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
