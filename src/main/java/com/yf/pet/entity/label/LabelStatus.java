package com.yf.pet.entity.label;

import java.util.Date;

/**
 * 标签状态实体
 * Created by Infi on 17/6/1.
 */
public class LabelStatus {
    private Long userId;
    private Long labelId;
    private Integer mode;
    private byte[] statusData;
    private Date createTime;
    private Long lastMinuteTimestamp;

    /**
     * 获取上一次解析数据最后一分钟的时间戳，等于分钟数据的时间戳加上时区偏移秒（minute.timestampInSecond + 60 * 15 * minute.timezoneIn15Minutes）用于判断数据重复提交
     *
     * @return lastMinuteTimestamp 上一次解析数据最后一分钟的时间戳，等于分钟数据的时间戳加上时区偏移秒（minute.timestampInSecond + 60 * 15 * minute.timezoneIn15Minutes）用于判断数据重复提交
     */
    public Long getLastMinuteTimestamp() {
        return lastMinuteTimestamp;
    }

    /**
     * 设置上一次解析数据最后一分钟的时间戳，等于分钟数据的时间戳加上时区偏移秒（minute.timestampInSecond + 60 * 15 * minute.timezoneIn15Minutes）用于判断数据重复提交
     *
     * @param lastMinuteTimestamp 上一次解析数据最后一分钟的时间戳，等于分钟数据的时间戳加上时区偏移秒（minute.timestampInSecond + 60 * 15 * minute.timezoneIn15Minutes）用于判断数据重复提交
     */
    public void setLastMinuteTimestamp(Long lastMinuteTimestamp) {
        this.lastMinuteTimestamp = lastMinuteTimestamp;
    }

    /**
     * 获取用户ID
     *
     * @return userId 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取上次解析最后一个标签ID
     *
     * @return labelId 上次解析最后一个标签ID
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 设置上次解析最后一个标签ID
     *
     * @param labelId 上次解析最后一个标签ID
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * 获取标签类型
     *
     * @return mode 标签类型
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 设置标签类型
     *
     * @param mode 标签类型
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    /**
     * 获取数据
     *
     * @return statusData 数据
     */
    public byte[] getStatusData() {
        return statusData;
    }

    /**
     * 设置数据
     *
     * @param statusData 数据
     */
    public void setStatusData(byte[] statusData) {
        this.statusData = statusData;
    }

    /**
     * 获取创建时间
     *
     * @return createTime 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
