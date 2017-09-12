package com.yf.pet.user.service;

import com.yf.pet.user.dto.*;
import com.yf.pet.user.entity.User;

import java.io.IOException;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/12
 */
public interface IUserService {
    /**
     * 邮箱用户注册
     *
     * @param userRegisterDto
     * @return
     */
    public User emailRegister(UserRegisterDto userRegisterDto);

    /**
     * 用户email登录
     *
     * @param userEmailLoginDto
     * @return
     */
    public User emailLogin(UserEmailLoginDto userEmailLoginDto);

    /**
     * 第三方账户登录
     *
     * @param userOpenIdLoginDto
     * @return
     */
    public User openLogin(UserOpenIdLoginDto userOpenIdLoginDto) throws IOException;

    /**
     * 查询账号是否已经存在
     *
     * @param
     * @return
     */
    public Boolean findAccountIsExist(String email, String openId);

    /**
     * 验证accessToken是否有效
     *
     * @param accessToken accessToken
     * @return 用户ID, token过期时间信息
     */
    public User findAccessTokenIsValid(String accessToken);

    /**
     * 重置密码
     *
     * @param userPwdResetDto
     */
    public void pwdreset(UserPwdResetDto userPwdResetDto);

    /**
     * 根据验证码修改密码
     *
     * @param userPwdResetDto
     */
    public void resetPwdByCode(UserPwdResetDto userPwdResetDto);

    /**
     * 登出
     *
     * @param accessToken
     */
    public void loginOut(String accessToken);

    /**
     * 获取用户信息
     *
     * @param accessToken
     */
    public User getUserInfo(String accessToken);

    /**
     * 修改用户信息
     *
     * @param userUpdateInfoDto
     * @return
     * @throws IOException
     */
    public User updateUserInfo(UserUpdateInfoDto userUpdateInfoDto) throws IOException;

    /**
     * 发送邮件重置密码
     *
     * @param email
     */
    public void sendEmailResetPwd(String email);
}
