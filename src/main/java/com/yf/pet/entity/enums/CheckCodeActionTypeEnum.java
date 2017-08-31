package com.yf.pet.entity.enums;

/**
 * 验证码类型枚举类<br>
 * 枚举说明 枚举类只读<br>
 * RESET_PWD 表示重置密码,<br>
 * REGISTER 表示用户注册,<br>
 * Created by Infi on 17/2/19.
 */
public interface CheckCodeActionTypeEnum {

    /**
     * 注册
     */
    public static final int REGISTER = 1;
    /**
     * 重置密码
     */
    public static final int RESET_PWD = 2;
    /**
     * 邮箱地址激活
     */
    public static final int EMAIL_ACTIVATE = 3;
    /**
     * 帐号绑定
     */
    public static final int MOBILE_BIND = 4;
}
