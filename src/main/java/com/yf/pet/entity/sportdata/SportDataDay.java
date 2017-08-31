package com.yf.pet.entity.sportdata;

import java.util.Date;

/**
 * 运动数据天粒度统计对象
 * Created by Infi on 17/6/4.
 */
public class SportDataDay {
    private Long userId;
    private Integer happenDate;
    private int calorieRun;
    private int calorieRiding;
    private int calorieSwim;
    private double distanceRun;
    private double distanceRiding;
    private double distanceSwim;
    private int motionDurationRun;
    private int motionDurationRiding;
    private int motionDurationSwim;
    private int stepRun;
    private Date createTime;
    private int calorieTotal;
    private double distanceTotal;
    private int motionDurationTotal;
    private int stepTotal;
    private Integer happenYear;
    private Integer happenMonth;
    private Integer happenWeekDay;

    /**
     * 获取发生日期,精确到年，如：2017
     *
     * @return happenYear 发生日期,精确到年，如：2017
     */
    public Integer getHappenYear() {
        return happenYear;
    }

    /**
     * 设置发生日期,精确到年，如：2017
     *
     * @param happenYear 发生日期,精确到年，如：2017
     */
    public void setHappenYear(Integer happenYear) {
        this.happenYear = happenYear;
    }

    /**
     * 获取发生日期,精确到月，如：201704
     *
     * @return happenMonth 发生日期,精确到月，如：201704
     */
    public Integer getHappenMonth() {
        return happenMonth;
    }

    /**
     * 设置发生日期,精确到月，如：201704
     *
     * @param happenMonth 发生日期,精确到月，如：201704
     */
    public void setHappenMonth(Integer happenMonth) {
        this.happenMonth = happenMonth;
    }

    /**
     * 获取发生日期,周首日，如：20170406
     *
     * @return happenWeekDay 发生日期,周首日，如：20170406
     */
    public Integer getHappenWeekDay() {
        return happenWeekDay;
    }

    /**
     * 设置发生日期,周首日，如：20170406
     *
     * @param happenWeekDay 发生日期,周首日，如：20170406
     */
    public void setHappenWeekDay(Integer happenWeekDay) {
        this.happenWeekDay = happenWeekDay;
    }


    /**
     * 获取总卡路里
     *
     * @return calorieTotal 总卡路里
     */
    public int getCalorieTotal() {
        return calorieTotal;
    }

    /**
     * 设置总卡路里
     *
     * @param calorieTotal 总卡路里
     */
    public void setCalorieTotal(int calorieTotal) {
        this.calorieTotal = calorieTotal;
    }

    /**
     * 获取总距离
     *
     * @return distanceTotal 总距离
     */
    public double getDistanceTotal() {
        return distanceTotal;
    }

    /**
     * 设置总距离
     *
     * @param distanceTotal 总距离
     */
    public void setDistanceTotal(double distanceTotal) {
        this.distanceTotal = distanceTotal;
    }

    /**
     * 获取总运动时间
     *
     * @return motionDurationTotal 总运动时间
     */
    public int getMotionDurationTotal() {
        return motionDurationTotal;
    }

    /**
     * 设置总运动时间
     *
     * @param motionDurationTotal 总运动时间
     */
    public void setMotionDurationTotal(int motionDurationTotal) {
        this.motionDurationTotal = motionDurationTotal;
    }

    /**
     * 获取总步数
     *
     * @return stepTotal 总步数
     */
    public int getStepTotal() {
        return stepTotal;
    }

    /**
     * 设置总步数
     *
     * @param stepTotal 总步数
     */
    public void setStepTotal(int stepTotal) {
        this.stepTotal = stepTotal;
    }

    /**
     * 获取用户ID
     *
     * @return userId 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取发生日期,精确到天，如：20170406
     *
     * @return happenDate 发生日期,精确到天，如：20170406
     */
    public Integer getHappenDate() {
        return happenDate;
    }

