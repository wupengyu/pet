package com.yf.pet.entity.daily;

/**
 * 心率统计对象对象实体
 * Created by Infi on 17/4/10.
 */
public class HeartRateData {
    private int heartRate;
    private int index;

    /**
     * 心率对象构造方法
     *
     * @param index     当天分段索引
     * @param heartRate 心率值
     */
    public HeartRateData(int index, int heartRate) {
        this.index = index;
        this.heartRate = heartRate;
    }

    /**
     * 获取心率刻度的顺序
     *
     * @return index 心率刻度的顺序
     */
    public int getIndex() {
        return index;
    }

    /**
     * 设置心率刻度的顺序
     *
     * @param index 心率刻度的顺序
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 获取心率值
     *
     * @return heartRate 心率值
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * 设置最大心率值
     *
     * @param heartRate 心率值
     */
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }


}
