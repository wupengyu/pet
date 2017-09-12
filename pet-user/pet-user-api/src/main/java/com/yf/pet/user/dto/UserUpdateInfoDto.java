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
 * @date 2017/9/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateInfoDto {
    private String mobile;
    private String accessToken;//登陆成功，返回访问的令牌
    private String firstName;
    private String lastName;
    private Integer gender;//性别
    private byte[] headPicFile;//头像文件
}