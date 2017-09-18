package com.yf.pet.pets.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetQueryDto implements Serializable {
    private BigInteger petId;
}
