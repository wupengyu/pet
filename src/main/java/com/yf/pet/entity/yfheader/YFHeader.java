package com.yf.pet.entity.yfheader;

import java.util.Map;

/**
 * http请求头部信息
 * Created by Infi on 17/4/7.
 */
public class YFHeader {

    private String appVersion;
    private Integer clientType;
    private String mobileName;
    private String systemVersion;
    private Integer timezone;
    private Integer releaseType;
    private Map<String, DeviceInfo> devices;


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
     * 获取手机型号名称
     *
     * @return mobileName 手机型号名称
     */
    public String getMobileName() {
        return mobileName;
    }

    /**
     * 设置手机型号名称
     *
     * @param mobileName 手机型号名称
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
     * 获取用户调用接口是所在时区，时区范围在正负48之间
     *
     * @return timezone 用户调用接口是所在时区，时区范围在正负48之间
     */
    public Integer getTimezone() {
        return timezone;
    }

    /**
     * 设置用户调用接口是所在时区，时区范围在正负48之间
     *
     * @param timezone 用户调用接口是所在时区，时区范围在正负48之间
     */
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    /**
     * 获取发布类型 1:正式版本 2:公测版本 3:内侧版本
     *
     * @return releaseType 发布类型 1:正式版本 2:公测版本 3:内侧版本
     */
    public Integer getReleaseType() {
        return releaseType;
    }

    /**
     * 设置发布类型 1:正式版本 2:公测版本 3:内侧版本
     *
     * @param releaseType 发布类型 1:正式版本 2:公测版本 3:内侧版本
     */
    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }

    /**
     * 获取
     *
     * @return devices
     */
    public Map<String, DeviceInfo> getDevices() {
        return devices;
    }

    /**
     * 设置
     *
     * @param devices
     */
    public void setDevices(Map<String, DeviceInfo> devices) {
        this.devices = devices;
    }

}
