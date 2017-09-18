/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.cache;

import com.alibaba.fastjson.JSON;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.cache.utils.TokenUtils;
import com.yf.pet.common.exception.YFException;
import com.yf.pet.user.api.entity.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

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
        if (!StringUtils.isEmpty(accessToken)) {
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

        if (userInfo != null && !StringUtils.isEmpty(userInfo.getAccessToken())) {
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
        if ( !StringUtils.isEmpty(accessToken)) {
            SessionCacheWrapper.evict(YFRedisCachePrefix.getProductAndMode(ProductType.PET, ProductModel.USER), accessToken);
        }
    }

    /**
     * request里取出user
     *
     * @param request
     * @return
     */
    public static User getUserByRequest(HttpServletRequest request){
        String accessToken = TokenUtils.getAuthToken(request);
        if (org.apache.commons.lang3.StringUtils.isEmpty(accessToken)) {
            throw new YFException(ReturnMessageEnum.TOKEN_NULL);
        }
        User user = RedisUtilsPet.getUserByToken(accessToken);
        if (user == null || user.getUserId() == null) {
            throw new YFException(ReturnMessageEnum.USER_NOT_EXIT);
        }
        return user;
    }


}
