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
public class UserPwdResetDto {
    private String email;
    private String pwd;
    private String newPwd;//新密码
}
