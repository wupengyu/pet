package com.yf.pet.entity.career;

/**
 * 个人职业生涯运动数据统计模板
 * Created by Infi on 17/6/7.
 */
public class CareerSportData {

    private Integer mode;
    private double distance;


    /**
     * 获取用户类型8:跑步、9:骑行、10:游泳
     *
     * @return mode 用户类型8:跑步、9:骑行、10:游泳
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 设置用户类型8:跑步、9:骑行、10:游泳
     *
     * @param mode 用户类型8:跑步、9:骑行、10:游泳
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    /**
     * 获取距离(米)
     *
     * @return distance 距离(米)
     */
    public double getDistance() {
        return distance;
    }

    /**
     * 设置距离(米)
     *
     * @param distance 距离(米)
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
