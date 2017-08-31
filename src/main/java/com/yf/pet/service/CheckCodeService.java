package com.yf.pet.service;


import com.yf.pet.entity.checkcode.CheckCode;
import com.yf.pet.entity.checkcode.CheckCodeCriteria;

/**
 * 概述: 获取验证码,校验验证码service层<br>
 * 背景: <br>
 * Created by Infi on 17/3/9.
 */
public interface CheckCodeService {
    /**
     * 获取验证码,新增一条验证码数据<br>
     *
     * @param checkCodeCriteria 验证码<br>
     *                          mobile: 电话号码<br>
     *                          actionType: 验证码类型,1: 手机号码注册，2：重置密码（手机短信或邮件）3：邮箱地址激活，<br>
     * @return 获取验证码的结果<br>
     */
    void createCheckCode(CheckCodeCriteria checkCodeCriteria);

    /**
     * 校验验证码,mobile和email是加密过的<br>
     *
     * @param checkCodeCriteria 验证码对象
     *                          mobile    电话号码<br>
     *                          checkCode 验证码<br>
     *                          actionType 验证码类型,1: 手机号码注册，2：重置密码（手机短信或邮件）3：邮箱地址激活，
     * @return 验证校验结果<br>
     */
    boolean verifyCheckCodeByAccountAES(CheckCodeCriteria checkCodeCriteria);

    /**
     * 校验验证码,mobile和email是没有加密过的<br>
     *
     * @param account       帐号,电话号码或者邮箱地址,首先电话号码
     * @param checkCode     验证码
     * @param actionType    验证码类型,1: 手机号码注册，2：重置密码（手机短信或邮件）3：邮箱地址激活，4:绑定手机号码
     * @param userId        用户ID,用户激活邮箱地址
     * @return 验证校验结果<br>
     */
    boolean verifyCheckCodeByAccount(String account, String checkCode, Integer actionType, Long userId);

    /**
     * 发送邮箱验证的验证邮件
     * @param userId    用户ID
     * @param email     用户邮箱地址
     * @param ipAddress 用户IP地址
     * @param activateType 激活类型。1:注册邮箱激活,2:绑定邮箱激活
     */
    void sendCheckCodeByEmailActivate(Long userId, String email, String ipAddress, Integer activateType);

    /**
     * 密码重置发送短信验证码或者密码重置邮件
     * @param email     邮箱地址
     * @param ipAddress 用户IP地址
     * @param appKey    密钥
     */
    void sendCheckCodeOrMailByRestPwd(String email, String ipAddress, String appKey);

    /**
     * 查询用户最后一次获取验证码的信息<br>
     *
     * @param account 电话号码,或者邮箱地址 <br>
     * @param type    短信类型,1: 手机号码注册，2：重置密码（手机短信或邮件）3：邮箱地址激活，
     * @param userId  用户ID,邮箱地址绑定时用到
     * @return 用户最后一次获取的验证码信息<br>
     */
    CheckCode findLastCheckCodeByAccount(String account, Integer type, Long userId);

    /**
     * 根据帐号信息,修改验证码状态,valid:1:表示有效,0:表示无效
     *
     * @param account    帐号
     * @param actionType 验证码类型:1:注册,2:重置密码,3:邮箱激活,4:绑定帐号
     * @param valid      验证码状态.valid:1:表示有效,0:表示无效
     * @param userId     用户ID,用户邮箱激活类型的验证码
     */
    void updateCheckCodeValidByAccount(String account, Integer actionType,
                                       Integer valid, Long userId);


}
