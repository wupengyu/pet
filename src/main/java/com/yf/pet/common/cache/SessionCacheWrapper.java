/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.cache;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Session缓存的包装类
 *
 * @author Tony.Tong
 */
public class SessionCacheWrapper {

    private static RedisCacheManager sessionCacheManager;

    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 用户 token 过期时间
     */
    private static Long expiredTime;

    /**
     * 后台维护信息用户的 token 过期时间
     */
    private static Long expiredTimeAdmin;

    /**
     * 设置CacheManager，通过Spring注入.
     *
     * @param sessionCacheManager 专用于Session的CacheManager.
     */
    public static final void setSessionCacheManager(RedisCacheManager sessionCacheManager) {
        SessionCacheWrapper.sessionCacheManager = sessionCacheManager;
    }

    /**
     * 设置RedisTemplate, 用于刷新过期时间.
     *
     * @param redisTemplate RedisTemplate对象
     */
    public static final void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        SessionCacheWrapper.redisTemplate = redisTemplate;
    }

    /**
     * 设置Session过期时间，通过Spring注入.
     *
     * @param expiredTime Session过期时间
     */
    public static final void setExpiredTime(Long expiredTime) {
        SessionCacheWrapper.expiredTime = expiredTime;
    }

    /**
     * 设置Session过期时间--后台管理员登录的过期时间，通过Spring注入.
     *
     * @param expiredTimeAdmin 后台管理员登录的token过期时间
     */
    public static void setExpiredTimeAdmin(Long expiredTimeAdmin) {
        SessionCacheWrapper.expiredTimeAdmin = expiredTimeAdmin;
    }

    /**
     * 从缓存读取Session Id，如果缓存没有，则返回null.
     *
     * @param cache     缓存名称
     * @param sessionId 缓存key.
     * @return 缓存内容
     */
    public static Object getObject(String cache, String sessionId) {
        if (sessionId == null) {
            return null;
        }
        ValueWrapper value = sessionCacheManager.getCache(cache).get(sessionId);
        if (value != null) {
            touch(cache, sessionId);
        }
        return value;
    }

    /**
     * 获取缓存json的对象
     *
     * @param cache     缓存名称，产品类型加上模块名称
     * @param sessionId 缓存id，也就是redis的key
     * @param clazz     类型
     * @param model     模块，比如用户、后台用户、APP强制升级、数据同步模块等
     * @return 指定对象
     */
    public static <T> T getJsonObject(String cache, String sessionId, Class<T> clazz, ProductModel model) {
        String key = cache + ":" + sessionId;
        String cacheValue = (String) redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(cacheValue)) {
            // 不同的模块，过期时间不同，刷新缓存
            switch (model) {
                case OPERATOR:
                    touch(cache, sessionId, expiredTimeAdmin);
                    break;
                case SYNC:
                    long outTime = 120;
                    touch(cache, sessionId, outTime);
                    break;
                default:
                    touch(cache, sessionId, expiredTime);
                    break;
            }
            return JSON.parseObject(cacheValue, clazz);
        } else {
            return null;
        }

    }

    /**
     * 获取缓存的list对象
     *
     * @param cache     缓存名称，产品类型加上模块名称
     * @param sessionId 缓存id，也就是redis的key
     * @param clazz     类型
     * @return 指定对象的list
     */
    public <T> List<T> getCacheDateList(String cache, String sessionId, Class<T> clazz) {
        String key = cache + ":" + sessionId;
        List<T> list = new ArrayList<T>();
        String cacheValue = (String) redisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(cacheValue)) {
            return list;
        }
        list = JSON.parseArray(cacheValue, clazz);
        return list;
    }

    /**
     * 将Session Id放入缓存中.
     *
     * @param cache     缓存名称，产品类型加上模块名称
     * @param sessionId 主键
     * @param object    存储对象
     */
    public static void put(String cache, String sessionId, Object object) {
        sessionCacheManager.getCache(cache).put(sessionId, object);
    }

    /**
     * 以json格式式存放session
     *
     * @param cache     缓存名称，产品类型加上模块名称
     * @param sessionId 主键
     * @param session   存储内容
     */
    public static void putSessionJson(String cache, String sessionId, String session) {
        sessionCacheManager.setDefaultExpiration(expiredTime);
        sessionCacheManager.getCache(cache).put(sessionId, session);
    }

    /**
     * 以json格式式存放session
     *
     * @param cache       缓存名称，产品类型加上模块名称
     * @param sessionId   主键
     * @param session     存储内容
     * @param expiredTime 过期时间
     */
    public static void putSessionJsonExpirationTime(String cache, String sessionId, String session, long expiredTime) {
        sessionCacheManager.setDefaultExpiration(expiredTime);
        sessionCacheManager.getCache(cache).put(sessionId, session);
    }

    /**
     * 以json格式式存放session
     *
     * @param cache       缓存名称，产品类型加上模块名称
     * @param sessionId   主键
     * @param session     存储内容
     * @param expiredTime 过期时间
     */
    public static void putSessionJsonExpirationTime(String cache, String sessionId, String session, int expiredTime) {
        sessionCacheManager.setDefaultExpiration(expiredTime);
        sessionCacheManager.getCache(cache).put(sessionId, session);
    }

    /**
     * 将Session Id从缓存中去除.
     *
     * @param cache     缓存名称，产品类型加上模块名称
     * @param sessionId 主键
     */
    public static void evict(String cache, String sessionId) {
        if (!StringUtils.isBlank(sessionId)) {
            sessionCacheManager.getCache(cache).evict(sessionId);
        }
    }

    /**
     * 刷新缓存的有效时间
     *
     * @param cache     产品类型
     * @param sessionId keys
     */
    public static void touch(String cache, String sessionId) {
        String redisKey = cache + ":" + sessionId;
        redisTemplate.expire(redisKey, expiredTime, TimeUnit.SECONDS);
    }

    /**
     * 刷新缓存的有效时间
     *
     * @param cache     产品类型
     * @param sessionId keys
     * @param outTime   过期时间
     */
    public static void touch(String cache, String sessionId, Long outTime) {
        String redisKey = cache + ":" + sessionId;
        redisTemplate.expire(redisKey, outTime, TimeUnit.SECONDS);
    }

}
