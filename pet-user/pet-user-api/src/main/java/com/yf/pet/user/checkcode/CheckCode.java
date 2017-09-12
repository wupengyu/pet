package com.yf.pet.user.checkcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: 验证码</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckCode implements Serializable {

    private static final long serialVersionUID = -8619649336163420301L;
    private String checkCode;//验证码
    private Date createDate;//创建时间
    private Integer type;//验证码类型，1: 邮件重置密码
    private String email;//用户邮箱
}
