package com.yf.pet.entity.daily;

import java.util.List;

/**
 * 日常数据返回类
 * Created by Infi on 17/4/1.
 */
public class DailyDataVO {
    private List<DailyData> dailyDatas;
    private String saveParseInfo;


    /**
     * 获取标签解析状态
     *
     * @return saveParseInfo 标签解析状态
     */
    public String getSaveParseInfo() {
        return saveParseInfo;
    }

    /**
     * 设置标签解析状态
     *
     * @param saveParseInfo 标签解析状态
     */
    public void setSaveParseInfo(String saveParseInfo) {
        this.saveParseInfo = saveParseInfo;
    }

    /**
     * 获取日常数据列表
     *
     * @return dailyDatas 日常数据列表
     */
    public List<DailyData> getDailyDatas() {
        return dailyDatas;
    }

    /**
     * 设置日常数据列表
     *
     * @param dailyDatas 日常数据列表
     */
    public void setDailyDatas(List<DailyData> dailyDatas) {
        this.dailyDatas = dailyDatas;
    }
}
