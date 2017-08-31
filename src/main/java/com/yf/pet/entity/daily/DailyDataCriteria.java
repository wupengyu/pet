package com.yf.pet.entity.daily;

/**
 * 查询条件
 * Created by Infi on 17/6/1.
 */
public class DailyDataCriteria {

    private Integer startDate;
    private Integer endDate;

    /**
     * 获取开始时间，查询条件
     *
     * @return startDate 开始时间，查询条件
     */
    public Integer getStartDate() {
        return startDate;
    }

    /**
     * 设置开始时间，查询条件
     *
     * @param startDate 开始时间，查询条件
     */
    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束时间，查询条件
     *
     * @return endDate 结束时间，查询条件
     */
    public Integer getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间，查询条件
     *
     * @param endDate 结束时间，查询条件
     */
    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }
}
