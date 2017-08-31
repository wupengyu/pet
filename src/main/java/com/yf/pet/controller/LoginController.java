/*

 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.controller;

import com.yf.pet.authentication.annotation.DisableAuth;
import com.yf.pet.entity.ResponseVO;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.user.vo.UserInfoVO;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.LoginService;
import com.yf.pet.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理控制层
 *
 * @author infi.wang
 */
@RestController
@RequestMapping( method = RequestMethod.POST)
public class LoginController {
    /**
     * log 日志
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginService loginService;


    @Autowired
    private UserInfoService userInfoService;

    /**
     * 电话号码或邮箱地址登录
     *
     * @param userInfo 用户信息
     * @param request  httprequest对象
     * @return 登录结果
     */
    @DisableAuth
    @RequestMapping(value = "/login")
    public ResponseVO login(@RequestBody UserInfoVO userInfo, HttpServletRequest request) {
        // 1. 判断参数是否为空
        if (userInfo == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 3. 保存用户信息
        Long userId = loginService.loginByAccount(userInfo);
        UserInfoVO resultUserInfo = userInfoService.findAccountAndUserInfoAndTargetByUserId(userId);
//        // 4. 查询上次解析的状态值
//        LabelStatus labelStatus = labelStatusService.findLabelStatusByUserId(userId);
//        if (labelStatus != null && labelStatus.getStatusData() != null) {
//            resultUserInfo.setSaveParseInfo(YFTools.toHexString(labelStatus.getStatusData()));
//        }
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(resultUserInfo);
        return responseVO;
    }

    /**
     * 用户登出,修改帐号token信息
     *
     * @param request httprequest信息
     * @return 登出操作结果
     */
    @DisableAuth
    @RequestMapping(value = "/logout")
    public ResponseVO logout(HttpServletRequest request) {
        // 1. 判断参数是否为空
        String accessToken = request.getParameter("accessToken");
        if (StringUtils.isBlank(accessToken)) {
            throw new YFException(ReturnMessageEnum.ACCESS_TOKEN_IS_NULL);
        }
        // 2. 用户登出
        loginService.logout(accessToken);
        // 3. 设置返回值
        return new ResponseVO();
    }
}
