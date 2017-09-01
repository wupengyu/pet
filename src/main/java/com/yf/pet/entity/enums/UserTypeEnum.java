package com.yf.pet.entity.enums;

/**
 * 帐号注册类型枚举类<br>
 * 枚举说明 枚举类只读<br>
 * EMAIL 表示使用email注册,<br>
 * FACEBOOK 表示使用facebook注册,<br>
 * Created by wpy on 17/9/1.
 */
public interface UserTypeEnum {
    /**
     * 邮箱地址
     */
    public static final int EMAIL = 1;
    /**
     * Facebook
     */
    public static final int FACEBOOK = 2;
}
