package com.yf.pet.entity.medal;

/**
 * 用户勋章信息表
 * Created by Infi on 17/6/13.
 */
public class UserMedal {

    private Long userId;
    private Integer medalId;
    private Long timestamp;
    private Integer timezone;

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
     * 获取勋章ID
     *
     * @return medalId 勋章ID
     */
    public Integer getMedalId() {
        return medalId;
    }

    /**
     * 设置勋章ID
     *
     * @param medalId 勋章ID
     */
    public void setMedalId(Integer medalId) {
        this.medalId = medalId;
    }

    /**
     * 获取获得勋章时的时间戳
     *
     * @return timestamp 获得勋章时的时间戳
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 设置获得勋章时的时间戳
     *
     * @param timestamp 获得勋章时的时间戳
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 获取获得勋章时的时区
     *
     * @return timezone 获得勋章时的时区
     */
    public Integer getTimezone() {
        return timezone;
    }

    /**
     * 设置获得勋章时的时区
     *
     * @param timezone 获得勋章时的时区
     */
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }
}
