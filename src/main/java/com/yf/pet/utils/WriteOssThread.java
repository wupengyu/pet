/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * TODO
 *
 * @author Administrator
 */
public class WriteOssThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(WriteOssThread.class);
    private YFEnumFileKey fileKey;
    private File file;
    private String fileName;
    private String parentPath;
    private AliyunOssUtils aliyunOssUtils;

    public WriteOssThread(AliyunOssUtils aliyunOssUtils, YFEnumFileKey fileKey, File file, String fileName, String parentPath) {
        this.aliyunOssUtils = aliyunOssUtils;
        this.fileKey = fileKey;
        this.file = file;
        this.fileName = fileName;
        this.parentPath = parentPath;
    }

    public void run() {
//        try {
//            if (YFTools.isEmpty(aliyunOssUtils)) {
//                aliyunOssUtils = new AliyunOssUtils();
//            }
//            long startTime = new Date().getTime();
//            parentPath = fileKey.getOssParentPath(parentPath);
//            // 默认为下载模式
//            String contentType = "application/octet-stream";
//            if (fileKey == YFEnumFileKey.HEAD_PIC) {
//                MagicMatch match = Magic.getMagicMatch(file, false, true);
//                contentType = match.getMimeType();
//                System.out.println("上传图片，获取contentType：" + contentType + "，(ms):--" + YFDateUtil.spendTime(startTime));
//                logger.info("上传图片，获取contentType：" + contentType + "，(ms):--" + YFDateUtil.spendTime(startTime));
//            }
//            startTime = new Date().getTime();
//            InputStream inputStream = new BufferedInputStream(new FileInputStream(this.file));
//            String ossPath = fileKey.getBucketName() + "/" + parentPath + "/" + fileName;
//            logger.info("开始写入OSS(byte):" + ossPath);
//            int size = inputStream.available();
//            aliyunOssUtils.putObject(inputStream, fileKey.getBucketName(), fileName, parentPath, size, contentType);
//            logger.info("OSS写入完毕,文件大小(byte)：" + size + " ，(ms)：" + YFDateUtil.spendTime(startTime));
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
    }
}
