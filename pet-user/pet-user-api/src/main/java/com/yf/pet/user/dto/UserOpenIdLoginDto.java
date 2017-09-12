package com.yf.pet.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class UserOpenIdLoginDto {

    private String openId;//第三方账户ID
    private Integer openType;//第三方账户类型
    private String email;//邮箱
    private Boolean openEmail;//是否是从第三方获取到的email: true-是，false-否
    private String firstName;
    private String lastName;
    private Integer gender;//性别
    private byte[] headPicFile;//头像文件
    private String mobile;//手机
    private Integer registerTimezone;//注册时区
}
