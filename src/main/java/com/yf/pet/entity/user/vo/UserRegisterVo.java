package com.yf.pet.entity.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/1
 */
@Data
public class UserRegisterVo {
    private String mobile;
    private String email;
    private String openId;//第三方账户ID
    private Integer openType;//第三方账户类型
    private String openName;//第三方账户昵称
    private String pwd;
    private String ipAddress;//最后登录的IP地址
    private String phoneId;//手机设备ID，相当于收集Mac地址之类的唯一标识符
    private Integer registerType;//用户注册方式，1：表示email，2：表示facebook
    private Integer registerTimezone;//注册时区
}