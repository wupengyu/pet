/*

 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.controller;

import com.yf.pet.authentication.annotation.DisableAuth;
import com.yf.pet.entity.ResponseVO;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.user.User;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.yf.pet.utils.IPUtils.getRemoteHost;

/**
 * 用户管理控制层
 *
 * @author wupengyu
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    /**
     * log 日志
     */
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册
     * <p>
     * //     * @param jsonParameter 注册帐号信息的json参数
     *
     * @param request httpRequest
     * @return 注册结果
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/register")
    public ResponseVO save(@RequestBody User user, HttpServletRequest request) throws Exception {
        // 检查参数
        if (user == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (user.getPwd() == null) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }
        if (user.getRegisterType() == null) {
            throw new YFException(ReturnMessageEnum.REGISTER_TEYP_NULL);
        }
        if (user.getRegisterType() == 1 && user.getEmail() == null) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        if (user.getRegisterType() == 2 && user.getFacebookId() == null) {
            throw new YFException(ReturnMessageEnum.FACEBOOK_ID_NULL);
        }
        if (user.getRegisterTimezone() == null) {
            throw new YFException(ReturnMessageEnum.REGISTER_TIMEZONE_NULL);
        }

        //验证账号是否已经存在
        Boolean isExist = userService.findAccountIsExist(user);
        if (isExist) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_EXIST);
        }

        //TODO 验证邮箱验证码

        // 获取用户IP地址
        user.setIpAddress(getRemoteHost(request));

        //注册
        user = userService.addUser(user);

        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
        return responseVO;
    }
}
