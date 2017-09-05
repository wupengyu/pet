/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.cache;

import com.alibaba.fastjson.JSON;
import com.yf.pet.common.ApplicationConstants;
import com.yf.pet.common.utils.YFResourceUtil;
import com.yf.pet.entity.user.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * redis缓存帮助类
 *
 * @author wupengyu
 */
public class RedisUtilsPet {


    /**
     * 根据token查询用户信息
     *
     * @param accessToken token
     * @return 用户信息
     */
    public static User getUserByToken(String accessToken) {
        if (StringUtils.isNotBlank(accessToken)) {
            return SessionCacheWrapper.getJsonObject(YFRedisCachePrefix.getProductAndMode(ProductType.PET, ProductModel.USER), accessToken,
                    User.class, ProductModel.USER);
        } else {
            return null;
        }
    }

    /**
     * 把用户信息写入redis
     *
     * @param userInfo 用户信息
     */
    public static void putUserKeyToken(User userInfo) {

        if (userInfo != null && StringUtils.isNotBlank(userInfo.getAccessToken())) {
            SessionCacheWrapper.putSessionJson(YFRedisCachePrefix.getProductAndMode(ProductType.PET, ProductModel.USER), userInfo.getAccessToken(),
                    JSON.toJSONString(userInfo));
        }
    }

    /**
     * 删除指定的用户信息
     *
     * @param accessToken redis中用token作为主键的键值
     */
    public static void evictUserByToken(String accessToken) {
        if (StringUtils.isNotBlank(accessToken)) {
            SessionCacheWrapper.evict(YFRedisCachePrefix.getProductAndMode(ProductType.PET, ProductModel.USER), accessToken);
        }
    }


}
