package com.yf.pet.entity.firmware;

import java.util.List;

/**
 * 固件信息返回类
 * Created by Infi on 17/6/29.
 */
public class FirmwareVO {

    private List<FirmwareFile> files;
    private String firmwareType;
    private String content;
    private String version;
    private Integer forceUpdate;

    /**
     *获取固件版本号，固件和资源文件版本号一致
     *@return version 固件版本号，固件和资源文件版本号一致
     */
    public String getVersion() {
        return version;
    }

    /**
     *设置固件版本号，固件和资源文件版本号一致
     *@param version 固件版本号，固件和资源文件版本号一致
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *获取固件是否强制更新，0:可选更新 1:强制更新
     *@return forceUpdate 固件是否强制更新，0:可选更新 1:强制更新
     */
    public Integer getForceUpdate() {
        return forceUpdate;
    }

    /**
     *设置固件是否强制更新，0:可选更新 1:强制更新
     *@param forceUpdate 固件是否强制更新，0:可选更新 1:强制更新
     */
    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }
    /**
     * 获取文件信息列表
     *
     * @return files 文件信息列表
     */
    public List<FirmwareFile> getFiles() {
        return files;
    }

    /**
     * 设置文件信息列表
     *
     * @param files 文件信息列表
     */
    public void setFiles(List<FirmwareFile> files) {
        this.files = files;
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
     * 获取固件说明
     *
     * @return content 固件说明
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置固件说明
     *
     * @param content 固件说明
     */
    public void setContent(String content) {
        this.content = content;
    }

}
