package com.yf.pet.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class UserUpdateInfoDto implements Serializable {
    private String mobile;
    private String accessToken;//登陆成功，返回访问的令牌
    private String firstName;
    private String lastName;
    private Integer gender;//性别
    private String headPic;//头像文件
}
