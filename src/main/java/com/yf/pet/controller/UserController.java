/*

 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.controller;

import com.yf.pet.common.authentication.annotation.DisableAuth;
import com.yf.pet.common.utils.TokenUtils;
import com.yf.pet.entity.ResponseVO;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.entity.user.User;
import com.yf.pet.entity.user.UserRegisterEnum;
import com.yf.pet.entity.user.vo.UserEmailLoginVo;
import com.yf.pet.entity.user.vo.UserOpenIdLoginVo;
import com.yf.pet.entity.user.vo.UserPwdResetVo;
import com.yf.pet.entity.user.vo.UserRegisterVo;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.yf.pet.common.utils.IPUtils.getRemoteHost;

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


    @DisableAuth
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    /**
     * 用户邮箱注册
     *
     * @param request httpRequest
     * @return 注册结果
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseVO register(@RequestBody UserRegisterVo userRegisterVo, HttpServletRequest request) throws Exception {
        // 检查参数
        if (userRegisterVo == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (userRegisterVo.getPwd() == null) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }
        if (StringUtils.isEmpty(userRegisterVo.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        if (userRegisterVo.getRegisterTimezone() == null) {
            throw new YFException(ReturnMessageEnum.REGISTER_TIMEZONE_NULL);
        }
        //TODO 密码和邮箱格式验证

        //验证账号是否已经存在
        Boolean isExist = userService.findAccountIsExist(userRegisterVo.getEmail(), null);
        if (isExist) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_EXIST);
        }

        //注册
        User user = userService.emailRegister(userRegisterVo);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
        return responseVO;
    }

    /**
     * 用户登录
     *
     * @param request httpRequest
     * @return 登录
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseVO login(@RequestBody UserEmailLoginVo userEmailLoginVo, HttpServletRequest request) throws Exception {
        // 检查参数
        if (userEmailLoginVo == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (userEmailLoginVo.getEmail() == null) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        if (userEmailLoginVo.getPwd() == null) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }

        //登录
        User user = userService.emailLogin(userEmailLoginVo);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
        return responseVO;
    }

    /**
     * 第三方用户登录
     *
     * @param request httpRequest
     * @return 登录
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/openlogin", consumes = "application/json", produces = "application/json")
    public ResponseVO openAccountlogin(@RequestBody UserOpenIdLoginVo userOpenIdLoginVo, HttpServletRequest request) throws Exception {

        log.info("第三方用户登录："+ this.getClass().getName() + ".openAccountlogin" );
        // 检查参数
        if (userOpenIdLoginVo == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (userOpenIdLoginVo.getOpenId() == null) {
            throw new YFException(ReturnMessageEnum.OPEN_ID_NULL);
        }
        if (userOpenIdLoginVo.getOpenType() == null) {
            throw new YFException(ReturnMessageEnum.OPEN_TYPE_NULL);
        }
        if (userOpenIdLoginVo.getPwd() == null) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }

        // 获取用户IP地址
//        userOpenIdLoginVo.setIpAddress(getRemoteHost(request));

        //登录
        User user = userService.openLogin(userOpenIdLoginVo);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
        return responseVO;
    }


    /**
     * 修改密码
     *
     * @param request httpRequest
     * @return
     * @throws Exception 异常抛出
     */
    @RequestMapping(value = "/pwdreset", consumes = "application/json", produces = "application/json")
    public ResponseVO updatePassword(@RequestBody UserPwdResetVo userPwdResetVo, HttpServletRequest request) throws Exception {
        // 检查参数
        if (userPwdResetVo == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isEmpty(userPwdResetVo.getPwd()) || StringUtils.isEmpty(userPwdResetVo.getNewPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }
        if (StringUtils.isEmpty(userPwdResetVo.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        userService.pwdreset(userPwdResetVo);
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 密码找回
     *
     * @param request httpRequest
     * @return
     * @throws Exception 异常抛出
     */
    @RequestMapping(value = "/forgetpwd", consumes = "application/json", produces = "application/json")
    public ResponseVO getbackpwd(@RequestBody UserOpenIdLoginVo userOpenIdLoginVo, HttpServletRequest request) throws Exception {
        //TODO


        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 更新用户信息
     *
     * @param request httpRequest
     * @return
     * @throws Exception 异常抛出
     */
    @RequestMapping(value = "/updateuserinfo", consumes = "application/json", produces = "application/json")
    public ResponseVO updateUserinfo(@RequestBody UserOpenIdLoginVo userOpenIdLoginVo, HttpServletRequest request) throws Exception {
        //TODO
        
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 获取用户信息
     *
     * @param request httpRequest
     * @return
     * @throws Exception 异常抛出
     */
    @RequestMapping(value = "/getuserinfo", consumes = "application/json", produces = "application/json")
    public ResponseVO getUserinfo( HttpServletRequest request) throws Exception {
        String accessToken = TokenUtils.getAuthToken(request);
        if(StringUtils.isEmpty(accessToken)){
            throw new YFException(ReturnMessageEnum.TOKEN_NULL);
        }

        User user =userService.getUserInfo(accessToken);
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
        return responseVO;
    }

    /**
     * 退出登录
     *
     * @param request httpRequest
     * @return
     * @throws Exception 异常抛出
     */
    @RequestMapping(value = "/loginout", consumes = "application/json", produces = "application/json")
    public ResponseVO logout(HttpServletRequest request) throws Exception {
        String accessToken = TokenUtils.getAuthToken(request);
        if(StringUtils.isEmpty(accessToken)){
            throw new YFException(ReturnMessageEnum.TOKEN_NULL);
        }
        //登出
        userService.loginOut(accessToken);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 检测邮箱是否被注册
     *
     * @param request httpRequest
     * @return
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/checkemail", consumes = "application/json", produces = "application/json")
    public ResponseVO checkEmail(@RequestBody UserEmailLoginVo userEmailLoginVo, HttpServletRequest request) throws Exception {
        // 检查参数
        if (userEmailLoginVo == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isEmpty(userEmailLoginVo.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        Boolean isExist = userService.findAccountIsExist(userEmailLoginVo.getEmail(), null);
        if(isExist){
            ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.ACCOUNT_IS_EXIST);
            return responseVO;
        }
        
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 给指定邮箱发个验证码
     *
     * @param request httpRequest
     * @return
     * @throws Exception 异常抛出
     */
//    @DisableAuth
//    @RequestMapping(value = "/sendcodetoemail", consumes = "application/json", produces = "application/json")
//    public ResponseVO sendCodeToEmail(@RequestBody UserEmailLoginVo  userEmailLoginVo, HttpServletRequest request) throws Exception {
//        // 检查参数
//        if (userEmailLoginVo == null) {
//            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
//        }
//        if (StringUtils.isEmpty(userEmailLoginVo.getEmail())) {
//            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
//        }
//        //给指定邮箱发个验证码
//
//        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
//        return responseVO;
//    }

}
