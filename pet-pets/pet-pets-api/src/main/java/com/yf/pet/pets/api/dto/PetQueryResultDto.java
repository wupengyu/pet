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
 * @date 2017/9/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetQueryResultDto implements Serializable {
    private BigInteger petId;
    private Integer deviceId;
    private String nickName;
    private String headPic;
    private Date birthday;
    private Integer gender;
    private Boolean isSterilization;
    private float shoulderHeight;
    private float weight;
    private Integer petTypeId;
    private float pedigree;
    private Integer target;
    private String deviceNo;
//    private float battery;
    private Integer sim;
    private Date activateTime;
}
