/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.entity.enums;

/**
 * 标签类型:1:摘下，2:睡眠，3:步行，4:跑步，5:低运动量，6:运动，7:快走,8:跑步训练,9:骑行,10:游泳
 * 其中,8:跑步训练,9:骑行。这三种运动类型有动态心率
 *
 * @author Infi
 */
public interface LabelModeEnum {

    /**
     * 摘下
     */
    public static final int TAKEOFF = 1;
    /**
     * 睡眠
     */
    public static final int SLEEP = 2;
    /**
     * 步行
     */
    public static final int WALK = 3;
    /**
     * 跑步
     */
    public static final int RUN = 4;
    /**
     * 低运动量
     */
    public static final int SLOWMOTION = 5;
    /**
     * 运动
     */
    public static final int MOTION = 6;
    /**
     * 快走
     */
    public static final int FASTWALK = 7;
    /**
     * 跑步训练
     */
    public static final int RUNTRAIN = 8;
    /**
     * 骑行
     */
    public static final int RIDING = 9;
    /**
     * 游泳
     */
    public static final int SWIM = 10;
}
