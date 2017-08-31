package com.yf.pet.entity.daily;

/**
 * 心率统计对象对象实体
 * Created by Infi on 17/4/10.
 */
public class HeartRateStatisticsData {


    private int maxHeartRate;
    private int minHeartRate;
    private int sumHeartRateValue;
    private int countHeartRateNumber;
    private int index;

    /**
     * 构造方法
     *
     * @param index                当天所在统计刻度索引,这个心率对象在当天第一个15分钟
     * @param maxHeartRate         最大心率值
     * @param minHeartRate         最小心率值
     * @param sumHeartRateValue    心率值总和
     * @param countHeartRateNumber 心率总个数
     */
    public HeartRateStatisticsData(int index, int maxHeartRate, int minHeartRate, int sumHeartRateValue, int countHeartRateNumber) {
        this.index = index;
        this.maxHeartRate = maxHeartRate;
        this.minHeartRate = minHeartRate;
        this.sumHeartRateValue = sumHeartRateValue;
        this.countHeartRateNumber = countHeartRateNumber;
    }

    /**
     * 获取心率值总数
     *
     * @return sumHeartRateValue 心率值总数
     */
    public int getSumHeartRateValue() {
        return sumHeartRateValue;
    }

    /**
     * 设置心率值总数
     *
     * @param sumHeartRateValue 心率值总数
     */
    public void setSumHeartRateValue(int sumHeartRateValue) {
        this.sumHeartRateValue = sumHeartRateValue;
    }

    /**
     * 获取心率对象总个数
     *
     * @return countHeartRateNumber 心率对象总个数
     */
    public int getCountHeartRateNumber() {
        return countHeartRateNumber;
    }

    /**
     * 设置心率对象总个数
     *
     * @param countHeartRateNumber 心率对象总个数
     */
    public void setCountHeartRateNumber(int countHeartRateNumber) {
        this.countHeartRateNumber = countHeartRateNumber;
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
     * 获取最大心率值
     *
     * @return maxHeartRate 最大心率值
     */
    public int getMaxHeartRate() {
        return maxHeartRate;
    }

    /**
     * 设置最大心率值
     *
     * @param maxHeartRate 最大心率值
     */
    public void setMaxHeartRate(int maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    /**
     * 获取最小心率值
     *
     * @return minHeartRate 最小心率值
     */
    public int getMinHeartRate() {
        return minHeartRate;
    }

    /**
     * 设置最小心率值
     *
     * @param minHeartRate 最小心率值
     */
    public void setMinHeartRate(int minHeartRate) {
        this.minHeartRate = minHeartRate;
    }

}
