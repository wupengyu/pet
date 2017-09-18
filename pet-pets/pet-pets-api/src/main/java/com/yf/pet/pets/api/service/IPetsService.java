package com.yf.pet.pets.api.service;

import com.yf.pet.pets.api.dto.PetBreedReturnDto;
import com.yf.pet.user.api.dto.PetQueryResultDto;
import com.yf.pet.user.api.dto.PetUpdateResultDto;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/16
 */
public interface IPetsService {

    public PetUpdateResultDto add(PetUpdateResultDto petUpdateResultDto);

    public PetUpdateResultDto update(PetUpdateResultDto petUpdateResultDto);

    public List<PetQueryResultDto> list(BigInteger userId);

    public void delete (BigInteger petId);

    public List<PetBreedReturnDto>  findBreeds();

    public PetQueryResultDto query(BigInteger userId, BigInteger petId);
}
