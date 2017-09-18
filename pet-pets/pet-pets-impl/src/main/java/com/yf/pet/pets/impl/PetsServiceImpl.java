package com.yf.pet.pets.impl;

import com.yf.pet.common.enums.ServiceModeType;
import com.yf.pet.common.utils.primary.YFPrimaryKeyUtils;
import com.yf.pet.pets.api.dto.PetBreedReturnDto;
import com.yf.pet.pets.api.service.IPetsService;
import com.yf.pet.pets.dao.IPetsBreedDao;
import com.yf.pet.pets.dao.IPetsDao;
import com.yf.pet.user.api.dto.PetQueryResultDto;
import com.yf.pet.user.api.dto.PetUpdateResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("petService")
public class PetsServiceImpl implements IPetsService {
    @Autowired
    private IPetsDao petsDao;

    @Autowired
    private IPetsBreedDao petsBreedDao;

    @Override
    public PetUpdateResultDto add(PetUpdateResultDto petUpdateResultDto) {
        //生成ID
        Long petId = YFPrimaryKeyUtils.getId(ServiceModeType.PETS);
        petUpdateResultDto.setPetId(BigInteger.valueOf(petId));
        petsDao.add(petUpdateResultDto);
        return petUpdateResultDto;
    }

    @Override
    public PetUpdateResultDto update(PetUpdateResultDto petUpdateResultDto) {
        petsDao.update(petUpdateResultDto);
        return petUpdateResultDto;
    }

    @Override
    public List<PetQueryResultDto> list(BigInteger userId) {
        return petsDao.list(userId);
    }

    @Override
    public void delete(BigInteger petId) {
        petsDao.delete(petId);
    }

    @Override
    public List<PetBreedReturnDto> findBreeds() {
        return petsBreedDao.findBreeds();
    }

    @Override
    public PetQueryResultDto query(BigInteger userId, BigInteger petId) {
        return petsDao.query(userId,petId);
    }
}
