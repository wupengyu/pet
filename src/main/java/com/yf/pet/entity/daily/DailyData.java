package com.yf.pet.entity.daily;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 日常数据实体对象
 * Created by Infi on 17/6/1.
 */
public class DailyData {
    private Long userId;
    private Integer happenDate;
    private int totalCalorie;
    private float totalDistance;
    private int totalMotionTime;
    private int totalStep;
    private int targetMotionTime;
    private int targetCalorie;
    private String calorieLine;
    private String motionTimeLine;
    private String stepLine;
    private Date createTime;
    private String heartRateLine;
    private Integer calorieStandardRate;
    private Integer motionTimeStandardRate;
    private Integer happenYear;
    private Integer happenMonth;
    private Integer happenWeekDay;
    private Integer targetCalorieInWeek;
    private List<HeartRateProtoBufEntity> heartRateProtoBufEntityList;
    private byte[] heartRateData;
    private Map<Integer, HeartRateStatisticsData> heartRateDataMap;
    private Map<Integer, Integer> calorieMap;
    private Map<Integer, Integer> motionTimeMap;
    private Map<Integer, Integer> stepMap;
    private Long timestamp;
    private Integer timezone;
    private int dataStatus;
    private String heartRateDetail;

    /**
     * 获取心率数据明细,格式[[1],[[1503504258,32,80],......]]。格式说明:[[心率格式版本],[[时间戳,时区,心率值],......]]
     *
     * @return heartRateDetail 心率数据明细,格式[[1],[[1503504258,32,80],......]]。格式说明:[[心率格式版本],[[时间戳,时区,心率值],......]]
     */
    public String getHeartRateDetail() {
        return heartRateDetail;
    }

    /**
     * 设置心率数据明细,格式[[1],[[1503504258,32,80],......]]。格式说明:[[心率格式版本],[[时间戳,时区,心率值],......]]
     *
     * @param heartRateDetail 心率数据明细,格式[[1],[[1503504258,32,80],......]]。格式说明:[[心率格式版本],[[时间戳,时区,心率值],......]]
     */
    public void setHeartRateDetail(String heartRateDetail) {
        this.heartRateDetail = heartRateDetail;
    }


    /**
     * 获取1：新增，2：修改，3：没有变化的数据不需要新增也不需要修改
     *
     * @return dataStatus 1：新增，2：修改，3：没有变化的数据不需要新增也不需要修改
     */
    public int getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置1：新增，2：修改，3：没有变化的数据不需要新增也不需要修改
     *
     * @param dataStatus 1：新增，2：修改，3：没有变化的数据不需要新增也不需要修改
     */
    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * 获取用户当前时间戳
     *
     * @return timestamp 用户当前时间戳
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 设置用户当前时间戳
     *
     * @param timestamp 用户当前时间戳
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 获取用户当前时区
     *
     * @return timezone 用户当前时区
     */
    public Integer getTimezone() {
        return timezone;
    }

    /**
     * 设置用户当前时区
     *
     * @param timezone 用户当前时区
     */
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    /**
     * 获取卡路里分段统计数据
     *
     * @return calorieMap 卡路里分段统计数据
     */
    public Map<Integer, Integer> getCalorieMap() {
        return calorieMap;
    }

    /**
     * 设置卡路里分段统计数据
     *
     * @param calorieMap 卡路里分段统计数据
     */
    public void setCalorieMap(Map<Integer, Integer> calorieMap) {
        this.calorieMap = calorieMap;
    }

    /**
     * 获取运动时长分段统计数据
     *
     * @return motionTimeMap 运动时长分段统计数据
     */
    public Map<Integer, Integer> getMotionTimeMap() {
        return motionTimeMap;
    }

    /**
     * 设置运动时长分段统计数据
     *
     * @param motionTimeMap 运动时长分段统计数据
     */
    public void setMotionTimeMap(Map<Integer, Integer> motionTimeMap) {
        this.motionTimeMap = motionTimeMap;
    }

    /**
     * 获取步数分段统计数据
     *
     * @return stepMap 步数分段统计数据
     */
    public Map<Integer, Integer> getStepMap() {
        return stepMap;
    }

    /**
     * 设置步数分段统计数据
     *
     * @param stepMap 步数分段统计数据
     */
    public void setStepMap(Map<Integer, Integer> stepMap) {
        this.stepMap = stepMap;
    }

    /**
     * 获取当天心率统计数据
     *
     * @return heartRateDataMap 当天心率统计数据
     */
    public Map<Integer, HeartRateStatisticsData> getHeartRateDataMap() {
        return heartRateDataMap;
    }

