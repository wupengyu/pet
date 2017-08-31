package com.yf.pet.entity.daily;

import java.util.Date;
import java.util.List;

/**
 * Created by Infi on 17/4/7.
 */
public class DailySumData {
    private Long id;
    private Integer userId;
    private Date happenDate;
    private Integer totalCalorie;
    private Integer totalStep;
    private Integer totalDistance;
    private Integer totalMotionTime;
    private Integer targetMotionTime;
    private Integer targetCalorie;
    private String energyLine;
    private String motionTimeLine;
    private String stepLine;
    private Integer status;
    private Date createTime;
    private List<HeartRateStatisticsData> heartRateList;
    private List<SleepData> sleepList;

    /**
     * 获取睡觉数据
     *
     * @return heartRateList 睡觉数据
     */
    public List<HeartRateStatisticsData> getHeartRateList() {
        return heartRateList;
    }

    /**
     * 设置睡觉数据
     *
     * @param heartRateList 睡觉数据
     */
    public void setHeartRateList(List<HeartRateStatisticsData> heartRateList) {
        this.heartRateList = heartRateList;
    }

    /**
     * 获取睡觉数据
     *
     * @return sleepList 睡觉数据
     */
    public List<SleepData> getSleepList() {
        return sleepList;
    }

    /**
     * 设置睡觉数据
     *
     * @param sleepList 睡觉数据
     */
    public void setSleepList(List<SleepData> sleepList) {
        this.sleepList = sleepList;
    }

    /**
     * 获取运动时间合计
     *
     * @return totalMotionTime 运动时间合计
     */
    public Integer getTotalMotionTime() {
        return totalMotionTime;
    }

    /**
     * 设置运动时间合计
     *
     * @param totalMotionTime 运动时间合计
     */
    public void setTotalMotionTime(Integer totalMotionTime) {
        this.totalMotionTime = totalMotionTime;
    }

    /**
     * 获取主键,自增长
     *
     * @return id 主键,自增长
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键,自增长
     *
     * @param id 主键,自增长
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return userId 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取发生日期,精确到天，如：2017-04-06 00:00:00
     *
     * @return happenDate 发生日期,精确到天，如：2017-04-06 00:00:00
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置发生日期,精确到天，如：2017-04-06 00:00:00
     *
     * @param happenDate 发生日期,精确到天，如：2017-04-06 00:00:00
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取消费总卡路里
     *
     * @return totalCalorie 消费总卡路里
     */
    public Integer getTotalCalorie() {
        return totalCalorie;
    }

    /**
     * 设置消费总卡路里
     *
     * @param totalCalorie 消费总卡路里
     */
    public void setTotalCalorie(Integer totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    /**
     * 获取消耗总步数
     *
     * @return totalStep 消耗总步数
     */
    public Integer getTotalStep() {
        return totalStep;
    }

    /**
     * 设置消耗总步数
     *
     * @param totalStep 消耗总步数
     */
    public void setTotalStep(Integer totalStep) {
        this.totalStep = totalStep;
    }

    /**
     * 获取总距离(米）
     *
     * @return totalDistance 总距离(米）
     */
    public Integer getTotalDistance() {
        return totalDistance;
    }

    /**
     * 设置总距离(米）
     *
     * @param totalDistance 总距离(米）
     */
    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    /**
     * 获取目标运动时间（秒）
     *
     * @return targetMotionTime 目标运动时间（秒）
     */
    public Integer getTargetMotionTime() {
        return targetMotionTime;
    }

    /**
     * 设置目标运动时间（秒）
     *
     * @param targetMotionTime 目标运动时间（秒）
     */
    public void setTargetMotionTime(Integer targetMotionTime) {
        this.targetMotionTime = targetMotionTime;
    }

    /**
     * 获取目标卡路里（小卡）
     *
     * @return targetCalorie 目标卡路里（小卡）
     */
    public Integer getTargetCalorie() {
        return targetCalorie;
    }

    /**
     * 设置目标卡路里（小卡）
     *
     * @param targetCalorie 目标卡路里（小卡）
     */
    public void setTargetCalorie(Integer targetCalorie) {
        this.targetCalorie = targetCalorie;
    }

    /**
     * 获取能量柱状图，半小时一个刻度，一天有48个刻度。
     *
     * @return energyLine 能量柱状图，半小时一个刻度，一天有48个刻度。
     */
    public String getEnergyLine() {
        return energyLine;
    }

    /**
     * 设置能量柱状图，半小时一个刻度，一天有48个刻度。
     *
     * @param energyLine 能量柱状图，半小时一个刻度，一天有48个刻度。
     */
    public void setEnergyLine(String energyLine) {
        this.energyLine = energyLine;
    }

    /**
     * 获取运动时长柱状图，半小时一个刻度，一天有48个刻度。
     *
     * @return motionTimeLine 运动时长柱状图，半小时一个刻度，一天有48个刻度。
     */
    public String getMotionTimeLine() {
        return motionTimeLine;
    }

    /**
     * 设置运动时长柱状图，半小时一个刻度，一天有48个刻度。
     *
     * @param motionTimeLine 运动时长柱状图，半小时一个刻度，一天有48个刻度。
     */
    public void setMotionTimeLine(String motionTimeLine) {
        this.motionTimeLine = motionTimeLine;
    }

    /**
     * 获取步数柱状图，半小时一个刻度，一天有48个刻度。
     *
     * @return stepLine 步数柱状图，半小时一个刻度，一天有48个刻度。
     */
    public String getStepLine() {
        return stepLine;
    }

    /**
     * 设置步数柱状图，半小时一个刻度，一天有48个刻度。
     *
     * @param stepLine 步数柱状图，半小时一个刻度，一天有48个刻度。
     */
    public void setStepLine(String stepLine) {
        this.stepLine = stepLine;
    }


    /**
     * 获取状态，1：表示有效，0：表示无效
     *
     * @return status 状态，1：表示有效，0：表示无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态，1：表示有效，0：表示无效
     *
     * @param status 状态，1：表示有效，0：表示无效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return createTime 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
