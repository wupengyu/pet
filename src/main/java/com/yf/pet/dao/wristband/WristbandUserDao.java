package com.yf.pet.dao.wristband;

import com.yf.pet.entity.wristband.WristbandInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备使用dao层
 * Created by Infi on 17/6/26.
 */
public interface WristbandUserDao {

    /**
     * 新增设备使用数据
     *
     * @param wristbandInfos 设备数据
     */
    void addWristbandUsers(@Param("list") List<WristbandInfo> wristbandInfos);

    /**
     * 查询设备使用信息
     *
     * @param wristbandInfos 设备信息
     */
    List<WristbandInfo> findWristbandUserList(@Param("list") List<WristbandInfo> wristbandInfos);

    /**
     * 查询设备使用信息
     *
     * @param userId 用户ID
     * @param deviceId 设备ID
     * @return 用户设备信息
     */
    WristbandInfo findWristbandUser(@Param("userId") Long userId, @Param("deviceId") String deviceId);

    /**
     * 更新设备使用信息
     *
     * @param wristbandInfos 设备信息
     */
    void updateWristbandUsers(@Param("list") List<WristbandInfo> wristbandInfos);
}
