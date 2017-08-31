package com.yf.pet.dao.checkcode;

import com.yf.pet.entity.checkcode.CheckCode;
import com.yf.pet.entity.checkcode.CodeRequestLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * ∂
 * 概述: 验证码请求日志dao层<br>
 * 背景: <br>
 * Created by Infi on 17/3/9.
 */
public interface CodeRequestLogDao {
    /**
     * 保存验证码请求记录<br>
     *
     * @param codeRequestLog 验证码请求对象<br>
     */
    void addCodeRequestLog(CodeRequestLog codeRequestLog);

    /**
     * 查询验证码请求记录<br>
     *
     * @param account    电话号码<br>
     * @param createDate 创建时间<br>
     * @return 验证码信息<br>
     */
    CheckCode findCodeRequestLog(@Param("account") String account, @Param("createDate") Date createDate);

    /**
     * 查询用户当天获取到的验证码次数
     *
     * @param account 电话号码
     * @param tag     调用类型(0：获取验证码,1:调用登录接口)
     * @return 获取验证码次数
     */
    Integer findCodeRequestLogNumByMobileAndDay(@Param("account") String account, @Param("tag") Integer tag);

    /**
     * 查询一个IP当天获取的验证码次数
     *
     * @param remoteIPNum IP地址的long类型
     * @param actionType 验证码类型，1: 手机号码注册，2：重置密码（手机短信或邮件）3：邮箱地址激活，4:绑定帐号
     * @return 获取验证码的次数
     */
    Integer findCountByIPNumAndDay(@Param("remoteIPNum") Long remoteIPNum, @Param("actionType") Integer actionType);

}
