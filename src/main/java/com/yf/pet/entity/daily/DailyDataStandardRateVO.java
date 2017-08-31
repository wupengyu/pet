package com.yf.pet.entity.daily;

/**
 * 日常数据日历功能(达标率)实体对象
 * Created by Infi on 17/6/1.
 */
public class DailyDataStandardRateVO {
    private Integer happenDate;
    private Integer calorieStandardRate;
    private Integer motionTimeStandardRate;

    /**
     * 获取卡路里达标率
     *
     * @return calorieStandardRate 卡路里达标率
     */
    public Integer getCalorieStandardRate() {
        return calorieStandardRate;
    }

    /**
     * 设置卡路里达标率
     *
     * @param calorieStandardRate 卡路里达标率
     */
    public void setCalorieStandardRate(Integer calorieStandardRate) {
        this.calorieStandardRate = calorieStandardRate;
    }

    /**
     * 获取运动时间达标率
     *
     * @return motionTimeStandardRate 运动时间达标率
     */
    public Integer getMotionTimeStandardRate() {
        return motionTimeStandardRate;
    }

    /**
     * 设置运动时间达标率
     *
     * @param motionTimeStandardRate 运动时间达标率
     */
    public void setMotionTimeStandardRate(Integer motionTimeStandardRate) {
        this.motionTimeStandardRate = motionTimeStandardRate;
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

}
