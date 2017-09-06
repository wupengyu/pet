/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Plupload是一个上传插件。 这是一个bean类,主要存储Plupload插件上传时需要的参数。 属性名不可随意改动. 这里主要使用MultipartFile文件上传方法
 * 
 * @author infi.wang
 */
public class Plupload {

	/**
	 * 文件临时名(打文件被分解时)或原名
	 */
	private String name;

	/**
	 * 总的块数
	 */
	private int chunks = -1;

	/**
	 * 当前块数（从0开始计数）
	 */
	private int chunk = -1;

	/**
	 * HttpServletRequest对象，不能直接传入进来，需要手动传入
	 */
	private HttpServletRequest request;

	/**
	 * 保存文件上传信息，不能直接传入进来，需要手动传入
	 */
	private MultipartFile multipartFile;

	/**
	 * 获取文件临时名(打文件被分解时)或原名
	 *
	 * @return name 文件临时名(打文件被分解时)或原名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取总的块数
	 *
	 * @return chunks 总的块数
	 */
	public int getChunks() {
		return chunks;
	}

	/**
	 * 设置总的块数
	 * 
	 * @param chunks 总的块数
	 */
	public void setChunks(int chunks) {
		this.chunks = chunks;
	}

	/**
	 * 获取当前块数（从0开始计数）
	 *
	 * @return chunk 当前块数（从0开始计数）
	 */
	public int getChunk() {
		return chunk;
	}

	/**
	 * 设置当前块数（从0开始计数）
	 * 
	 * @param chunk 当前块数（从0开始计数）
	 */
	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	/**
	 * 获取HttpServletRequest对象，不能直接传入进来，需要手动传入
	 *
	 * @return request HttpServletRequest对象，不能直接传入进来，需要手动传入
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 设置HttpServletRequest对象，不能直接传入进来，需要手动传入
	 * 
	 * @param request HttpServletRequest对象，不能直接传入进来，需要手动传入
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 获取保存文件上传信息，不能直接传入进来，需要手动传入
	 *
	 * @return multipartFile 保存文件上传信息，不能直接传入进来，需要手动传入
	 */
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	/**
	 * 设置保存文件上传信息，不能直接传入进来，需要手动传入
	 * 
	 * @param multipartFile 保存文件上传信息，不能直接传入进来，需要手动传入
	 */
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	/**
	 * 设置获取文件临时名(打文件被分解时)或原名
	 * 
	 * @param name 获取文件临时名(打文件被分解时)或原名
	 */
	public void setName(String name) {
		this.name = name;
	}
}