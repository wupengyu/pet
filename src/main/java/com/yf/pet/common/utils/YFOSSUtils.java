/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.utils;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.OSSObject;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.exception.YFException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * OSS帮助类
 * 
 * @author infi.wang
 */
public class YFOSSUtils {

	private YFOSSUtils() {
	}

	/**
	 * 
	 * 文件存储方法
	 *
	 * @param accessKeyId 阿里云accessKeyId
	 * @param accessKeySecret 阿里云accessKeySecret
	 * @param endpoint 阿里云 endpoint
	 * @param inputStream 文件流
	 * @param bucketName 阿里云存储单元bucket名称
	 * @param folderStr 自定义目录
	 * @param fileExtension 文件扩展名
	 * @param contentLength 文件大小
	 * @return 文件名称
	 * @throws IOException 抛出io异常
	 */
	@SuppressWarnings("unused")
	public static String PutObject(String accessKeyId, String accessKeySecret, String endpoint, InputStream inputStream, String bucketName,
			String folderStr, String fileExtension, long contentLength) throws IOException {
		// 1. 如果文件流为空就返回空
		if (inputStream == null) {
			throw new YFException(ReturnMessageEnum.PARAMETER_NULL);
		}
		// 2. 初始化ossclient
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(contentLength);
		// meta.setContentType(contentType);
		// 文件名
		String fileName = getInstance32Bit();
		String ossFileName = folderStr + fileName;
		if (!StringUtils.isEmpty(fileExtension)) {
			ossFileName = ossFileName + fileExtension;
		}

		// 上传Object.
		Object object = ossClient.putObject(bucketName, ossFileName, inputStream, meta);
		return ossFileName;
	}

	/**
	 * 
	 * 文件存储方法
	 *
	 * @param accessKeyId 阿里云accessKeyId
	 * @param accessKeySecret 阿里云accessKeySecret
	 * @param endpoint 阿里云 endpoint
	 * @param inputStream 文件流
	 * @param bucketName 阿里云存储单元bucket名称
	 * @param folderStr 自定义目录
	 * @param fileExtension 文件扩展名
	 * @param contentLength 文件大小
	 * @param contentType 文件类型，比如text/plain
	 * @return 文件名称
	 * @throws IOException 抛出io异常
	 */
	public static String PutObject(String accessKeyId, String accessKeySecret, String endpoint, InputStream inputStream, String bucketName,
			String folderStr, String fileExtension, long contentLength, String contentType) throws IOException {
		// 1. 如果文件流为空就返回空
		if (inputStream == null) {
			throw new YFException(ReturnMessageEnum.PARAMETER_NULL);
		}
		// 2. 初始化ossclient
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(contentLength);
		// 设置文件类型，比如text/plain
		if (StringUtils.isNotBlank(contentType)) {
			meta.setContentType(contentType);
		}
		// 文件名
		String fileName = getInstance32Bit();
		String ossFileName = folderStr + fileName;
		if (StringUtils.isNotBlank(fileExtension)) {
			ossFileName = ossFileName + fileExtension;
		}

		// 上传Object.
		Object object = ossClient.putObject(bucketName, ossFileName, inputStream, meta);
		return ossFileName;
	}

	/**
	 *
	 * 文件存储方法
	 *
	 * @param accessKeyId 阿里云accessKeyId
	 * @param accessKeySecret 阿里云accessKeySecret
	 * @param endpoint 阿里云 endpoint
	 * @param inputStream 文件流
	 * @param bucketName 阿里云存储单元bucket名称
	 * @param folderStr 自定义目录
	 * @param fileName 文件名称
	 * @param fileExtension 文件扩展名
	 * @param contentLength 文件大小
	 * @param contentType 文件类型，比如text/plain
	 * @return 文件名称
	 * @throws IOException 抛出io异常
	 */
	public static String PutObject(String accessKeyId, String accessKeySecret, String endpoint, InputStream inputStream, String bucketName,
								   String folderStr, String fileName,String fileExtension, long contentLength, String contentType) throws IOException {
		// 1. 如果文件流为空就返回空
		if (inputStream == null) {
			throw new YFException(ReturnMessageEnum.PARAMETER_NULL);
		}
		// 2. 初始化ossclient
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(contentLength);
		// 设置文件类型，比如text/plain
		if (StringUtils.isNotBlank(contentType)) {
			meta.setContentType(contentType);
		}
		// 文件名
		String ossFileName = folderStr + fileName;
		if (StringUtils.isNotBlank(fileExtension)) {
			ossFileName = ossFileName + fileExtension;
		}

		// 上传Object.
		Object object = ossClient.putObject(bucketName, ossFileName, inputStream, meta);
		return ossFileName;
	}

