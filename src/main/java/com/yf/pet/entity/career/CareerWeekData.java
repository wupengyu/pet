package com.yf.pet.entity.career;

/**
 * 个人职业生涯周统计
 * Created by Infi on 17/6/7.
 */
public class CareerWeekData {

    private int weekCalorie;
    private int weekCalorieTarget;


    /**
     * 获取周卡路里
     *
     * @return weekCalorie 周卡路里
     */
    public int getWeekCalorie() {
        return weekCalorie;
    }

    /**
     * 设置周卡路里
     *
     * @param weekCalorie 周卡路里
     */
    public void setWeekCalorie(int weekCalorie) {
        this.weekCalorie = weekCalorie;
    }

    /**
     * 获取周卡路里目标值
     *
     * @return weekCalorieTarget 周卡路里目标值
     */
    public int getWeekCalorieTarget() {
        return weekCalorieTarget;
    }

    /**
     * 设置周卡路里目标值
     *
     * @param weekCalorieTarget 周卡路里目标值
     */
    public void setWeekCalorieTarget(int weekCalorieTarget) {
        this.weekCalorieTarget = weekCalorieTarget;
    }
}
