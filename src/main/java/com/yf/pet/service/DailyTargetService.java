package com.yf.pet.service;


import com.yf.pet.entity.dailytarget.DailyTarget;

import java.util.List;

/**
 * 用户目标值记录service层
 * Created by Infi on 17/3/23.
 */
public interface DailyTargetService {
    /**
     * 批量新增用户目标值数据
     * @param userId            用户ID
     * @param userTimezone 用户当前所在时区
     * @param targetMotionTime  目标运动时间,(单位:秒)
     * @param targetCalorie     目标运动卡路里(小卡)
     */
    void addDailyTarget(Long userId, Integer userTimezone, Integer targetMotionTime, Integer targetCalorie);

    /**
     * 查询用户最近一次的目标值信息,目标运动时间、目标运动卡路里
     *
     * @param userId 用户ID
     * @return 用户目标值信息
     */
    List<DailyTarget> findDailyTargetsLast(Long userId);

    /**
     * 查询用户某天的目标信息
     *
     * @param userId     用户ID
     * @param happenDate 发送天
     * @return 用户目标值对象
     */
    List<DailyTarget> findDailyTargetByHappenDate(Long userId, Integer happenDate);

    /**
     * 查询指定天以后用户所有的目标更新记录,按照happenDate倒序排列
     *
     * @param userId     用户ID
     * @param happenDate 发送天
     * @return 用户目标值对象
     */
    List<DailyTarget> findDailyTargetsByGTHappenDate(Long userId, Integer happenDate);

    /**
     * 查询用户一周卡路里总目标值
     *
     * @param userId         用户ID
     * @param firstDayOfWeek 周首日,默认0表示周日为一周首日
     * @param timezone       用户所在的时区
     * @return 用户一周总目标值
     */
    Integer findDailyTargetCalorieInWeekInWeek(Long userId, Integer firstDayOfWeek, Integer timezone);

}
