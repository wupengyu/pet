/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.entity.enums;

/**
 * 标签类型:1:用户模块，2:标签,运动数据模块
 *
 * @author Infi
 */
public interface ServiceModeType {
    /**
     * 用户服务模块
     */
    public static final int USER = 1;
    /**
     * 标签,运动数据服务模块,日常服务模块
     */
    public static final int LABEL = 2;
    /**
     * 自行车导航团队服务模块
     */
    public static final int BIKE_TEAM = 3;
    /**
     * 自行车路书服务模块
     */
    public static final int BIKE_ROUTE = 4;
    /**
     * 头盔项目sos短信信息
     */
    public static final int SOS_RECORD = 5;
    /**
     * 设备信息信息
     */
    public static final int WRISTBAND = 6;
    /**
     * 固件信息
     */
    public static final int FIRMWARE = 7;
    /**
     * 问题反馈信息
     */
    public static final int FEEDBACK = 8;

}
