package com.yf.pet.entity.user.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/1
 */
@Data
public class UserPwdResetVo {
    private String email;
    private String pwd;
    private String newPwd;//新密码
}
