package com.yf.pet.web.controller;

import com.yf.pet.common.ResponseVO;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.cache.RedisUtilsPet;
import com.yf.pet.common.exception.YFException;
import com.yf.pet.pets.api.dto.PetBreedReturnDto;
import com.yf.pet.pets.api.dto.PetQueryDto;
import com.yf.pet.pets.api.service.IPetsService;
import com.yf.pet.user.api.dto.PetDeleteDto;
import com.yf.pet.user.api.dto.PetQueryResultDto;
import com.yf.pet.user.api.dto.PetUpdateResultDto;
import com.yf.pet.user.api.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Description: 宠物信息管理</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/15
 */
@RestController
@RequestMapping(value = "/pets", method = RequestMethod.POST)
public class PetController {
    /**
     * log 日志
     */
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IPetsService petsService;

    /**
     * 添加宠物
     *
     * @param petUpdateResultDto
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseVO add(@RequestBody PetUpdateResultDto petUpdateResultDto, HttpServletRequest request) throws Exception {
        log.info("新增宠物：" + petUpdateResultDto.toString());

        // 检查参数
        if (petUpdateResultDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }

        User user = RedisUtilsPet.getUserByRequest(request);
        petUpdateResultDto.setUserId(user.getUserId());
        petUpdateResultDto = petsService.add(petUpdateResultDto);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(petUpdateResultDto);
        return responseVO;
    }

    /**
     * 查询宠物列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", produces = "application/json")
    public ResponseVO findList(HttpServletRequest request) throws Exception {
        //取出userId
        User user = RedisUtilsPet.getUserByRequest(request);
        log.info("查询宠物列表：" + user.getUserId());

        List<PetQueryResultDto> ls = petsService.list(user.getUserId());

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(ls);
        return responseVO;
    }

    /**
     * 删除宠物
     *
     * @param petDeleteDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public ResponseVO delete(@RequestBody PetDeleteDto petDeleteDto) throws Exception {
        log.info("删除宠物：" + petDeleteDto.toString());
        // 检查参数
        if (petDeleteDto == null || petDeleteDto.getPetId() == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        petsService.delete(petDeleteDto.getPetId());

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        return responseVO;
    }

    /**
     * 修改宠物信息
     *
     * @param petUpdateResultDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseVO update(@RequestBody PetUpdateResultDto petUpdateResultDto) throws Exception {
        log.info("修改宠物信息：" + petUpdateResultDto.toString());
        // 检查参数
        if (petUpdateResultDto == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        petsService.update(petUpdateResultDto);

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(petUpdateResultDto);
        return responseVO;
    }

    /**
     * 查询宠物信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query", produces = "application/json")
    public ResponseVO query(@RequestBody PetQueryDto petQueryDto, HttpServletRequest request) throws Exception {
        //参数检验
        if (petQueryDto == null || petQueryDto.getPetId() == null) {
            throw new YFException(ReturnMessageEnum.PARAMETER_NULL);
        }

        User user = RedisUtilsPet.getUserByRequest(request);
        PetQueryResultDto petQueryResultDto = petsService.query(user.getUserId(), petQueryDto.getPetId());

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(petQueryResultDto);
        return responseVO;
    }

    /**
     * 查询宠物种类
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/breeds/query", produces = "application/json")
    public ResponseVO findKinds() throws Exception {

        List<PetBreedReturnDto> ls = petsService.findBreeds();

        ResponseVO responseVO = new ResponseVO(ReturnMessageEnum.RETURN_OK);
        responseVO.setData(ls);
        return responseVO;
    }

}
