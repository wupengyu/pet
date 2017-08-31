package com.yf.pet.dao.checkcode;

import com.yf.pet.entity.checkcode.CheckCode;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 概述: 获取验证码dao层<br>
 * 背景: <br>
 * Created by Infi on 17/3/9.
 */
public interface CheckCodeDao {
    /**
     * 获取验证码,新增一条验证码数据<br>
     *
     * @param checkCode 验证码<br>
     * @param userId
     */
    void addCheckCode(@Param("account") String account, @Param("checkCode") String checkCode, @Param("createDate") Date createDate,
                      @Param("type") Integer type, @Param("userId") Long userId);

    /**
     * 查询用户最后一次获取验证码的信息<br>
     *
     * @param account   电话号码,或者邮箱地址 <br>
     * @param type      短信类型,1: 手机号码注册，2：重置密码（手机短信或邮件）3：邮箱地址激活，
     * @param userId    用户ID,邮箱地址绑定时用到
     * @return 用户最后一次获取的验证码信息<br>
     */
    CheckCode findLastCheckCodeByAccount(@Param("account") String account, @Param("type") Integer type, @Param("userId") Long userId);

    /**
     * 用户当天获取验证码的次数,区分验证码类型
     *
     * @param account 电话号码,或者邮箱地址<br>
     * @param type 验证码类型，1: 手机号码注册，2：重置密码（手机短信或邮件）3：邮箱地址激活，4:绑定帐号
     * @return 当天获取验证码的次数<br>
     */
    Integer findCheckCodeCountToday(@Param("account") String account, @Param("type") Integer type);

    /**
     * 修改验证码状态,valid:1:表示有效,0:表示无效
     *
     * @param valid 验证码状态.valid:1:表示有效,0:表示无效
     * @param id    验证码ID
     */
    void updateCheckCodeValid(@Param("valid") Integer valid, @Param("id") Integer id);

    /**
     * 根据帐号信息,修改验证码状态,valid:1:表示有效,0:表示无效
     *
     * @param account    帐号
     * @param actionType 验证码类型:1:注册,2:重置密码,3:邮箱激活,4:绑定帐号
     * @param valid      验证码状态.valid:1:表示有效,0:表示无效
     * @param userId     用户ID,用户邮箱激活类型的验证码
     */
    void updateCheckCodeValidByAccount(@Param("account") String account, @Param("type") Integer actionType,
                                       @Param("valid") Integer valid, @Param("userId") Long userId);

    /**
     * 发送激活、修改密码邮件以后,把之前的邮件验证码设置为无效
     *
     * @param account    帐号
     * @param actionType 验证码类型。1:注册,2:重置密码,3:邮箱激活,4:绑定帐号
     * @param id         最后一个验证码的ID
     */
    void updateCheckCodeInValidBeforeSendMail(@Param("account") String account, @Param("type") Integer actionType,
                                              @Param("id") Integer id);

}
