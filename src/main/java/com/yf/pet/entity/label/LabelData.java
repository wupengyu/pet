package com.yf.pet.entity.label;

import java.util.Date;

/**
 * Created by Infi on 17/5/9.
 * 标签对象
 */
public class LabelData {
    private Long labelId;
    private Long userId;
    private Integer happenDate;
    private Long startTime;
    private Long endTime;
    private Integer startTimezone;
    private Integer endTimezone;
    private Integer mode;
    private int step;
    private int calorie;
    private double distance;
    private Integer state;
    private String graphValue;
    private Date createTime;
    private byte[] imageData;
    private String uuid;
    private byte[] sportData;
    private String deviceId;
    private Integer happenMonth;
    private String imageDataStr;
    private int duration;
    private int laps;
    private Integer subMode;
    private String imageUrl;

    /**
     * 获取运动轨迹缩略图链接地址
     *
     * @return imageUrl 运动轨迹缩略图链接地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置运动轨迹缩略图链接地址
     *
     * @param imageUrl 运动轨迹缩略图链接地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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
     * 获取图片的base64字符串格式
     *
     * @return imageDataStr 图片的base64字符串格式
     */
    public String getImageDataStr() {
        return imageDataStr;
    }

    /**
     * 设置图片的base64字符串格式
     *
     * @param imageDataStr 图片的base64字符串格式
     */
    public void setImageDataStr(String imageDataStr) {
        this.imageDataStr = imageDataStr;
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
     * 获取运动数据
     *
     * @return sportData 运动数据
     */
    public byte[] getSportData() {
        return sportData;
    }

    /**
     * 设置运动数据
     *
     * @param sportData 运动数据
     */
    public void setSportData(byte[] sportData) {
        this.sportData = sportData;
    }

    /**
     * 获取运动数据类型，8:跑步训练,9:骑行,10:游泳
     *
     * @return mode 运动数据类型，8:跑步训练,9:骑行,10:游泳
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 设置运动数据类型，8:跑步训练,9:骑行,10:游泳
     *
     * @param mode 运动数据类型，8:跑步训练,9:骑行,10:游泳
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    /**
     * 获取运动数据编号，APP本地生成
     *
     * @return uuid 运动数据编号，APP本地生成
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置运动数据编号，APP本地生成
     *
     * @param uuid 运动数据编号，APP本地生成
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取运动轨迹缩略图
     *
     * @return imageData 运动轨迹缩略图
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * 设置运动轨迹缩略图
     *
     * @param imageData 运动轨迹缩略图
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }


    /**
     * 获取主键,使用分布是主键生成器生成
     *
     * @return labelId 主键,使用分布是主键生成器生成
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 设置主键,使用分布是主键生成器生成
     *
     * @param labelId 主键,使用分布是主键生成器生成
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
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
     * 获取标签状态,用于删除数据，1：表示可用，0：表示删除
     *
     * @return state 标签状态,用于删除数据，1：表示可用，0：表示删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置标签状态,用于删除数据，1：表示可用，0：表示删除
     *
     * @param state 标签状态,用于删除数据，1：表示可用，0：表示删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取睡眠曲线，标签解析后得到，如：0,0,2,1,86,2,206,1格式和weloop保存一致
     *
     * @return graphValue 睡眠曲线，标签解析后得到，如：0,0,2,1,86,2,206,1格式和weloop保存一致
     */
    public String getGraphValue() {
        return graphValue;
    }

    /**
     * 设置睡眠曲线，标签解析后得到，如：0,0,2,1,86,2,206,1格式和weloop保存一致
     *
     * @param graphValue 睡眠曲线，标签解析后得到，如：0,0,2,1,86,2,206,1格式和weloop保存一致
     */
    public void setGraphValue(String graphValue) {
        this.graphValue = graphValue;
    }

    /**
     * 获取创建时间，服务器utc时间
     *
     * @return createTime 创建时间，服务器utc时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间，服务器utc时间
     *
     * @param createTime 创建时间，服务器utc时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
