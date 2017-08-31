package com.yf.pet.entity.career;


import com.yf.pet.entity.medal.UserMedal;

import java.util.List;

/**
 * 个人职业生涯统计
 * Created by Infi on 17/6/7.
 */
public class CareerDataVO {

    private CareerWeekData weekData;
    private List<CareerSportData> sportDatas;
    private List<UserMedal> medalDatas;

    /**
     * 获取勋章数据
     *
     * @return medalDatas 勋章数据
     */
    public List<UserMedal> getMedalDatas() {
        return medalDatas;
    }

    /**
     * 设置勋章数据
     *
     * @param medalDatas 勋章数据
     */
    public void setMedalDatas(List<UserMedal> medalDatas) {
        this.medalDatas = medalDatas;
    }


    /**
     * 获取个人职业生涯周统计数据
     *
     * @return weekData 个人职业生涯周统计数据
     */
    public CareerWeekData getWeekData() {
        return weekData;
    }

    /**
     * 设置个人职业生涯周统计数据
     *
     * @param weekData 个人职业生涯周统计数据
     */
    public void setWeekData(CareerWeekData weekData) {
        this.weekData = weekData;
    }

    /**
     * 获取个人职业生涯运动数据统计
     *
     * @return sportDatas 个人职业生涯运动数据统计
     */
    public List<CareerSportData> getSportDatas() {
        return sportDatas;
    }

    /**
     * 设置个人职业生涯运动数据统计
     *
     * @param sportDatas 个人职业生涯运动数据统计
     */
    public void setSportDatas(List<CareerSportData> sportDatas) {
        this.sportDatas = sportDatas;
    }
}