    /**
     * 设置发生日期,精确到天，如：20170406
     *
     * @param happenDate 发生日期,精确到天，如：20170406
     */
    public void setHappenDate(Integer happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取跑步（手动开启的跑步，运动跑步数据）卡路里（小卡）
     *
     * @return calorieRun 跑步（手动开启的跑步，运动跑步数据）卡路里（小卡）
     */
    public int getCalorieRun() {
        return calorieRun;
    }

    /**
     * 设置跑步（手动开启的跑步，运动跑步数据）卡路里（小卡）
     *
     * @param calorieRun 跑步（手动开启的跑步，运动跑步数据）卡路里（小卡）
     */
    public void setCalorieRun(int calorieRun) {
        this.calorieRun = calorieRun;
    }

    /**
     * 获取消耗总步数（）
     *
     * @return calorieRiding 消耗总步数（）
     */
    public int getCalorieRiding() {
        return calorieRiding;
    }

    /**
     * 设置消耗总步数（）
     *
     * @param calorieRiding 消耗总步数（）
     */
    public void setCalorieRiding(int calorieRiding) {
        this.calorieRiding = calorieRiding;
    }

    /**
     * 获取总距离，不包含日常的距离(米）保留两位小数
     *
     * @return calorieSwim 总距离，不包含日常的距离(米）保留两位小数
     */
    public int getCalorieSwim() {
        return calorieSwim;
    }

    /**
     * 设置总距离，不包含日常的距离(米）保留两位小数
     *
     * @param calorieSwim 总距离，不包含日常的距离(米）保留两位小数
     */
    public void setCalorieSwim(int calorieSwim) {
        this.calorieSwim = calorieSwim;
    }

    /**
     * 获取跑步（手动开启的跑步，运动跑步数据）距离（米）
     *
     * @return distanceRun 跑步（手动开启的跑步，运动跑步数据）距离（米）
     */
    public double getDistanceRun() {
        return distanceRun;
    }

    /**
     * 设置跑步（手动开启的跑步，运动跑步数据）距离（米）
     *
     * @param distanceRun 跑步（手动开启的跑步，运动跑步数据）距离（米）
     */
    public void setDistanceRun(double distanceRun) {
        this.distanceRun = distanceRun;
    }

    /**
     * 获取骑行时长（秒）
     *
     * @return distanceRiding 骑行时长（秒）
     */
    public double getDistanceRiding() {
        return distanceRiding;
    }

    /**
     * 设置骑行时长（秒）
     *
     * @param distanceRiding 骑行时长（秒）
     */
    public void setDistanceRiding(double distanceRiding) {
        this.distanceRiding = distanceRiding;
    }

    /**
     * 获取游泳时长（秒）
     *
     * @return distanceSwim 游泳时长（秒）
     */
    public double getDistanceSwim() {
        return distanceSwim;
    }

    /**
     * 设置游泳时长（秒）
     *
     * @param distanceSwim 游泳时长（秒）
     */
    public void setDistanceSwim(double distanceSwim) {
        this.distanceSwim = distanceSwim;
    }

    /**
     * 获取跑步（手动开启的跑步，运动跑步数据）运动时长（秒）
     *
     * @return motionDurationRun 跑步（手动开启的跑步，运动跑步数据）运动时长（秒）
     */
    public int getMotionDurationRun() {
        return motionDurationRun;
    }

    /**
     * 设置跑步（手动开启的跑步，运动跑步数据）运动时长（秒）
     *
     * @param motionDurationRun 跑步（手动开启的跑步，运动跑步数据）运动时长（秒）
     */
    public void setMotionDurationRun(int motionDurationRun) {
        this.motionDurationRun = motionDurationRun;
    }

    /**
     * 获取骑行时长（秒）
     *
     * @return motionDurationRiding 骑行时长（秒）
     */
    public int getMotionDurationRiding() {
        return motionDurationRiding;
    }

    /**
     * 设置骑行时长（秒）
     *
     * @param motionDurationRiding 骑行时长（秒）
     */
    public void setMotionDurationRiding(int motionDurationRiding) {
        this.motionDurationRiding = motionDurationRiding;
    }

    /**
     * 获取游泳时长（秒）
     *
     * @return motionDurationSwim 游泳时长（秒）
     */
    public int getMotionDurationSwim() {
        return motionDurationSwim;
    }

    /**
     * 设置游泳时长（秒）
     *
     * @param motionDurationSwim 游泳时长（秒）
     */
    public void setMotionDurationSwim(int motionDurationSwim) {
        this.motionDurationSwim = motionDurationSwim;
    }

    /**
     * 获取跑步（手动开启的跑步，运动跑步数据）步数统计步数没有意义，和产品确认
     *
     * @return stepRun 跑步（手动开启的跑步，运动跑步数据）步数统计步数没有意义，和产品确认
     */
    public int getStepRun() {
        return stepRun;
    }

    /**
     * 设置跑步（手动开启的跑步，运动跑步数据）步数统计步数没有意义，和产品确认
     *
     * @param stepRun 跑步（手动开启的跑步，运动跑步数据）步数统计步数没有意义，和产品确认
     */
    public void setStepRun(int stepRun) {
        this.stepRun = stepRun;
    }

    /**
     * 获取创建时间，服务器utc时间
     *
     * @return createTime 创建时间，服务器utc时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间，服务器utc时间
     *
     * @param createTime 创建时间，服务器utc时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
