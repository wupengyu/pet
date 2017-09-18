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
public class PetUpdateResultDto implements Serializable {
    private BigInteger petId;
    private BigInteger userId;
    private String nickName;
    private String headPic;
    private Date birthday;
    private Integer gender;
    private Boolean isSterilization;
    private float shoulderHeight;
    private float weight;
    private Integer petBreedId;
    private float pedigree;
    private Integer target;
}
