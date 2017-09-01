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
public class UserEmailLoginVo {

    private String email;
    private String pwd;
    private String ipAddress;//最后登录的IP地址
    private String phoneId;//手机设备ID，相当于收集Mac地址之类的唯一标识符
}
