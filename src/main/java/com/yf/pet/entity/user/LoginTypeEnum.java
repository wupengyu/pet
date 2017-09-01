package com.yf.pet.entity.user;


/**
 * 帐号登录类型枚举类<br>
 * 枚举说明 枚举类只读<br>
 * EMAIL 表示使用email登录,<br>
 * OPENID 表示使用第三方账户登录,<br>
 * Created by wpy on 17/9/1.
 */
public interface LoginTypeEnum {
    /**
     * 邮箱地址
     */
    public static final int EMAIL = 1;
    /**
     * Facebook
     */
    public static final int OPENID = 2;
}
