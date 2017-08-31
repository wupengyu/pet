package com.yf.pet.dao.wristband;

import com.yf.pet.entity.wristband.WristbandInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备激活dao层
 * Created by Infi on 17/6/26.
 */
public interface WristbandInfoDao {

    /**
     * 新增设备激活数据
     *
     * @param wristbandInfos 设备数据
     */
    void addWristbandInfos(@Param("list") List<WristbandInfo> wristbandInfos);

    /**
     * 查询设备激活信息
     *
     * @param wristbandInfos 设备信息
     */
    List<WristbandInfo> findWristbandInfos(@Param("list") List<WristbandInfo> wristbandInfos);
}
