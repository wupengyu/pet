package com.yf.pet.user.api.dto;

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
 * @date 2017/9/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReturnDto implements Serializable {
    private BigInteger userId;
    private String mobile;
    private String email;
    private String accessToken;//登陆成功，返回访问的令牌
    private Date validityDate;//令牌的有效期
    private String firstName;
    private String lastName;
    private Integer gender;//性别
    private String headPic;//头像
}
