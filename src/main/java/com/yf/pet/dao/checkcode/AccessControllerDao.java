package com.yf.pet.dao.checkcode;

import com.yf.pet.entity.checkcode.AccessController;
import org.apache.ibatis.annotations.Param;

/**
 * 概述: 判断验证码dao层<br>
 * 背景: <br>
 * Created by Infi on 17/3/9.
 */
public interface AccessControllerDao {
    /**
     * 获取验证码,添加一个新的验证码数据到数据库<br>
     *
     * @param accessController 验证码<br>
     */
    void addAccessController(AccessController accessController);

    /**
     * 判断验证码是否正确<br>
     *
     * @param account   电话号码<br>
     * @param checkCode 验证码<br>
     * @return 验证码信息<br>
     */
    AccessController findAccessController(@Param("account") String account, @Param("checkCode") String checkCode);

    /**
     * 查询用户当天验证码校验次数<br>
     *
     * @param account 电话号码<br>
     * @param actionType 验证码类型，1: 手机号码注册，2：重置密码（手机短信或邮件）3：邮箱地址激活，4:绑定帐号
     * @return 返回当天验证码校验次数
     */
    Integer findAccessControllerCountByMobileToday(@Param("account") String account, @Param("actionType") Integer actionType);
}
