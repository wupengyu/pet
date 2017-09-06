/*

 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.controller;

import com.yf.pet.common.authentication.annotation.DisableAuth;
import com.yf.pet.common.utils.TokenUtils;
import com.yf.pet.entity.ResponseVO;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.entity.user.User;
import com.yf.pet.entity.user.dto.*;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
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
    public ResponseVO register(@RequestBody UserRegisterDto userRegisterDto, HttpServletRequest request) throws Exception {
        // 检查参数
        if (userRegisterDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (userRegisterDto.getPwd() == null) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }
        if (StringUtils.isEmpty(userRegisterDto.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        if (userRegisterDto.getRegisterTimezone() == null) {
            throw new YFException(ReturnMessageEnum.REGISTER_TIMEZONE_NULL);
        }
        //TODO 密码和邮箱格式验证

        //验证账号是否已经存在
        Boolean isExist = userService.findAccountIsExist(userRegisterDto.getEmail(), null);
        if (isExist) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_EXIST);
        }

        //注册
        User user = userService.emailRegister(userRegisterDto);

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
    public ResponseVO login(@RequestBody UserEmailLoginDto userEmailLoginDto, HttpServletRequest request) throws Exception {
        // 检查参数
        if (userEmailLoginDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (userEmailLoginDto.getEmail() == null) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        if (userEmailLoginDto.getPwd() == null) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }

        //登录
        User user = userService.emailLogin(userEmailLoginDto);

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
    public ResponseVO openAccountlogin(@RequestBody UserOpenIdLoginDto userOpenIdLoginDto, HttpServletRequest request) throws Exception {

        log.info("第三方用户登录：" + this.getClass().getName() + ".openAccountlogin");
        // 检查参数
        if (userOpenIdLoginDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (userOpenIdLoginDto.getOpenId() == null) {
            throw new YFException(ReturnMessageEnum.OPEN_ID_NULL);
        }
        if (userOpenIdLoginDto.getOpenType() == null) {
            throw new YFException(ReturnMessageEnum.OPEN_TYPE_NULL);
        }
        if (userOpenIdLoginDto.getPwd() == null) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }

        // 获取用户IP地址
//        userOpenIdLoginDto.setIpAddress(getRemoteHost(request));

        //登录
        User user = userService.openLogin(userOpenIdLoginDto);

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
    public ResponseVO updatePassword(@RequestBody UserPwdResetDto userPwdResetDto, HttpServletRequest request) throws Exception {
        // 检查参数
        if (userPwdResetDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isEmpty(userPwdResetDto.getPwd()) || StringUtils.isEmpty(userPwdResetDto.getNewPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }
        if (StringUtils.isEmpty(userPwdResetDto.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        userService.pwdreset(userPwdResetDto);
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
    @RequestMapping(value = "/password/reset", consumes = "application/json", produces = "application/json")
    public ResponseVO getbackpwd(@RequestBody UserForgetPwdDto userForgetPwdDto, HttpServletRequest request) throws Exception {
        if(userForgetPwdDto == null || StringUtils.isEmpty(userForgetPwdDto.getEmail()) ){
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }



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
    @RequestMapping(value = "/updateuserinfo")
    public ResponseVO updateUserinfo(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        String accessToken = TokenUtils.getAuthToken(request);
        if (StringUtils.isEmpty(accessToken)){
            throw  new YFException(ReturnMessageEnum.TOKEN_NULL);
        }

        //接收参数
        UserUpdateInfoDto userUpdateInfoDto = new UserUpdateInfoDto();
        MultipartFile headPic = file;
        userUpdateInfoDto.setHeadPicFile(headPic);

        userUpdateInfoDto.setAccessToken(accessToken);
        userUpdateInfoDto.setFirstName(StringUtils.isEmpty(request.getParameter("firstName")) ? null : request.getParameter("firstName"));
        userUpdateInfoDto.setLastName(StringUtils.isEmpty(request.getParameter("lastName")) ? null : request.getParameter("lastName"));
        userUpdateInfoDto.setMobile(StringUtils.isEmpty(request.getParameter("mobile")) ? null : request.getParameter("mobile"));

        Integer gender = null;
        Object sexObj = request.getParameter("gender");
        if (sexObj != null && !StringUtils.isEmpty(sexObj.toString())) {
            gender = NumberUtils.toInt(sexObj.toString());
        }
        userUpdateInfoDto.setGender(gender);

        Date birthday = null;
        Object birthdayObj = request.getParameter("birthday");
        if (birthdayObj != null && !StringUtils.isEmpty(birthdayObj.toString())) {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayObj.toString());
        }
        userUpdateInfoDto.setBirthday(birthday);

        //调用接口
        User user = userService.updateUserInfo(userUpdateInfoDto);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
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
    public ResponseVO getUserinfo(HttpServletRequest request) throws Exception {
        String accessToken = TokenUtils.getAuthToken(request);
        if (StringUtils.isEmpty(accessToken)) {
            throw new YFException(ReturnMessageEnum.TOKEN_NULL);
        }

        User user = userService.getUserInfo(accessToken);
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
    @RequestMapping(value = "/loginout")
    public ResponseVO logout(HttpServletRequest request) throws Exception {
        String accessToken = TokenUtils.getAuthToken(request);
        if (StringUtils.isEmpty(accessToken)) {
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
    public ResponseVO checkEmail(@RequestBody UserEmailLoginDto userEmailLoginDto, HttpServletRequest request) throws Exception {
        // 检查参数
        if (userEmailLoginDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isEmpty(userEmailLoginDto.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        Boolean isExist = userService.findAccountIsExist(userEmailLoginDto.getEmail(), null);
        if (isExist) {
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
//    public ResponseVO sendCodeToEmail(@RequestBody UserEmailLoginDto  userEmailLoginDto, HttpServletRequest request) throws Exception {
//        // 检查参数
//        if (userEmailLoginDto == null) {
//            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
//        }
//        if (StringUtils.isEmpty(userEmailLoginDto.getEmail())) {
//            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
//        }
//        //给指定邮箱发个验证码
//
//        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
//        return responseVO;
//    }

}
