package com.yf.pet.user.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginAction implements Serializable  {
    private BigInteger id;
    private BigInteger userId;
    private String accessToken;
    private Integer loginType;
    private String ipAddress;//最后登录的IP地址
    private String macAddress;//手机mac地址
    private Integer phoneType;
    private String phoneModel;
    private String osVer;
    private String appVer;
    private Date actionTime;
    private String logLat;
    private Integer action;
}
