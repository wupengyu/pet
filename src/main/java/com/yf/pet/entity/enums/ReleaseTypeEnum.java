package com.yf.pet.entity.enums;

/**
 * 固件releaseType类型枚举
 * Created by Infi on 17/6/29.
 */
public interface ReleaseTypeEnum {

    /**
     * 发布版本
     */
    static final int RELEASE = 1;
    /**
     * 公测版本
     */
    static final int PUBLIC_BETA = 2;
    /**
     * 内测版本
     */
    static final int CLOSED_BETA = 3;
}
