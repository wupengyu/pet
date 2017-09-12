/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.cache;

/**
 * 产品模块枚举，用户、后台用户、app版本强制升级、同步状态等
 *
 * @author Infi
 */
public enum ProductModel {

    /**
     * 用户
     */
    USER("u"),

    /**
     * 后台用户
     */
    OPERATOR("o"),

    /**
     * app版本强制升级
     */
    APPVERSION("v"),

    /**
     * 同步状态
     */
    SYNC("sync"),

    /**
     * email找回密码验证码
     */
    EMAIL_CODE("code:email");

    private final String productModel;

    private ProductModel(String productModel) {
        this.productModel = productModel;
    }

    @Override
    public String toString() {
        return productModel;
    }
}
