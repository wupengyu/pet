package com.yf.pet.entity.daily;

/**
 * Created by Infi on 17/4/10.
 */
public class SleepData {

    private Long startTime;
    private Long endTime;
    private Integer timezone;
    private String graphValue;

    /**
     * 获取开始时间
     *
     * @return startTime 开始时间
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return endTime 结束时间
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取睡觉数据所在时区
     *
     * @return timezone 睡觉数据所在时区
     */
    public Integer getTimezone() {
        return timezone;
    }

    /**
     * 设置睡觉数据所在时区
     *
     * @param timezone 睡觉数据所在时区
     */
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    /**
     * 获取睡觉数据
     *
     * @return graphValue 睡觉数据
     */
    public String getGraphValue() {
        return graphValue;
    }

    /**
     * 设置睡觉数据
     *
     * @param graphValue 睡觉数据
     */
    public void setGraphValue(String graphValue) {
        this.graphValue = graphValue;
    }
}
