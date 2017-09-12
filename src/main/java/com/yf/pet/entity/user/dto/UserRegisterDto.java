package com.yf.pet.entity.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserRegisterDto {
    private String email;
    private String pwd;
    private Integer registerTimezone;//注册时区

}
