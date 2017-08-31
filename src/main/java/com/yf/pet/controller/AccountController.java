/*

 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.controller;

import com.alibaba.fastjson.JSON;
import com.yf.pet.authentication.annotation.DisableAuth;
import com.yf.pet.entity.ResponseVO;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.enums.AccountTypeEnum;
import com.yf.pet.entity.enums.ActivateTypeEnum;
import com.yf.pet.entity.user.Account;
import com.yf.pet.entity.user.UserSimpleInfo;
import com.yf.pet.entity.user.vo.UserInfoVO;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.AccountService;
import com.yf.pet.service.AccountTemporaryService;
import com.yf.pet.service.CheckCodeService;
import com.yf.pet.service.UserInfoService;
import com.yf.pet.utils.ApplicationConstants;
import com.yf.pet.utils.IPUtils;
import com.yf.pet.utils.TokenUtils;
import com.yf.pet.utils.aes.AESUtil;
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

import static com.yf.pet.utils.IPUtils.getRemoteHost;

/**
 * 用户管理控制层
 *
 * @author infi.wang
 */
@RestController
@RequestMapping(value = "/account", method = RequestMethod.POST)
public class AccountController {
    /**
     * log 日志
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AccountTemporaryService accountTemporaryService;

    @Autowired
    private CheckCodeService checkCodeService;

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
    public ResponseVO save(HttpServletRequest request) throws Exception {
        // 1. 判断参数是否为空
        if (StringUtils.isBlank(request.getParameter("jsonParameter"))) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 2. 如果传递的参数中包含文件，就接收文件(头像文件)
        MultipartFile headPic = null;
        if (request instanceof MultipartHttpServletRequest) {
            headPic = ((DefaultMultipartHttpServletRequest) request).getFile("headPic");
        }
        // 3. 保存用户信息
        UserInfoVO userInfoVO = JSON.parseObject(request.getParameter("jsonParameter"), UserInfoVO.class);
        userInfoVO.setHeadPicFile(headPic);

        // 获取用户IP地址
        userInfoVO.setIpAddress(getRemoteHost(request));
        // 4. 邮箱注册先加入临时表,发送激活邮件
//        if (AccountTypeEnum.EMAIL == userInfoVO.getAccountType()) {
//        accountTemporaryService.registerAccountTemporary(userInfoVO);
//        } else {
        Long userId = accountService.addAccount(userInfoVO);
//        }
        UserInfoVO resultUserInfo = userInfoService.findAccountAndUserInfoAndTargetByUserId(userId);

        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(resultUserInfo);
        return responseVO;
    }

    /**
     * 检查邮箱地址是否已经注册
     *
     * @param request    httprequest对象
     * @param userInfoVO 用户信息
     * @return 检查的结果
     */
    @DisableAuth
    @RequestMapping(value = "/email/isexist/check")
    public ResponseVO checkEmailIsExist(HttpServletRequest request,
                                        @RequestBody UserInfoVO userInfoVO) {
        // 1. 判断参数是否为空
        if (userInfoVO == null || StringUtils.isBlank(userInfoVO.getEmail())) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isBlank(userInfoVO.getAppKey())) {
            throw new YFException(ReturnMessageEnum.APPKEY_IS_INVALID);
        }
        // 2. 解密邮箱地址,检查邮箱地址是否被注册
        String email = AESUtil.decrypt(userInfoVO.getEmail(), userInfoVO.getAppKey());
        email = AESUtil.encrypt4InitStr(email, userInfoVO.getAppKey());
        Integer count = accountService.findEmailIsExist(email);
        boolean isExist = false;
        if (count != null && count > 0) {
            isExist = true;
        }
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(isExist);
        return responseVO;
    }

    /**
     * 帐号绑定
     *
     * @param request    httprequest对象
     * @param userInfoVO 用户信息
     * @return 绑定的结果
     */
    @RequestMapping(value = "/bind")
    public ResponseVO bindAccount(HttpServletRequest request, @RequestBody UserInfoVO userInfoVO) {
        // 1. 判断参数是否为空
        if (userInfoVO == null || StringUtils.isBlank(userInfoVO.getAccount())
                || userInfoVO.getAccountType() == null || StringUtils.isBlank(userInfoVO.getAppKey())) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 不支持电话号码
        if (AccountTypeEnum.MOBILE == userInfoVO.getAccountType()) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 2. 绑定手机号需要验证码,以后开通手机号功能以后在添加手机号码的逻辑
//        if (AccountTypeEnum.MOBILE == userInfoVO.getAccountType() && StringUtils.isBlank(userInfoVO.createCheckCode())) {
//            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
//        }
        // 2. 根据token查询account信息
        Account accountInDB = accountService.findAccountAndPwdByAccessToken(TokenUtils.getAuthToken(request));
        // 3. 帐号绑定
        userInfoVO.setIpAddress(getRemoteHost(request));
        accountService.bindAccount(accountInDB, userInfoVO);
        // 3. 查询用户信息
        UserInfoVO findUserVO = userInfoService.findAccountAndUserInfoAndTargetByUserId(accountInDB.getUserId());
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(findUserVO);
        return responseVO;
    }


    /**
     * 解除帐号绑定
     *
     * @param request    httprequest对象
     * @param userInfoVO 用户信息
     * @return 绑定的结果
     */
    @RequestMapping(value = "/unbind")
    public ResponseVO unbindAccount(HttpServletRequest request, @RequestBody UserInfoVO userInfoVO) {
        // 1. 判断参数是否为空
        if (userInfoVO == null || StringUtils.isBlank(userInfoVO.getAccount())
                || userInfoVO.getAccountType() == null || StringUtils.isBlank(userInfoVO.getAppKey())) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 2. 根据token查询account信息
        Account account = accountService.findAccountByAccessToken(TokenUtils.getAuthToken(request));
        // 3. 帐号绑定
        accountService.unbindAccount(account, userInfoVO);
        // 3. 查询用户信息
        UserInfoVO findUserVO = userInfoService.findAccountAndUserInfoAndTargetByUserId(account.getUserId());
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(findUserVO);
        return responseVO;
    }

    /**
     * TODO 这个接口会删除
     * 重新发送激活邮件
     *
     * @param request    httprequest
     * @param userInfoVO 用户信息
     * @return 发送结果
     */
    @RequestMapping(value = "/activate/mail/send")
    public ResponseVO sendActivateMail(HttpServletRequest request, @RequestBody UserInfoVO userInfoVO) {
        // 1. 参数错误
        if (StringUtils.isBlank(userInfoVO.getEmail()) || StringUtils.isBlank(userInfoVO.getAppKey())) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 2. 根据token查询account信息
        Account account = accountService.findAccountByAccessToken(TokenUtils.getAuthToken(request));
        // 3. 查看上传的邮箱地址是否是绑定未激活的邮箱地址
        String email = AESUtil.decryptAndXOR(userInfoVO.getEmail(), userInfoVO.getAppKey());
        Account accountTemporary = accountTemporaryService.findAccountTemporaryByUserIdAndEmail(account.getUserId(), email);
        Integer activateType = ActivateTypeEnum.BIND_ACTIVATE;
        if (accountTemporary == null) {
            // 4. 如果是邮箱注册的帐号,没有激活,就重新发送邮件
            Account registerAccount = accountService.findAccountByUserId(account.getUserId());
            // 5. 帐号不存在,或者数据库中的email和用户上传的email不同,就直接抛出异常
            if (registerAccount == null || !email.equals(registerAccount.getEmail())) {
                throw new YFException(ReturnMessageEnum.EMAIL_NOT_BIND);
            } else if (registerAccount.getActivateStatus() != null
                    && ApplicationConstants.IS_TRUE == registerAccount.getActivateStatus()) {
                // 6. 邮箱地址已经激活
                throw new YFException(ReturnMessageEnum.EMAIL_IS_ACTIVATED);
            } else {
                activateType = ActivateTypeEnum.REGISTER_ACTIVATE;
            }
        }
        // 5. 邮箱注册,发送激活邮件
        String ipAddress = IPUtils.getRemoteHost(request);
        checkCodeService.sendCheckCodeByEmailActivate(account.getUserId(), email, ipAddress, activateType);
        // 6. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 检查邮箱是否已经被激活<br>
     * 用途: 邮箱激活的html页面(activate.html)<br>
     *
     * @param request httprequest对象
     * @return 绑定的结果
     */
    @RequestMapping(value = "/email/activated/check")
    public ResponseVO checkEamilIsActivated(HttpServletRequest request, @RequestBody UserInfoVO userInfoVO) {
        if (StringUtils.isBlank(userInfoVO.getEmail()) || StringUtils.isBlank(userInfoVO.getAppKey())) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 解密邮箱地址
        String email = AESUtil.decryptAndXOR(userInfoVO.getEmail(), userInfoVO.getAppKey());
        // 1. 查询邮箱地址是否已经被激活
        UserSimpleInfo userSimpleInfo = accountService.findSimpleUserInfoByToken(TokenUtils.getAuthToken(request));
        boolean activated = accountService.checkEmailIsActivated(userSimpleInfo.getUserId(), email);
        // 2. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(activated);
        return responseVO;
    }

    /**
     * 用途: 用户邮箱激活邮件内的激活连接<br>
     * 邮箱激活,邮件中激活链接请求。<br>
     * 邮箱地址不需要加密<br>
     *
     * @param request httprequest对象
     * @return 绑定的结果
     */
    @DisableAuth
    @RequestMapping(value = "/from/mail/activate")
    public ResponseVO activateFormMail(@RequestBody UserInfoVO userInfoVO, HttpServletRequest request) {
        // 1. 参数错误
        if (userInfoVO == null || userInfoVO.getUserId() == null || StringUtils.isBlank(userInfoVO.getCheckCode())
                || StringUtils.isBlank(userInfoVO.getEmail()) || userInfoVO.getActivateType() == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 2. 根据不同的激活类型执行激活操作
        userInfoVO.setIpAddress(getRemoteHost(request));
        if (ActivateTypeEnum.REGISTER_ACTIVATE == userInfoVO.getActivateType()) {
            accountTemporaryService.activateRegisterAccountTemporary(userInfoVO.getUserId(),
                    userInfoVO.getEmail(), userInfoVO.getCheckCode(), userInfoVO.getIpAddress());
        } else {
            accountTemporaryService.activateBindAccountTemporary(userInfoVO.getUserId(),
                    userInfoVO.getEmail(), userInfoVO.getCheckCode(), userInfoVO.getIpAddress());
        }
        // 3. 返回信息
        return new ResponseVO(ReturnMessageEnum.RETURN_OK);
    }

    /**
     * 用途: 密码重置html页面调用(resetpwd.html)<br>
     * 用户密码重置,<br>
     * 1. 密码重置方式有手机号码和邮箱两种方式<br>
     * 2. 如果用手机号码重置密码,APP需要调用这个接口<br>
     * 3. 如果用邮箱地址重置密码,服务器会发送邮件到用户邮箱,用户设置新密码以后html页面请求本接口,进行密码重置<br>
     * 4. 两种方式密码重置都需要验证码<br>
     *
     * @param userInfoVO 用户电话号码、邮箱地址、验证码
     * @param request    httprequest对象
     * @return 重置密码操作结果
     */
    @DisableAuth
    @RequestMapping(value = "/password/reset")
    public ResponseVO resetPassword(@RequestBody UserInfoVO userInfoVO, HttpServletRequest request) {
        // 1. 参数检查
        if (userInfoVO == null || StringUtils.isBlank(userInfoVO.getPwd())
                || StringUtils.isBlank(userInfoVO.getAccount()) || userInfoVO.getAccountType() == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        // 2. 使用手机号码重置密码,appkey不能为空
        if (AccountTypeEnum.MOBILE == userInfoVO.getAccountType() && StringUtils.isBlank(userInfoVO.getAppKey())) {
            throw new YFException(ReturnMessageEnum.APPKEY_IS_INVALID);
        }
        // 3. 密码重置
        UserInfoVO result = accountService.resetPassword(userInfoVO.getAccount(), userInfoVO.getAccountType(), userInfoVO.getPwd(),
                userInfoVO.getCheckCode(), userInfoVO.getAppKey(), getRemoteHost(request));
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(result);
        return responseVO;
    }


    /**
     * TODO 该接口不用,要删除,需求改动,目前只要APP操作绑定邮箱地址或者使用邮箱地址注册,会自动发送激活的邮件
     * 邮箱激活<br>
     * 用途: 邮箱激活的html页面(activate.html)<br>
     * 1. 如果不修改邮箱地址,就直接根据上传的accessToken修改用户的激活状态
     * 2. 如果要修改邮箱地址,服务器就发送一份邮件到用户的邮箱,用户需要点击邮件中的连接来完成激活
     * 3. 邮件中的post请求是:http://域名/coros/account//from/mail/activate.do
     *
     * @param request httprequest对象
     * @return 绑定的结果
     */
//    @RequestMapping(value = "/activate/mail/send")
    public ResponseVO sendActivateMailHtml(HttpServletRequest request, @RequestBody UserInfoVO userInfoVO) {
        // 2. 根据token查询account信息
        Account account = accountService.findAccountByAccessToken(TokenUtils.getAuthToken(request));
        // 3. 帐号绑定,用户修改的邮箱地址不能和原来的邮箱地址相同
//        String newEmail = "";
//        if (userInfoVO != null && StringUtils.isNotBlank(userInfoVO.getNewEmail())
//                && !account.getEmail().equals(userInfoVO.getNewEmail())) {
//            newEmail = userInfoVO.getNewEmail();
//        }
        account.setIpAddress(getRemoteHost(request));
//        accountService.sendActivateMail(account, newEmail);
        // 3. 查询用户信息
        UserInfoVO findUserVO = userInfoService.findAccountAndUserInfoAndTargetByUserId(account.getUserId());
        // 4. 设置返回值
        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(findUserVO);
        return responseVO;
    }


}