    /**
     * 设置当天心率统计数据
     *
     * @param heartRateDataMap 当天心率统计数据
     */
    public void setHeartRateDataMap(Map<Integer, HeartRateStatisticsData> heartRateDataMap) {
        this.heartRateDataMap = heartRateDataMap;
    }

    /**
     * 获取获取心率byte数组数据，ProtoBuf转换过来的byte[]
     *
     * @return heartRateData 获取心率byte数组数据，ProtoBuf转换过来的byte[]
     */
    public byte[] getHeartRateData() {
        return heartRateData;
    }

    /**
     * 设置获取心率byte数组数据，ProtoBuf转换过来的byte[]
     *
     * @param heartRateData 获取心率byte数组数据，ProtoBuf转换过来的byte[]
     */
    public void setHeartRateData(byte[] heartRateData) {
        this.heartRateData = heartRateData;
    }

    /**
     * 获取心率数据列表
     *
     * @return heartRateProtoBufEntityList 心率数据列表
     */
    public List<HeartRateProtoBufEntity> getHeartRateProtoBufEntityList() {
        return heartRateProtoBufEntityList;
    }

    /**
     * 设置心率数据列表
     *
     * @param heartRateProtoBufEntityList 心率数据列表
     */
    public void setHeartRateProtoBufEntityList(List<HeartRateProtoBufEntity> heartRateProtoBufEntityList) {
        this.heartRateProtoBufEntityList = heartRateProtoBufEntityList;
    }


    /**
     * 获取周目标卡路里
     *
     * @return targetCalorieInWeek 周目标卡路里
     */
    public Integer getTargetCalorieInWeek() {
        return targetCalorieInWeek;
    }

    /**
     * 设置周目标卡路里
     *
     * @param targetCalorieInWeek 周目标卡路里
     */
    public void setTargetCalorieInWeek(Integer targetCalorieInWeek) {
        this.targetCalorieInWeek = targetCalorieInWeek;
    }

    /**
     * 获取发生日期,精确到年，如：2017
     *
     * @return happenYear 发生日期,精确到年，如：2017
     */
    public Integer getHappenYear() {
        return happenYear;
    }

    /**
     * 设置发生日期,精确到年，如：2017
     *
     * @param happenYear 发生日期,精确到年，如：2017
     */
    public void setHappenYear(Integer happenYear) {
        this.happenYear = happenYear;
    }

    /**
     * 获取发生日期,精确到月，如：201704
     *
     * @return happenMonth 发生日期,精确到月，如：201704
     */
    public Integer getHappenMonth() {
        return happenMonth;
    }

    /**
     * 设置发生日期,精确到月，如：201704
     *
     * @param happenMonth 发生日期,精确到月，如：201704
     */
    public void setHappenMonth(Integer happenMonth) {
        this.happenMonth = happenMonth;
    }

    /**
     * 获取发生日期,周首日，如：20170406
     *
     * @return happenWeekDay 发生日期,周首日，如：20170406
     */
    public Integer getHappenWeekDay() {
        return happenWeekDay;
    }

    /**
     * 设置发生日期,周首日，如：20170406
     *
     * @param happenWeekDay 发生日期,周首日，如：20170406
     */
    public void setHappenWeekDay(Integer happenWeekDay) {
        this.happenWeekDay = happenWeekDay;
    }

    /**
     * 获取卡路里达标率
     *
     * @return calorieStandardRate 卡路里达标率
     */
    public Integer getCalorieStandardRate() {
        return calorieStandardRate;
    }

    /**
     * 设置卡路里达标率
     *
     * @param calorieStandardRate 卡路里达标率
     */
    public void setCalorieStandardRate(Integer calorieStandardRate) {
        this.calorieStandardRate = calorieStandardRate;
    }

    /**
     * 获取运动时间达标率
     *
     * @return motionTimeStandardRate 运动时间达标率
     */
    public Integer getMotionTimeStandardRate() {
        return motionTimeStandardRate;
    }

    /**
     * 设置运动时间达标率
     *
     * @param motionTimeStandardRate 运动时间达标率
     */
    public void setMotionTimeStandardRate(Integer motionTimeStandardRate) {
        this.motionTimeStandardRate = motionTimeStandardRate;
    }


    /**
     * 获取心率数据字符串
     *
     * @return heartRateLine 心率数据字符串
     */
    public String getHeartRateLine() {
        return heartRateLine;
    }

