package com.yf.pet.entity.firmware;

/**
 * 固件和资源文件信息类
 * Created by Infi on 17/6/29.
 */
public class FirmwareFileObject {


    private String fileUrl;
    private Integer fileMd5;
    private Integer fileSize;
    private String version;
    private Integer type;

    /**
     * 构造方法
     *
     * @param type     文件类型
     * @param version  文件版本号
     * @param fileUrl  文件下载地址
     * @param fileMd5  文件md5校验码
     * @param fileSize 文件大小
     */
    public FirmwareFileObject(Integer type, String version, String fileUrl, Integer fileMd5, Integer fileSize) {
        this.fileUrl = fileUrl;
        this.fileMd5 = fileMd5;
        this.fileSize = fileSize;
        this.version = version;
        this.type = type;
    }

    /**
     * 获取文件类型,1:固件,2:资源
     *
     * @return type 文件类型,1:固件,2:资源
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置文件类型,1:固件,2:资源
     *
     * @param type 文件类型,1:固件,2:资源
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取文件下载路径
     *
     * @return fileUrl 文件下载路径
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 设置文件下载路径
     *
     * @param fileUrl 文件下载路径
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * 获取文件md5校验码
     *
     * @return fileMd5 文件md5校验码
     */
    public Integer getFileMd5() {
        return fileMd5;
    }

    /**
     * 设置文件md5校验码
     *
     * @param fileMd5 文件md5校验码
     */
    public void setFileMd5(Integer fileMd5) {
        this.fileMd5 = fileMd5;
    }

    /**
     * 获取文件大小
     *
     * @return fileSize 文件大小
     */
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     *
     * @param fileSize 文件大小
     */
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 获取文件版本号
     *
     * @return version 文件版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置文件版本号
     *
     * @param version 文件版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
