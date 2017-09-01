package com.yf.pet.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体对象
 *  wupengyu
 */
@Data
public class User {

    private Integer userId;
    private String mobile;
    private String email;
    private String facebookId;
    private String facebookName;//facebook昵称
    private String pwd;
    private String accessToken;//登陆成功，返回访问的令牌
    private Date validityDate;//令牌的有效期
//    private Integer clientType;//客户端类型。1-Android，2-iOS
    private Integer loginType;//登录类型 1：表示email，2：表示facebook
    private String ipAddress;//最后登录的IP地址
    private Date createDate;//注册日期
    private Date updateDate;//更新时间
    private Integer registerType;//用户注册方式，1：表示email，2：表示facebook
    private Integer activateStatus;//获取是否激活，用于邮箱激活功能
    private String firstName;
    private String lastName;
    private Integer gender;//性别
    private String headPic;//头像
    private Integer registerTimezone;//注册时区
}