    /**
     * 设置心率数据字符串
     *
     * @param heartRateLine 心率数据字符串
     */
    public void setHeartRateLine(String heartRateLine) {
        this.heartRateLine = heartRateLine;
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
     * 获取发生日期,精确到天，如：20170406
     *
     * @return happenDate 发生日期,精确到天，如：20170406
     */
    public Integer getHappenDate() {
        return happenDate;
    }

    /**
     * 设置发生日期,精确到天，如：20170406
     *
     * @param happenDate 发生日期,精确到天，如：20170406
     */
    public void setHappenDate(Integer happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取总卡路里（日常加运动）（小卡）
     *
     * @return totalCalorie 总卡路里（日常加运动）（小卡）
     */
    public int getTotalCalorie() {
        return totalCalorie;
    }

    /**
     * 设置总卡路里（日常加运动）（小卡）
     *
     * @param totalCalorie 总卡路里（日常加运动）（小卡）
     */
    public void setTotalCalorie(int totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    /**
     * 获取总距离（日常加运动）（米）
     *
     * @return totalDistance 总距离（日常加运动）（米）
     */
    public float getTotalDistance() {
        return totalDistance;
    }

    /**
     * 设置总距离（日常加运动）（米）
     *
     * @param totalDistance 总距离（日常加运动）（米）
     */
    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

    /**
     * 获取总运动时长（日常+运动）（秒）
     *
     * @return totalMotionTime 总运动时长（日常+运动）（秒）
     */
    public int getTotalMotionTime() {
        return totalMotionTime;
    }

    /**
     * 设置总运动时长（日常+运动）（秒）
     *
     * @param totalMotionTime 总运动时长（日常+运动）（秒）
     */
    public void setTotalMotionTime(int totalMotionTime) {
        this.totalMotionTime = totalMotionTime;
    }

    /**
     * 获取总步数（日常加运动）
     *
     * @return totalStep 总步数（日常加运动）
     */
    public int getTotalStep() {
        return totalStep;
    }

    /**
     * 设置总步数（日常加运动）
     *
     * @param totalStep 总步数（日常加运动）
     */
    public void setTotalStep(int totalStep) {
        this.totalStep = totalStep;
    }

    /**
     * 获取当天目标运动时间（秒）
     *
     * @return targetMotionTime 当天目标运动时间（秒）
     */
    public int getTargetMotionTime() {
        return targetMotionTime;
    }

    /**
     * 设置当天目标运动时间（秒）
     *
     * @param targetMotionTime 当天目标运动时间（秒）
     */
    public void setTargetMotionTime(int targetMotionTime) {
        this.targetMotionTime = targetMotionTime;
    }

    /**
     * 获取当天目标卡路里（小卡）
     *
     * @return targetCalorie 当天目标卡路里（小卡）
     */
    public int getTargetCalorie() {
        return targetCalorie;
    }

    /**
     * 设置当天目标卡路里（小卡）
     *
     * @param targetCalorie 当天目标卡路里（小卡）
     */
    public void setTargetCalorie(int targetCalorie) {
        this.targetCalorie = targetCalorie;
    }

    /**
     * 获取能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有卡路里就为0每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     *
     * @return calorieLine 能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有卡路里就为0每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     */
    public String getCalorieLine() {
        return calorieLine;
    }

    /**
     * 设置能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有卡路里就为0每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     *
     * @param calorieLine 能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有卡路里就为0每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     */
    public void setCalorieLine(String calorieLine) {
        this.calorieLine = calorieLine;
    }

    /**
     * 获取运动时长柱状图，能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有运动时间就为0，如果当天没有数据，就为空每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     *
     * @return motionTimeLine 运动时长柱状图，能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有运动时间就为0，如果当天没有数据，就为空每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     */
    public String getMotionTimeLine() {
        return motionTimeLine;
    }

    /**
     * 设置运动时长柱状图，能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有运动时间就为0，如果当天没有数据，就为空每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     *
     * @param motionTimeLine 运动时长柱状图，能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有运动时间就为0，如果当天没有数据，就为空每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     */
    public void setMotionTimeLine(String motionTimeLine) {
        this.motionTimeLine = motionTimeLine;
    }

    /**
     * 获取步数柱状图，能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有步数就为0，如果当天没有数据，就为空每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     *
     * @return stepLine 步数柱状图，能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有步数就为0，如果当天没有数据，就为空每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     */
    public String getStepLine() {
        return stepLine;
    }

    /**
     * 设置步数柱状图，能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有步数就为0，如果当天没有数据，就为空每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     *
     * @param stepLine 步数柱状图，能量柱状图，数据库记录最小粒度的数据数组，最小粒度为15分钟。15分钟内没有步数就为0，如果当天没有数据，就为空每个刻度值用逗号隔开，如[[15],[ 4,4,5,6,6,666,6,6,6,6]]。第一个数值15代表统计粒度
     */
    public void setStepLine(String stepLine) {
        this.stepLine = stepLine;
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
