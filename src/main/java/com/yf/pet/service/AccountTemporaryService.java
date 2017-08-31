package com.yf.pet.service;


import com.yf.pet.entity.user.Account;
import com.yf.pet.entity.user.vo.UserInfoVO;

import java.io.IOException;

/**
 * 临时帐号信息接口
 * Created by Infi on 17/3/22.
 */
public interface AccountTemporaryService {
    /**
     * 新增临时帐号信息,邮箱地址注册,先增加临时帐号
     *
     * @param userInfoVO 新增帐号信息,包括account信息和userinfo信息
     * @return 帐号信息
     */
    void registerAccountTemporary(UserInfoVO userInfoVO) throws IOException;

    /**
     * 绑定邮箱地址,绑定后先保存在临时表,激活后保存到用户表
     *
     * @param userId    用户ID(绑定目标用户ID)
     * @param email     新增帐号信息,邮箱地址
     * @param ipAddress ip地址
     * @param pwd       密码
     * @return 帐号信息
     */
    void bindAccountTemporary(Long userId, String email, String ipAddress, String pwd);

    /**
     * 邮箱注册的帐号激活
     *
     * @param userId    用户ID
     * @param email     用户新邮箱地址
     * @param checkCode 验证码
     * @param ipAddress IP地址
     * @return 返回校验的短信ID
     */
    void activateRegisterAccountTemporary(Long userId, String email, String checkCode, String ipAddress);

    /**
     * 邮箱绑定的帐号激活
     *
     * @param userId    用户ID
     * @param email     用户新邮箱地址
     * @param checkCode 验证码
     * @param ipAddress IP地址
     */
    void activateBindAccountTemporary(Long userId, String email, String checkCode, String ipAddress);

    /**
     * 查询最近一个未激活的邮箱地址
     *
     * @param userId 用户ID
     * @return 最近一个未激活的邮箱地址
     */
    String findLastEmailByUserId(Long userId);

    /**
     * 根据邮箱地址查询临时帐号信息
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @return 临时帐号信息
     */
    Account findAccountTemporaryByUserIdAndEmail(Long userId, String email);

}
