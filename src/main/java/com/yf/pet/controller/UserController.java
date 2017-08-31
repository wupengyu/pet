/*

 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.controller;

import com.alibaba.fastjson.JSON;
import com.yf.pet.authentication.annotation.DisableAuth;
import com.yf.pet.entity.ResponseVO;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.user.UserInfo;
import com.yf.pet.entity.user.UserSimpleInfo;
import com.yf.pet.entity.user.vo.UserInfoVO;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.AccountService;
import com.yf.pet.service.UserInfoService;
import com.yf.pet.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理控制层
 *
 * @author infi.wang
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    /**
     * log 日志
     */
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AccountService accountService;

    /**
     * 根据token修改用户信息
     * 1. 如果只修改用户头像,jsonParameter参数可以为空
     *
     * @param request httprequest对象
     * @return 修改用户信息的结果
     * @throws Exception 异常抛出
     */
    @RequestMapping(value = "/bytoken/save")
    public ResponseVO save(HttpServletRequest request) throws Exception {
        // 2. 如果传递的参数中包含文件，就接收文件(头像文件)
        MultipartFile headPic = null;
        if (request instanceof MultipartHttpServletRequest) {
            headPic = ((DefaultMultipartHttpServletRequest) request).getFile("headPic");
        }
        // 1. 判断参数是否为空
        if (StringUtils.isBlank(request.getParameter("jsonParameter")) && headPic == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 3. 保存用户信息
        UserInfoVO userInfoVO = new UserInfoVO();
        if (StringUtils.isNotBlank(request.getParameter("jsonParameter"))) {
            userInfoVO = JSON.parseObject(request.getParameter("jsonParameter"), UserInfoVO.class);
        }
        userInfoVO.setHeadPicFile(headPic);
        Long userId = userInfoService.updateUserInfoByToken(TokenUtils.getAuthToken(request), userInfoVO);

        //返回用户信息
        UserInfoVO resultUserInfo = userInfoService.findAccountAndUserInfoAndTargetByUserId(userId);
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(resultUserInfo);
        return responseVO;
    }

    /**
     * 保存用户时区,当用户时区变化以后把变更记录保存到服务器
     * 1. 在用户时区变更记录表(t_user_timezone_modify)新增一条记录
     * 2. 如果本次上传的时区和用户信息表(t_user_info)中的时区不相同,就修改用户信息表中的用户当前所在时区字段
     *
     * @param request  httprequest对象
     * @param userInfo 用户时区信息
     * @return 保存结果
     * @throws Exception
     */
    @RequestMapping(value = "/timezone/update")
    public ResponseVO updateTimezone(HttpServletRequest request, @RequestBody UserInfo userInfo) {

        // 1. 判断参数是否为空
        if (userInfo == null || userInfo.getTimezone() == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        UserInfo findUserInfo = userInfoService.findUserInfoByToken(TokenUtils.getAuthToken(request));
        userInfoService.updateTimezone(findUserInfo, userInfo.getTimezone());
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 查询用户信息,根据token返回用户信息
     *
     * @param request httpRequest对象
     * @return 用户信息
     * @throws Exception
     */
    @RequestMapping(value = "/bytoken/find")
    public ResponseVO findUserInfo(HttpServletRequest request) {

        UserInfoVO userInfo = userInfoService.findAccountAndUserInfoByAccessToken(TokenUtils.getAuthToken(request));
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(userInfo);
        return responseVO;
    }

    /**
     * 通过拦截器验证token有效性
     *
     * @param request httpRequest对象
     * @return 用户信息
     * @throws Exception
     */
    @DisableAuth
    @RequestMapping(value = "/token/check")
    public ResponseVO checkToken(HttpServletRequest request) {
        UserSimpleInfo userSimpleInfo = accountService.findSimpleUserInfoByToken(TokenUtils.getAuthToken(request));
        boolean valid = false;
        if (userSimpleInfo != null) {
            valid = true;
        }
        // 1. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(valid);
        return responseVO;
    }

    /**
     * 修改紧急联系人电话号码
     *
     * @param userInfoVO 前台提交的参数
     * @return 获取验证码信息
     * @throws Exception 异常抛出
     */
    @RequestMapping(value = "/contact/number/save")
    public ResponseVO saveContact(HttpServletRequest request, @RequestBody UserInfoVO userInfoVO) {
        // 1. 检查参数
        if (userInfoVO == null
                || (StringUtils.isBlank(userInfoVO.getContactsMobile()) && StringUtils.isBlank(userInfoVO.getPhoneCode())
                && StringUtils.isBlank(userInfoVO.getContactCountryCode()) && StringUtils.isBlank(userInfoVO.getContactNote()))) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 2. 查询用户信息.
        UserInfo findUserInfo = userInfoService.findUserInfoByToken(TokenUtils.getAuthToken(request));
        // 4. 修改紧急联系人电话
        userInfoService.saveUserToContact(findUserInfo.getUserId(), userInfoVO.getContactsMobile(), findUserInfo.getNickname(), userInfoVO.getContactNote(),
                userInfoVO.getPhoneCode(), userInfoVO.getContactCountryCode());

        // 5. 返回操作结果
        ResponseVO result = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        UserInfoVO resultUser = userInfoService.findAccountAndUserInfoAndTargetByUserId(findUserInfo.getUserId());
        result.setData(resultUser);

        return result;
    }
}
