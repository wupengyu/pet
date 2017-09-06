package com.yf.pet.entity.user.dto;

import lombok.Data;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/1
 */
@Data
public class UserOpenIdLoginDto {

    private String openId;//第三方账户ID
    private Integer openType;//第三方账户类型
//    private String openName;//第三方账户昵称
    private String pwd;
//    private String ipAddress;//最后登录的IP地址
//    private String phoneId;//手机设备ID，相当于收集Mac地址之类的唯一标识符
    private String firstName;
    private String lastName;
    private Integer gender;//性别
    private String headPic;//头像
    private String mobile;//手机
    private Integer registerTimezone;//注册时区
}
