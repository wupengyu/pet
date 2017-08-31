/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.entity;

/**
 * 与Client端交互用的实体类
 *
 * @author infi.wang
 */
public class ResponseVO {

    private String result;

    private String message;

    private Object data;

    public ResponseVO() {
        this.result = ReturnMessageEnum.RETURN_OK.getResult();
        this.message = ReturnMessageEnum.RETURN_OK.getMessage();
    }

    public ResponseVO(ReturnMessageEnum returnMessage) {
        this.result = returnMessage.getResult();
        this.message = returnMessage.getMessage();
    }


    /**
     * 获取错误代码
     *
     * @return errorCode 错误代码
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置错误代码
     *
     * @param result 错误代码
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取错误消息
     *
     * @return errorMessage 错误消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置错误消息
     *
     * @param message 错误消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获得数据
     *
     * @return 数据
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setData(Object data) {
        this.data = data;
    }

}
