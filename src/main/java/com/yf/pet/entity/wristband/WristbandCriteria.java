package com.yf.pet.entity.wristband;

import java.util.List;

/**
 * 手环绑定、激活、使用信息实体类
 * Created by Infi on 17/6/26.
 */
public class WristbandCriteria {
    private Long userId;
    private Integer clientType;
    private String appVersion;
    private String mobileName;
    private String systemVersion;
    private Integer releaseType;
    private String timezone;
    private List<WristbandInfo> devices;

    /**
     * 获取设备列表
     *
     * @return devices 设备列表
     */
    public List<WristbandInfo> getDevices() {
        return devices;
    }

    /**
     * 设置设备列表
     *
     * @param devices 设备列表
     */
    public void setDevices(List<WristbandInfo> devices) {
        this.devices = devices;
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

}
