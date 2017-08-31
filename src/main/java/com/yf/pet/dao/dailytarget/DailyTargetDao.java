package com.yf.pet.dao.dailytarget;

import com.yf.pet.entity.dailytarget.DailyTarget;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户目标值记录dao层
 * Created by Infi on 17/3/23.
 */
public interface DailyTargetDao {
    /**
     * 批量新增用户目标值数据
     *
     * @param dailyTargets 多个目标值数据
     */
    void addDailyTarget(@Param("list") List<DailyTarget> dailyTargets);

    /**
     * 修改用户当天的目标值
     *
     * @param dailyTargets 用户目标值对象
     */
    void updateDailyTargets(@Param("list") List<DailyTarget> dailyTargets);


    /**
     * 查询用户某天的目标信息
     *
     * @param userId     用户ID
     * @param happenDate 发送天
     * @return 用户目标值对象
     */
    List<DailyTarget> findDailyTargetsByHappenDate(@Param("userId") Long userId, @Param("happenDate") Integer happenDate);


    /**
     * 查询用户最近的目标值变更记录
     *
     * @param userId 用户ID
     * @return 用户最近一次目标步数
     */
    List<DailyTarget> findLastDailyTargetByUserId(@Param("userId") Long userId);

    /**
     * 查找用户每天最后一条目标变更记录
     *
     * @param userId     用户ID
     * @param happenDate 发送天
     * @return 用户目标值对象
     */
    List<DailyTarget> findDailyTargetsByDayLastData(@Param("userId") Long userId, @Param("happenDate") Integer happenDate);

    /**
     * 查询指定天的最近一次目标值变化记录的日期
     *
     * @param userId     用户ID
     * @param happenDate 发送时间
     * @param targetType 目标值类型
     * @return 最近一个目标值时间
     */
    Integer findRecentHappenDate(@Param("userId") Long userId, @Param("happenDate") Integer happenDate, @Param("targetType") Integer targetType);
    
    /**
     * 查询用户最近一次的目标值信息,目标运动时间、目标运动卡路里
     *
     * @param userId 用户ID
     * @return 用户目标值信息
     */
    List<DailyTarget> findDailyTargetsLast(@Param("userId") Long userId);
}
