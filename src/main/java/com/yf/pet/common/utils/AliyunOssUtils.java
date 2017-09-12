/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.utils;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.yf.pet.common.ApplicationConstants;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;

/**
 * OSS帮助类
 *
 * @author dinghui
 */
@Component
public class AliyunOssUtils {

    private static Logger logger = LoggerFactory.getLogger(AliyunOssUtils.class);
    private OSSClient ossClient = null;
    // 配置文件路径
    public static final String CINFIG_PATH = ApplicationConstants.CONFIG_PATH;
    public static final String ENDPOINT = YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.endpoint");
    private static final String INTERNAL_ENDPOINT = YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.internal.endpoint");
    public static final String ACCESSKEYID = YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.accessKeyId");
    public static final String ACCESSKEYSECRET = YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.accessKeySecret");
    public static final String MODE = YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.mode");
    public static final String OSS_IMG_ENDPOINT = YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.img.endpoint");
    // buket名称前缀
    public static final String BUCKET_PREFIX = YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.bucket.prefix");
    public static final Boolean LOCAL_WRITE = Boolean.valueOf(YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.local.write"));
    public static final String IMG_FORMAT = YFResourceUtil.getValueByKey(CINFIG_PATH, "oss.img.format");

    public OSSClient getClient() {
        if (this.ossClient == null) {
            long startTime = new Date().getTime();
            this.ossClient = new OSSClient(INTERNAL_ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
            logger.warn("创建ossClient (ms)：" + YFDateUtil.spendTime(startTime));
        }
        return ossClient;
    }

    static {
        //checkBucket();
    }

    public static void checkBucket() {
        OSSClient client = new OSSClient(INTERNAL_ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        // 列举bucket
        List<Bucket> buckets = client.listBuckets();
        EnumSet<YFEnumFileKey> enumSet = EnumSet.allOf(YFEnumFileKey.class);
        Set<String> buketSet = new HashSet<String>();

        for (Bucket bucket : buckets) {
            buketSet.add(bucket.getName());
        }
        //判断当前服务器OSS 是否存在需要的buket，如果不存在，则创建该buket
        for (YFEnumFileKey fileKey : enumSet) {
            if (!buketSet.contains(fileKey.getBucketName())) {
                logger.warn("创建Bucket：" + fileKey.getBucketName());
                client.createBucket(fileKey.getBucketName());
                buketSet.add(fileKey.getBucketName());
            }
        }
        client.shutdown();
    }

    /**
     * 文件存储方法
     *
     * @param inputStream:文件流
     * @param bucketName:阿里云存储单元bucket名称
     * @param folderStr:自定义目录
     * @param fileExtension:文件扩展名
     * @param contentLength:文件大小
     * @return 文件名称
     * @throws Exception:抛出异常
     */
    public String putObject(InputStream inputStream, String bucketName, String fileName, String folderPath,
                            long contentLength) throws Exception {
        return putObject(inputStream, bucketName, fileName, folderPath, contentLength, null);
    }

    /**
     * 文件存储方法
     *
     * @param inputStream:文件流
     * @param bucketName:阿里云存储单元bucket名称
     * @param folderStr:自定义目录
     * @param fileExtension:文件扩展名
     * @param contentLength:文件大小
     * @param contentType:文件类型，比如text/plain
     * @return 文件名称
     * @throws Exception:抛出异常
     */
    @SuppressWarnings("unused")
    public String putObject(InputStream inputStream, String bucketName, String fileName, String folderPath,
                            long contentLength, String contentType) throws Exception {
        // 1. 如果文件流为空就返回空
        if (inputStream == null) {
            throw new Exception("OSS inputStream 为空");
        }
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(contentLength);
        // 设置文件类型，比如text/plain
        if (!StringUtils.isBlank(contentType)) {
            meta.setContentType(contentType);
        }
        // 文件名
        // String fileName = CodeGenerator.getCodeString(30);
        String ossFileName = fileName;
        if (!YFTools.isEmpty(folderPath)) {
            ossFileName = YFTools.trim(folderPath, "/") + "/" + ossFileName;
        }
        ossFileName = ossFileName.replace("//", "/");
        // 上传Object.
        getClient().putObject(bucketName, ossFileName, inputStream, meta);
        return ossFileName;
    }

    /**
     * 删除OSS上的文件
     *
     * @param bucketName
     * @param fileName
     * @param folderPath
     */
    public void deleteObject(String bucketName, String fileName, String folderPath) {
        String ossFileName = fileName;
        if (!YFTools.isEmpty(folderPath)) {
            ossFileName = folderPath + "/" + ossFileName;
        }
        ossFileName = ossFileName.replace("//", "/");
        getClient().deleteObject(bucketName, ossFileName);
    }


    /**
     * 将保存在oss中的json字符串转成成对象.<br>
     * 把[{"sleepType":1,"offset":0,"length":38},{"sleepType":0,"offset":38,
     * "length":8}]转化成List<SleepCurve>对象
     *
     * @param bucketName:backet名称
     * @param key:oss对象key值
     * @param clazz:json数据对于的实体对象
     * @return 实体列表
     * @throws Exception:抛出异常
     */
    public <T> List<T> getObjectToList(String bucketName, String key, Class<T> clazz) throws Exception {
        // 获取Object，返回结果为OSSObject对象
        OSSObject object = ossClient.getObject(bucketName, key);
        // 获取Object Metadata
        // ObjectMetadata metadata = object.getObjectMetadata();
        // 获取Object的输入流
        InputStream objectContent = object.getObjectContent();
        // 处理Object,转化成string
        String objectStr = IOUtils.toString(objectContent);
        // 关闭流，请注意，需要显式关闭，否则会造成资源泄露。
        objectContent.close();
        return JSON.parseArray(objectStr, clazz);
    }

    public List<String> listObjects(String bucketName, int pageSize, int pageCount) {
        final int maxKeys = pageSize;
        String nextMarker = null;
        ObjectListing objectListing;
        List<String> keyList = new ArrayList<String>();
        int i = 0;
        do {
            objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withMarker(nextMarker).withMaxKeys(maxKeys));

            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                keyList.add(s.getKey());
            }
            nextMarker = objectListing.getNextMarker();
            i++;
        } while (objectListing.isTruncated() && (pageCount > 0 ? i < pageCount : true));
        return keyList;
    }



}
