package com.yf.pet.service;


import com.yf.pet.entity.wristband.WristbandInfo;

import java.util.List;

/**
 * 设备信息保存service层,保存激活和使用信息
 * Created by Infi on 17/6/26.
 */
public interface WristbandService {
    /**
     * 保存设备使用或激活信息
     *
     * @param wristbands 设备信息
     */
    void saveWristbands(List<WristbandInfo> wristbands);

    /**
     * 保存设备使用信息
     *
     * @param wristband 设备信息
     */
    void saveWristbandUser(WristbandInfo wristband);

}
