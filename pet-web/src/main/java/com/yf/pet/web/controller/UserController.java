/*

 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.web.controller;

import com.yf.pet.common.ResponseVO;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.cache.utils.TokenUtils;
import com.yf.pet.common.exception.YFException;
import com.yf.pet.common.utils.regex.YFRegextUtils;
import com.yf.pet.user.api.dto.*;
import com.yf.pet.user.api.service.IUserService;
import com.yf.pet.web.common.annotation.DisableAuth;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    private IUserService userService;


    @DisableAuth
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    /**
     * 用户邮箱注册
     *
     * @return 注册结果
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseVO register(@RequestBody UserRegisterDto userRegisterDto) throws Exception {
        log.info("用户注册：" + userRegisterDto.toString());

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
        if (!YFRegextUtils.isEmail(userRegisterDto.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_ERROR);
        }

        //验证账号是否已经存在
        Boolean isExist = userService.findAccountIsExist(userRegisterDto.getEmail(), null);
        if (isExist) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_EXIST);
        }

        //注册
        UserLoginReturnDto user = userService.emailRegister(userRegisterDto);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
        return responseVO;
    }

    /**
     * 用户登录
     *
     * @return 登录
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseVO login(@RequestBody UserEmailLoginDto userEmailLoginDto) throws Exception {
        log.info("用户登录：" + userEmailLoginDto.toString());
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
        UserLoginReturnDto user = userService.emailLogin(userEmailLoginDto);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
        return responseVO;
    }

    /**
     * 第三方用户登录
     *
     * @return 登录
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/openlogin", consumes = "application/json", produces = "application/json")
    public ResponseVO openAccountlogin(@RequestBody UserOpenIdLoginDto userOpenIdLoginDto) throws Exception {
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
        if (userOpenIdLoginDto.getOpenEmail() != null && userOpenIdLoginDto.getOpenEmail() && StringUtils.isEmpty(userOpenIdLoginDto.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }

        //登录
        log.info("第三方用户登录：" + userOpenIdLoginDto.toString());
        UserLoginReturnDto user = userService.openLogin(userOpenIdLoginDto);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(user);
        return responseVO;
    }


    /**
     * 检查openid是否已经注册
     *
     * @param verifyOpenIdExistDto
     * @return
     * @throws Exception
     */
    @DisableAuth
    @RequestMapping(value = "/openid/exist", consumes = "application/json", produces = "application/json")
    public ResponseVO openIdExist(@RequestBody VerifyOpenIdExistDto verifyOpenIdExistDto) throws Exception {

        log.info("验证openId是否已经登录过：" + verifyOpenIdExistDto.toString());
        // 检查参数
        if (verifyOpenIdExistDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (verifyOpenIdExistDto.getOpenId() == null) {
            throw new YFException(ReturnMessageEnum.OPEN_ID_NULL);
        }

        Boolean isExist = userService.findAccountIsExist(null, verifyOpenIdExistDto.getOpenId());

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(isExist);
        return responseVO;
    }


    /**
     * 修改密码
     *
     * @return
     * @throws Exception 异常抛出
     */
    @RequestMapping(value = "/pwdreset", consumes = "application/json", produces = "application/json")
    public ResponseVO updatePassword(@RequestBody UserPwdResetDto userPwdResetDto) throws Exception {
        log.info("修改密码：" + userPwdResetDto.toString());
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
     * 发送找回密码邮件
     *
     * @return
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/password/reset/send/email", consumes = "application/json", produces = "application/json")
    public ResponseVO getbackpwd(@RequestBody UserForgetPwdDto userForgetPwdDto) throws Exception {
        log.info("发送找回密码邮件：" + userForgetPwdDto.toString());
        if (userForgetPwdDto == null || StringUtils.isEmpty(userForgetPwdDto.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        userService.sendEmailResetPwd(userForgetPwdDto.getEmail());
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 邮箱找回密码
     *
     * @return
     * @throws Exception 异常抛出
     */
    @DisableAuth
    @RequestMapping(value = "/pwd/update", consumes = "application/json", produces = "application/json")
    public ResponseVO updatePasswordByEmail(@RequestBody UserPwdResetDto userPwdResetDto) throws Exception {
        log.info("邮箱找回密码：" + userPwdResetDto.toString());
        // 检查参数
        if (userPwdResetDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isEmpty(userPwdResetDto.getPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_NULL);
        }
        if (StringUtils.isEmpty(userPwdResetDto.getEmail())) {
            throw new YFException(ReturnMessageEnum.EMAIL_NULL);
        }
        userService.resetPwdByCode(userPwdResetDto);
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
    public ResponseVO updateUserinfo(@RequestBody UserUpdateInfoDto userUpdateInfoDto, HttpServletRequest request) throws Exception {
        String accessToken = TokenUtils.getAuthToken(request);
        if (StringUtils.isEmpty(accessToken)) {
            throw new YFException(ReturnMessageEnum.TOKEN_NULL);
        }
        userUpdateInfoDto.setAccessToken(accessToken);

        //调用接口
        log.info("更新用户信息：" + userUpdateInfoDto.toString());
        UserLoginReturnDto user = userService.updateUserInfo(userUpdateInfoDto);

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
        log.info("获取用户信息: " + accessToken);
        UserLoginReturnDto user = userService.getUserInfo(accessToken);
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
        log.info("退出登录: " + accessToken);
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
        log.info("检测邮箱是否被注册: " + userEmailLoginDto.toString());
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
     * 上传图片到服务器
     *
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload/picture")
    public ResponseVO uploadPicture(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) throws Exception {
        log.info("上传图片到服务器: ");
        // 检查参数
        if (file == null || file.getSize() <= 0) {
            throw new YFException(ReturnMessageEnum.PARAMETER_NULL);
        }

        String url = userService.uploadPic(file.getBytes());

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(url);
        return responseVO;
    }

}
