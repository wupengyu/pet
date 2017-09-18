package com.yf.pet.pets.dao;

import com.yf.pet.pets.api.dto.PetBreedReturnDto;

import java.util.List;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/17
 */
public interface IPetsBreedDao {
    public List<PetBreedReturnDto> findBreeds();
}