	/**
	 * 
	 * 从阿里云获取文件
	 * 
	 * @param accessKeyId oss key
	 * @param accessKeySecret oss keysecret
	 * @param endpoint endpoint
	 * @param bucketName backet名称
	 * @param key oss 对象key值
	 * @return 文件流对象
	 * @throws IOException 抛出异常
	 */
	public static InputStream getObject(String accessKeyId, String accessKeySecret, String endpoint, String bucketName, String key)
			throws IOException {
		// 1. 如果key为空，就返回空对象
		if (StringUtils.isBlank(key)) {
			return null;
		}
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 获取Object，返回结果为OSSObject对象
		OSSObject object = ossClient.getObject(bucketName, key);
		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();
		return objectContent;
	}

	/**
	 * 
	 * 将保存在oss中的json字符串转成成对象.<br>
	 * 把[{"sleepType":1,"offset":0,"length":38},{"sleepType":0,"offset":38,"length":8}]转化成List<SleepCurve>对象
	 * 
	 * @param accessKeyId oss key
	 * @param accessKeySecret oss keysecret
	 * @param endpoint endpoint
	 * @param bucketName backet名称
	 * @param key oss 对象key值
	 * @param clazz json数据对于的实体对象
	 * @return 实体列表
	 * @throws IOException 抛出一场
	 */
	public static <T> List<T> getObjectToList(String accessKeyId, String accessKeySecret, String endpoint, String bucketName, String key,
			Class<T> clazz) throws IOException {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
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

	/**
	 * 
	 * 删除对象
	 *
	 * @param accessKeyId 阿里云accessKeyId
	 * @param accessKeySecret 阿里云accessKeySecret
	 * @param endpoint 阿里云 endpoint
	 * @param bucketName 阿里云存储单元bucket名称
	 * @param key 对象key
	 * @throws IOException 抛出io异常
	 */
	public static void deleteObject(String accessKeyId, String accessKeySecret, String endpoint, String bucketName, String key) throws IOException {
		// 1. 主键为空就不做删除操作
		if (StringUtils.isBlank(key)) {
			return;
		}
		// 2. 初始化ossclient
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		// 3. 删除Object.
		ossClient.deleteObject(bucketName, key);
	}

	/**
	 * 
	 * 保存byte形式的文件
	 *
	 * @param accessKeyId 阿里云accessKeyId
	 * @param accessKeySecret 阿里云accessKeySecret
	 * @param endpoint 阿里云 endpoint
	 * @param bucketName 阿里云存储单元bucket名称
	 * @param fileByte 文件的byte对象
	 * @param folderStr 自定义目录
	 * @param fileName 文件名称
	 * @param fileExtension 扩展名
	 * @return 文件路径
	 * @throws IOException 抛出io异常
	 */
	@SuppressWarnings("unused")
	public static String PutByte(String accessKeyId, String accessKeySecret, String endpoint, String bucketName, byte[] fileByte, String folderStr,
			String fileName, String fileExtension) throws IOException {
		// 1. 如果文件流为空就返回空
		if (fileByte == null) {
            return null;
//			throw new YFException("2002");
        }
        // 2. 初始化ossclient
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(fileByte.length);
        // 3. 文件名
        String ossFileName = folderStr + fileName;
        if (StringUtils.isNotBlank(fileExtension)) {
            ossFileName = ossFileName + fileExtension;
        }
        // 4. 上传Object.
        Object object = ossClient.putObject(bucketName, ossFileName, new ByteArrayInputStream(fileByte), meta);
        return ossFileName;
    }

	public static String getInstance32Bit() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
