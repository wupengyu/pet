package com.yf.pet.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户实体对象
 *  wupengyu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long userId;
    private String mobile;
    private String email;
    private String openId;//第三方账户ID
    private Integer openType;//第三方账户类型
    private String pwd;
    private String accessToken;//登陆成功，返回访问的令牌
    private Date validityDate;//令牌的有效期
    private Date createDate;//注册日期
    private Date updateDate;//更新时间
    private Integer registerType;//用户注册方式，1：表示email，2：表示facebook
    private String firstName;
    private String lastName;
    private Integer gender;//性别
    private String headPic;//头像
    private Integer registerTimezone;//注册时区
}
