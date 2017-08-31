package com.yf.pet.entity.daily;

/**
 * 心率pb对象实体类
 * Created by Infi on 17/6/19.
 */
public class HeartRateProtoBufEntity {
    public int timestampInSecond;
    public short rate;
    public byte timezoneIn15Minutes;
    public byte mode;

    /**
     * 获取0 自动检测，1手动检测
     *
     * @return mode 0 自动检测，1手动检测
     */
    public byte getMode() {
        return mode;
    }

    /**
     * 设置0 自动检测，1手动检测
     *
     * @param mode 0 自动检测，1手动检测
     */
    public void setMode(byte mode) {
        this.mode = mode;
    }

    /**
     * 构造方法
     *
     * @param timestampInSecond   时间戳(秒)
     * @param rate                心率值
     * @param timezoneIn15Minutes 时区
     */
    public HeartRateProtoBufEntity(int timestampInSecond, short rate, byte timezoneIn15Minutes, byte mode) {
        this.timestampInSecond = timestampInSecond;
        this.rate = rate;
        this.timezoneIn15Minutes = timezoneIn15Minutes;
        this.mode = mode;
    }

    /**
     * 获取时间戳(秒)
     *
     * @return timestampInSecond 时间戳(秒)
     */
    public int getTimestampInSecond() {
        return timestampInSecond;
    }

    /**
     * 设置时间戳(秒)
     *
     * @param timestampInSecond 时间戳(秒)
     */
    public void setTimestampInSecond(int timestampInSecond) {
        this.timestampInSecond = timestampInSecond;
    }

    /**
     * 获取心率值
     *
     * @return rate 心率值
     */
    public short getRate() {
        return rate;
    }

    /**
     * 设置心率值
     *
     * @param rate 心率值
     */
    public void setRate(short rate) {
        this.rate = rate;
    }

    /**
     * 获取心率时区，以15分钟为单位。
     *
     * @return timezoneIn15Minutes 心率时区，以15分钟为单位。
     */
    public byte getTimezoneIn15Minutes() {
        return timezoneIn15Minutes;
    }

    /**
     * 设置心率时区，以15分钟为单位。
     *
     * @param timezoneIn15Minutes 心率时区，以15分钟为单位。
     */
    public void setTimezoneIn15Minutes(byte timezoneIn15Minutes) {
        this.timezoneIn15Minutes = timezoneIn15Minutes;
    }
}
