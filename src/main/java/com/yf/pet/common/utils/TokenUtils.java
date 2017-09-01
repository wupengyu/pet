/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * token 帮助类
 *
 * @author Infi
 */
public class TokenUtils {
    /**
     * 获取http请求头部或者参数中的token值
     *
     * @param request http请求传递的值
     * @return 返回token
     */
    public static String getAuthToken(HttpServletRequest request) {
        String token = null;
        token = request.getParameter("accessToken");
        if (token == null) {
            token = request.getHeader("accessToken");
        }
        return token;
    }
}
