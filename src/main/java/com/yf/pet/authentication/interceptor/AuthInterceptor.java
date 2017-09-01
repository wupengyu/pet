/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.authentication.interceptor;

import com.yf.pet.authentication.annotation.DisableAuth;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.user.User;
import com.yf.pet.exception.YFException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户登录信息过期拦截器
 *
 * @author infi
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountService accountService;

//	private String product = YFResourceUtil.getValueByKey("resources.properties", "redis.key.product");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        // 1. 判断是否鉴权
        DisableAuth auth = method.getMethod().getAnnotation(DisableAuth.class);
        if (isDisableAuth(auth)) {
            return super.preHandle(request, response, handler);
        }

        // 2. 获取token,检查token是否为空
        String accessToken = getAuthToken(request);
        if (StringUtils.isBlank(accessToken)) {
            throw new YFException(ReturnMessageEnum.ACCESS_TOKEN_IS_NULL);
        }

        // 3. 查询token是否正确
        User account = accountService.findAccessTokenIsValid(accessToken);
        if (account == null) {
            throw new YFException(ReturnMessageEnum.ACCESS_TOKEN_IS_INVALID);
        }

        // 4. 查看token是否过期
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        if (account.getValidityDate() != null && today.compareTo(account.getValidityDate()) > -1) {
            throw new YFException(ReturnMessageEnum.ACCESS_TOKEN_IS_INVALID);
        }
        return super.preHandle(request, response, handler);
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 获取http请求头部或者参数中的token值
     *
     * @param request http请求传递的值
     * @return 返回token
     */
    private String getAuthToken(HttpServletRequest request) {
        String token = null;
        token = request.getParameter("accessToken");
        if (token == null) {
            token = request.getHeader("accessToken");
        }
        return token;
    }

    public String getBodyString(BufferedReader br) {
        String inputLine;
        StringBuffer bodyValueStringBuffer = new StringBuffer();
        try {
            while ((inputLine = br.readLine()) != null) {
                bodyValueStringBuffer.append(inputLine);
            }
        } catch (IOException e) {
            log.error("AuthInterceptor类中getBodyString()发生IO异常", e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                log.error("AuthInterceptor类中getBodyString()释放资源发生IO异常", e);
            }
        }
        return bodyValueStringBuffer.toString();
    }

    /**
     * 判断是否鉴权
     *
     * @param auth 权限注解
     * @return 是否拦截
     */
    private static boolean isDisableAuth(DisableAuth auth) {
        if (auth != null) {
            return true;
        }
        return false;
    }
}
