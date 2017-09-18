package com.yf.pet.pets.daotest;

import com.google.gson.Gson;
import com.yf.pet.pets.dao.IPetsDao;
import com.yf.pet.user.api.dto.PetUpdateResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class PetsDaoTest {

    @Autowired
    private IPetsDao petsDao;

    Gson gson = new Gson();

    @Test
    public void testPetsDao(){
        PetUpdateResultDto petUpdateResultDto=  gson.fromJson("{\n" +
                "        \"nickName\": \"aaa\",\n" +
                "        \"birthday\": \"2017-09-15 14:15:12\",\n" +
                "        \"gender\": 1,\n" +
                "        \"isSterilization\": false,\n" +
                "         \"headPic\":\"band/headpic/42896c610bec4d46a9967cdac63a1e6f.jpg\",\n" +
                "\t\t\"shoulderHeight\":20.1,\n" +
                "\t\t\"weight\":123,\n" +
                "\t\t\"petTypeId\":1,\n" +
                "\t\t\"pedigree\":12.2,\n" +
                "\t\t\"target\":8000\n" +
                "   }\n", PetUpdateResultDto.class);
        petUpdateResultDto.setPetId(new BigInteger("10"));
        petUpdateResultDto.setUserId(new BigInteger("9"));

        petsDao.add(petUpdateResultDto);
    }

}
