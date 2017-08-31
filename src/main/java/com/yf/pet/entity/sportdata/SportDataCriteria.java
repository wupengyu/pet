package com.yf.pet.entity.sportdata;

/**
 * 运动数据的查询条件
 * Created by Infi on 17/5/11.
 */
public class SportDataCriteria {

    private Integer month;
    private Integer mode;
    private Long labelId;


    /**
     * 获取运动数据ID，标签ID
     *
     * @return labelId 运动数据ID，标签ID
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 设置运动数据ID，标签ID
     *
     * @param labelId 运动数据ID，标签ID
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * 获取月份，格式yyyyMM,比如201701
     *
     * @return month 月份，格式yyyyMM,比如201701
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * 设置月份，格式yyyyMM,比如201701
     *
     * @param month 月份，格式yyyyMM,比如201701
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * 获取运动类型,mode不设置值，服务器会返回全部运动数据
     *
     * @return mode 运动类型,mode不设置值，服务器会返回全部运动数据
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 设置运动类型,mode不设置值，服务器会返回全部运动数据
     *
     * @param mode 运动类型,mode不设置值，服务器会返回全部运动数据
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }
}
