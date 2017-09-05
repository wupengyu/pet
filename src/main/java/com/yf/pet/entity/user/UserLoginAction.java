package com.yf.pet.entity.user;

import java.util.Date;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/5
 */
public class UserLoginAction {
    private String id;
    private String userId;
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
