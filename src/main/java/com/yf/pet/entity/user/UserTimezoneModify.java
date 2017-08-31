package com.yf.pet.entity.user;

import java.util.Date;

/**
 * 用户时区变更记录表
 * Created by Infi on 17/6/9.
 */
public class UserTimezoneModify {

    private Long userId;
    private Integer timezone;
    private Date createTime;

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
     * 获取时区
     *
     * @return timezone 时区
     */
    public Integer getTimezone() {
        return timezone;
    }

    /**
     * 设置时区
     *
     * @param timezone 时区
     */
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
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
