package com.yf.pet.entity.enums;

/**
 * 帐号注册类型枚举类<br>
 * 枚举说明 枚举类只读<br>
 * MOBILE 表示使用电话号码注册,<br>
 * EMAIL 表示使用email注册,<br>
 * FACEBOOK 表示使用facebook注册,<br>
 * WEIXIN 表示使用微信注册<br>
 * Created by Infi on 17/2/19.
 */
public interface AccountTypeEnum {
    /**
     * 电话号码
     */
    public static final int MOBILE = 1;
    /**
     * 邮箱地址
     */
    public static final int EMAIL = 2;
    /**
     * Facebook
     */
    public static final int FACEBOOK = 3;
    /**
     * 微信
     */
    public static final int WEIXIN = 4;
}
