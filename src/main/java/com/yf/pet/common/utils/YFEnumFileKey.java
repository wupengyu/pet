/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.utils;

import com.yf.pet.common.ApplicationConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

/**
 * EnumFileKey:文件类型枚举类
 *
 * @author dinghui 2016-05-30
 */
public enum YFEnumFileKey {
    // OSS单用户最多能创建10个BUKET，为节省BUKET，部分类似文件放在同一BUKET下
    /**
     * 原始数据，二进制流
     */
    LABEL_ORI("labelorifile", "rawdata"), // 标签数据,二进制流

    /**
     * 原始数据:运动轨迹缩率图,二进制流
     */
    LABEL_IMAGEDATA("imagedata", "rawdata"), // 标签数据,二进制流

    /**
     * 原始数据:运动数据,二进制流
     */
    LABEL_SPORTDATA("sportdata", "rawdata"), // 标签数据,二进制流
    /**
     * 运动轨迹数据，二进制流
     */
    LABEL_LOCUS("labellocusfile", "rawdata"), // 运动轨迹数据，二进制流

    /**
     * 动态心率数据，二进制流
     */
    LABEL_HEART("labelheartfile", "rawdata"), // 运动轨迹数据，二进制流
    /**
     * 运动轨迹缩率图,保存在oss的labellocusimage文件夹，png图片
     */
    LABEL_LOCUS_IMAGE("locusimage", "locusimage"),
    /**
     * 运动轨迹数据，二进制流
     */
    MINUTES("minutesDate", "rawdata"), // 分钟数据，二进制流
    /**
     * 固件包,APP或手环用到
     */
    FIRMWARE("firmware", "firmware"), // APP固件，bin文件
    /**
     * 表盘固件，bin文件
     */
    WATCH_FACE("watchface", "firmware"), // 表盘固件，bin文件
    /**
     * 骚扰数据，zip压缩包
     */
    CALL_HARESS("callharess", "firmware"), // 骚扰数据，zip压缩包
    /**
     * 用户头像，png图片
     */
    HEAD_PIC("headpic", "headpic"),// 用户头像，png图片
    /**
     * 问题反馈日志
     */
    FEEDBACK_FILE("feedback", "feedback");// 用户头像，png图片


    // 配置文件路径
    private static final String CINFIG_PATH = ApplicationConstants.CONFIG_PATH;


    // 成员变量
    private String name;// 这里的name可以看作根目录
    private String dataType;
    private String bucketName;
    private String upLoadPath;// 文件上传根目录
    private String httpRoot;

    public String getName() {
        return name;
    }

    public String getDataType() {
        return dataType;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getUpLoadPath() {
        return upLoadPath;
    }

    public String getHttpRoot() {
        return httpRoot;
    }

    private String contentType = "application/octet-stream";

    public final static String FILE_LOCAL = "local";
    public final static String FILE_OSS = "oss";

    // 构造方法
    private YFEnumFileKey(String name, String dataType) {
        this(name, dataType, "application/octet-stream");

    }

    // 构造方法
    private YFEnumFileKey(String name, String dataType, String contentType) {
        this.name = name;
        this.bucketName = AliyunOssUtils.BUCKET_PREFIX + dataType;
        this.dataType = dataType;
        this.contentType = contentType;
        this.upLoadPath = YFResourceUtil.getValueByKey(CINFIG_PATH, dataType + ".path");
        this.httpRoot = YFResourceUtil.getValueByKey(CINFIG_PATH, dataType + ".http");
    }


    public String getLocalPath() {
        return getLocalPath("");
    }

    /**
     * 本地路径
     *
     * @param parentPath：父目录
     * @return
     */
    public String getLocalPath(String parentPath) {
        String filePath = YFTools.trimEnd(this.getUpLoadPath(), "/") + File.separator + getName() + File.separator;
        //需要创建父目录的文件类型
        EnumSet<YFEnumFileKey> sets = EnumSet.of(YFEnumFileKey.LABEL_ORI, YFEnumFileKey.LABEL_LOCUS, YFEnumFileKey.LABEL_HEART);
        if (!YFTools.isEmpty(parentPath)) {
            if (sets.contains(this)) {
                int subIndex = parentPath.length();
                if (parentPath.length() > 7) {
                    subIndex = parentPath.length() < 12 ? 4 : 7;
                }
                filePath += YFTools.trim(parentPath.substring(0, subIndex), "/") + File.separator;
            }
            filePath += YFTools.trim(parentPath, "/") + File.separator;
        }
        return filePath;
    }

    /**
     * @return LOCAL/OSS
     */
    private String getFileSaveType() {
        EnumSet<YFEnumFileKey> set = EnumSet.of(YFEnumFileKey.LABEL_ORI, YFEnumFileKey.LABEL_LOCUS, YFEnumFileKey.MINUTES);
        return set.contains(this) ? FILE_LOCAL : FILE_OSS;
    }

    /**
     * 文件是否存在本地
     *
     * @return
     */
    public boolean isSaveOnLocal() {
        return FILE_LOCAL.equals(getFileSaveType());
    }

    /**
     * 文件是否存在阿里云OSS
     *
     * @return
     */
    public boolean isSaveOnOss() {
        return FILE_OSS.equals(getFileSaveType());
    }

    /**
     * 获取http访问目录，尾部已经包含"/"
     *
     * @return
     */
    public String getHttpPath() {
        return YFTools.trim(this.httpRoot, "/") + "/" + getName() + "/";
        // if (FILE_LOCAL.equals(this.getFileSaveType()))
        // return YFTools.sideTrim(ApplicationConstants.FILE_HTTP_URL, "/") + "/"
        // + getName() + "/";
        // return getOssHttpPath("") + "/";
    }

    public String getOssParentPath(String parentPath) {
        if (YFTools.isEmpty(parentPath))
            return this.getName();
        // 结尾不用斜线
        return this.getName() + "/" + YFTools.trim(parentPath, "/");
    }

    // 获取OSS 文件访问地址
    public String getOssHttpPath(String parentPath) {
        String channel = "http://" + this.getBucketName() + ".";
        // 判断是否是图片http://
        // 域名规则：http://channel.<endpoint>/object@format
        String endpoint = this == YFEnumFileKey.HEAD_PIC ? AliyunOssUtils.OSS_IMG_ENDPOINT : AliyunOssUtils.ENDPOINT;
        endpoint = endpoint.replace("http://", "").replace("/", "");
        String httpRoot = channel + endpoint + "/" + getOssParentPath(parentPath);
        return YFTools.trim(httpRoot, "/");
    }

    public static void main(String[] args) {
        // File file = new File("D:/appCache/java.jpg");
        // System.out.println(file.getPath());
        // printContentType("D:/appCache/java.bin");
        // printContentType(file.getPath());
        // printContentType("D:/appCache/java.png");
        // printContentType("D:/appCache/java.zip");
        // printContentType("D:/appCache/java");

        String parentPath = "////aaaa/////";
        String teString = YFEnumFileKey.CALL_HARESS.getHttpPath() + "/" + "aaaa.zip";

        parentPath = YFTools.trim(parentPath, "/");
        System.out.println(parentPath);
        System.out.println(teString);

    }

    private static void printContentType(String pathToFile) {
        Path path = Paths.get(pathToFile);
        String contentType = null;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pathToFile + " content type is : " + contentType);

    }
}
