package com.yf.pet.entity.firmware;

import java.io.Serializable;
import java.util.Date;

/**
 * 固件实体类
 * Created by Infi on 17/6/28.
 */
public class Firmware_old implements Serializable {
    private static final long serialVersionUID = 5483862375692236102L;
    private Long id;
    private String firmwareType;
    private String firmwareVersion;
    private String firmwareUrl;
    private Integer firmwareMd5;
    private Integer firmwareSize;
    private String fontVersion;
    private String fontUrl;
    private Integer fontMd5;
    private Integer fontSize;
    private Date addtime;
    private Integer releaseType;
    private String adduser;
    private Integer systemType;
    private String content;
    private Integer tag;
    private Integer b;
    private Integer s;
    private String appVersion;
    private String md5;
    private String deviceId;
    private String mac;
    private String desiredFirmwareVersion;
    private Integer forceUpdateFont;
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
     * 获取强制更新资源库，1：强制更新，0：可选更新
     *
     * @return forceUpdateFont 强制更新资源库，1：强制更新，0：可选更新
     */
    public Integer getForceUpdateFont() {
        return forceUpdateFont;
    }

    /**
     * 设置强制更新资源库，1：强制更新，0：可选更新
     *
     * @param forceUpdateFont 强制更新资源库，1：强制更新，0：可选更新
     */
    public void setForceUpdateFont(Integer forceUpdateFont) {
        this.forceUpdateFont = forceUpdateFont;
    }

    /**
     * 获取资源信息版本号
     *
     * @return fontVersion 资源信息版本号
     */
    public String getFontVersion() {
        return fontVersion;
    }

    /**
     * 设置资源信息版本号
     *
     * @param fontVersion 资源信息版本号
     */
    public void setFontVersion(String fontVersion) {
        this.fontVersion = fontVersion;
    }

    /**
     * 获取资源文件大小
     *
     * @return fontSize 资源文件大小
     */
    public Integer getFontSize() {
        return fontSize;
    }

    /**
     * 设置资源文件大小
     *
     * @param fontSize 资源文件大小
     */
    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * 获取希望得到的固件版本号
     *
     * @return desiredFirmwareVersion 希望得到的固件版本号
     */
    public String getDesiredFirmwareVersion() {
        return desiredFirmwareVersion;
    }

    /**
     * 设置希望得到的固件版本号
     *
     * @param desiredFirmwareVersion 希望得到的固件版本号
     */
    public void setDesiredFirmwareVersion(String desiredFirmwareVersion) {
        this.desiredFirmwareVersion = desiredFirmwareVersion;
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
     * 获取固件md5校验码,前台传递上来的校验码
     *
     * @return md5 固件md5校验码,前台传递上来的校验码
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 设置固件md5校验码,前台传递上来的校验码
     *
     * @param md5 固件md5校验码,前台传递上来的校验码
     */
    public void setMd5(String md5) {
        this.md5 = md5;
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
     * 获取子版本号
     *
     * @return b 子版本号
     */
    public Integer getB() {
        return b;
    }

    /**
     * 设置子版本号
     *
     * @param b 子版本号
     */
    public void setB(Integer b) {
        this.b = b;
    }

    /**
     * 获取子版本号
     *
     * @return s 子版本号
     */
    public Integer getS() {
        return s;
    }

    /**
     * 设置子版本号
     *
     * @param s 子版本号
     */
    public void setS(Integer s) {
        this.s = s;
    }

    /**
     * 获取ID
     *
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
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
     * 获取固件地址
     *
     * @return firmwareUrl 固件地址
     */
    public String getFirmwareUrl() {
        return firmwareUrl;
    }

    /**
     * 设置固件地址
     *
     * @param firmwareUrl 固件地址
     */
    public void setFirmwareUrl(String firmwareUrl) {
        this.firmwareUrl = firmwareUrl;
    }

    /**
     * 获取固件大小
     *
     * @return firmwareSize 固件大小
     */
    public Integer getFirmwareSize() {
        return firmwareSize;
    }

    /**
     * 设置固件大小
     *
     * @param firmwareSize 固件大小
     */
    public void setFirmwareSize(Integer firmwareSize) {
        this.firmwareSize = firmwareSize;
    }

    /**
     * 获取创建时间
     *
     * @return addtime 创建时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置创建时间
     *
     * @param addtime 创建时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
     * 获取上传管理员
     *
     * @return adduser 上传管理员
     */
    public String getAdduser() {
        return adduser;
    }

    /**
     * 设置上传管理员
     *
     * @param adduser 上传管理员
     */
    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    /**
     * 获取操作系统类型,1:Android,2:IOS
     *
     * @return systemType 操作系统类型,1:Android,2:IOS
     */
    public Integer getSystemType() {
        return systemType;
    }

    /**
     * 设置操作系统类型,1:Android,2:IOS
     *
     * @param systemType 操作系统类型,1:Android,2:IOS
     */
    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    /**
     * 获取md5校验码
     *
     * @return firmwareMd5 md5校验码
     */
    public Integer getFirmwareMd5() {
        return firmwareMd5;
    }

    /**
     * 设置md5校验码
     *
     * @param firmwareMd5 md5校验码
     */
    public void setFirmwareMd5(Integer firmwareMd5) {
        this.firmwareMd5 = firmwareMd5;
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
     * 获取字库下载url
     *
     * @return fontUrl 字库下载url
     */
    public String getFontUrl() {
        return fontUrl;
    }

    /**
     * 设置字库下载url
     *
     * @param fontUrl 字库下载url
     */
    public void setFontUrl(String fontUrl) {
        this.fontUrl = fontUrl;
    }

    /**
     * 获取字库md5
     *
     * @return fontMd5 字库md5
     */
    public Integer getFontMd5() {
        return fontMd5;
    }

    /**
     * 设置字库md5
     *
     * @param fontMd5 字库md5
     */
    public void setFontMd5(Integer fontMd5) {
        this.fontMd5 = fontMd5;
    }

    /**
     * 获取0:可选更新 1:强制更新
     *
     * @return tag 0:可选更新 1:强制更新
     */
    public Integer getTag() {
        return tag;
    }

    /**
     * 设置0:可选更新 1:强制更新
     *
     * @param tag 0:可选更新 1:强制更新
     */
    public void setTag(Integer tag) {
        this.tag = tag;
    }
}
