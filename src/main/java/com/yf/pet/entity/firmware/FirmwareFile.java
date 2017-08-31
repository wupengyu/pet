package com.yf.pet.entity.firmware;

/**
 * 固件包含文件信息
 * Created by Infi on 17/7/28.
 */
public class FirmwareFile {
    private Long firmwareId;
    private Integer type;
    private String fileUrl;
    private Integer fileCRC;
    private Integer fileSize;
    private byte[] fileBytes;
    private String fileName;

    /**
     * 获取文件的byte数组
     *
     * @return fileBytes 文件的byte数组
     */
    public byte[] getFileBytes() {
        return fileBytes;
    }

    /**
     * 设置文件的byte数组
     *
     * @param fileBytes 文件的byte数组
     */
    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    /**
     * 获取文件名称
     *
     * @return fileName 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * 获取固件版本主键ID
     *
     * @return firmwareId 固件版本主键ID
     */
    public Long getFirmwareId() {
        return firmwareId;
    }

    /**
     * 设置固件版本主键ID
     *
     * @param firmwareId 固件版本主键ID
     */
    public void setFirmwareId(Long firmwareId) {
        this.firmwareId = firmwareId;
    }

    /**
     * 获取0：字库，1：字符集转码，2：图片资源，7：固件OTA，15：拉丁字体
     *
     * @return type 0：字库，1：字符集转码，2：图片资源，7：固件OTA，15：拉丁字体
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0：字库，1：字符集转码，2：图片资源，7：固件OTA，15：拉丁字体
     *
     * @param type 0：字库，1：字符集转码，2：图片资源，7：固件OTA，15：拉丁字体
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取文件下载地址
     *
     * @return fileUrl 文件下载地址
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 设置文件下载地址
     *
     * @param fileUrl 文件下载地址
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * 获取文件crc校验码
     *
     * @return fileCRC 文件crc校验码
     */
    public Integer getFileCRC() {
        return fileCRC;
    }

    /**
     * 设置文件crc校验码
     *
     * @param fileCRC 文件crc校验码
     */
    public void setFileCRC(Integer fileCRC) {
        this.fileCRC = fileCRC;
    }

    /**
     * 获取文件大小（单位bytes）
     *
     * @return fileSize 文件大小（单位bytes）
     */
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小（单位bytes）
     *
     * @param fileSize 文件大小（单位bytes）
     */
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
}
