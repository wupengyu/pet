/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.cache;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * 校验码缓存包装类
 *
 * @author Tony.Tong
 */
public class VerificationCacheWrapper {

    private static RedisCacheManager verificationCacheManager;

    private static final String CACHENAME = "v";

    /**
     * 设置CacheManager，通过Spring注入.
     *
     * @param verificationCacheManager 专用于校验码的CacheManager.
     */
    public static final void setVerificationCacheManager(RedisCacheManager verificationCacheManager) {
        VerificationCacheWrapper.verificationCacheManager = verificationCacheManager;
    }

    /**
     * 从缓存读取校验码，如果缓存没有，则返回null.
     *
     * @param veriCode 校验码
     * @return 如果缓存存在，则返回校验码，否则返回null.
     */
    public static String get(String veriCode) {
        if (veriCode == null) {
            return null;
        }
        ValueWrapper value = verificationCacheManager.getCache(CACHENAME).get(veriCode.toLowerCase());
        String code = null;
        if (value != null) {
            code = (String) value.get();
        }
        return code;
    }

    /**
     * 将校验码放入缓存中.
     *
     * @param veriCode 校验码
     */
    public static void put(String veriCode) {
        if (veriCode == null) {
            return;
        }
        veriCode = veriCode.toLowerCase();
        verificationCacheManager.getCache(CACHENAME).put(veriCode, veriCode);
    }

    /**
     * 将校验码从缓存中去除.
     *
     * @param veriCode 校验码
     */
    public static void evict(String veriCode) {
        if (veriCode == null) {
            return;
        }
        veriCode = veriCode.toLowerCase();
        verificationCacheManager.getCache(CACHENAME).evict(veriCode);
    }
}
