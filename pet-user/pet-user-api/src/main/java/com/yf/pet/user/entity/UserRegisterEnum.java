package com.yf.pet.user.entity;

/**
 * 帐号注册类型枚举类<br>
 * 枚举说明 枚举类只读<br>
 * EMAIL 表示使用email注册,<br>
 * OPENID 表示使用OPENID注册,<br>
 * Created by wpy on 17/9/1.
 */
public interface UserRegisterEnum {
    /**
     * 邮箱地址
     */
    public static final int EMAIL = 1;
    /**
     * OPENID
     */
    public static final int OPENID = 2;
}
