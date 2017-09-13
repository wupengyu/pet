package com.yf.pet.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>Description: 找回密码dto</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForgetPwdDto implements Serializable {
    private String email;
}
