package com.yf.pet.entity.riding;

import java.util.Date;

/**
 * 骑行运动数据对象
 * Created by Infi on 17/5/9.
 */
public class CorosSportData {

    private Long labelId;
    private Long userId;
    private Integer happenDate;
    private String deviceId;
    private Long startTime;
    private Long endTime;
    private Integer startTimezone;
    private Integer endTimezone;
    private int calorie;
    private double distance;
    private byte[] sportData;
    private Date createTime;
    private Integer happenMonth;
    private int duration;
    private int laps;
    private int step;
    private Integer subMode;

    /**
     * 获取子类型，1：室内跑步，2：室外跑步
     *
     * @return subMode 子类型，1：室内跑步，2：室外跑步
     */
    public Integer getSubMode() {
        return subMode;
    }

    /**
     * 设置子类型，1：室内跑步，2：室外跑步
     *
     * @param subMode 子类型，1：室内跑步，2：室外跑步
     */
    public void setSubMode(Integer subMode) {
        this.subMode = subMode;
    }

    /**
     * 获取趟数
     *
     * @return laps 趟数
     */
    public int getLaps() {
        return laps;
    }

    /**
     * 设置趟数
     *
     * @param laps 趟数
     */
    public void setLaps(int laps) {
        this.laps = laps;
    }

    /**
     * 获取步数
     *
     * @return step 步数
     */
    public int getStep() {
        return step;
    }

    /**
     * 设置步数
     *
     * @param step 步数
     */
    public void setStep(int step) {
        this.step = step;
    }


    /**
     * 获取运动时长，因为运动数据有暂停，所以运动时长有可能不等于结束时间减去开始时间
     *
     * @return duration 运动时长，因为运动数据有暂停，所以运动时长有可能不等于结束时间减去开始时间
     */
    public int getDuration() {
        return duration;
    }

    /**
     * 设置运动时长，因为运动数据有暂停，所以运动时长有可能不等于结束时间减去开始时间
     *
     * @param duration 运动时长，因为运动数据有暂停，所以运动时长有可能不等于结束时间减去开始时间
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }


    /**
     * 获取标签属于那一个月
     *
     * @return happenMonth 标签属于那一个月
     */
    public Integer getHappenMonth() {
        return happenMonth;
    }

    /**
     * 设置标签属于那一个月
     *
     * @param happenMonth 标签属于那一个月
     */
    public void setHappenMonth(Integer happenMonth) {
        this.happenMonth = happenMonth;
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
     * 获取所属标签编号
     *
     * @return labelId 所属标签编号
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 设置所属标签编号
     *
     * @param labelId 所属标签编号
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * 获取标签属于那一天
     *
     * @return happenDate 标签属于那一天
     */
    public Integer getHappenDate() {
        return happenDate;
    }

    /**
     * 设置标签属于那一天
     *
     * @param happenDate 标签属于那一天
     */
    public void setHappenDate(Integer happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取设备类型加上蓝牙名称，用来判断用户是否重复提交骑行数据
     *
     * @return deviceId 设备类型加上蓝牙名称，用来判断用户是否重复提交骑行数据
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备类型加上蓝牙名称，用来判断用户是否重复提交骑行数据
     *
     * @param deviceId 设备类型加上蓝牙名称，用来判断用户是否重复提交骑行数据
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取开始时间，时间戳
     *
     * @return startTime 开始时间，时间戳
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间，时间戳
     *
     * @param startTime 开始时间，时间戳
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间，时间戳
     *
     * @return endTime 结束时间，时间戳
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间，时间戳
     *
     * @param endTime 结束时间，时间戳
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取开始时间的时区，每15分钟一个刻度
     *
     * @return startTimezone 开始时间的时区，每15分钟一个刻度
     */
    public Integer getStartTimezone() {
        return startTimezone;
    }

    /**
     * 设置开始时间的时区，每15分钟一个刻度
     *
     * @param startTimezone 开始时间的时区，每15分钟一个刻度
     */
    public void setStartTimezone(Integer startTimezone) {
        this.startTimezone = startTimezone;
    }

    /**
     * 获取结束时间的时区，每15分钟一个刻度
     *
     * @return endTimezone 结束时间的时区，每15分钟一个刻度
     */
    public Integer getEndTimezone() {
        return endTimezone;
    }

    /**
     * 设置结束时间的时区，每15分钟一个刻度
     *
     * @param endTimezone 结束时间的时区，每15分钟一个刻度
     */
    public void setEndTimezone(Integer endTimezone) {
        this.endTimezone = endTimezone;
    }

    /**
     * 获取卡路里（小卡）
     *
     * @return calorie 卡路里（小卡）
     */
    public int getCalorie() {
        return calorie;
    }

    /**
     * 设置卡路里（小卡）
     *
     * @param calorie 卡路里（小卡）
     */
    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    /**
     * 获取距离,保留两位小数（单位：米）
     *
     * @return distance 距离,保留两位小数（单位：米）
     */
    public double getDistance() {
        return distance;
    }

    /**
     * 设置距离,保留两位小数（单位：米）
     *
     * @param distance 距离,保留两位小数（单位：米）
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * 获取运动详情数据,用户查看运动详情，服务器只需要把sport_data返回给APP，APP自己完成数据展示
     *
     * @return sportData 运动详情数据,用户查看运动详情，服务器只需要把sport_data返回给APP，APP自己完成数据展示
     */
    public byte[] getSportData() {
        return sportData;
    }

    /**
     * 设置运动详情数据,用户查看运动详情，服务器只需要把sport_data返回给APP，APP自己完成数据展示
     *
     * @param sportData 运动详情数据,用户查看运动详情，服务器只需要把sport_data返回给APP，APP自己完成数据展示
     */
    public void setSportData(byte[] sportData) {
        this.sportData = sportData;
    }

    /**
     * 获取插入时间
     *
     * @return createTime 插入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置插入时间
     *
     * @param createTime 插入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
