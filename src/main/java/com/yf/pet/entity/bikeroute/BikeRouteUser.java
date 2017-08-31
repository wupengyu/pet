package com.yf.pet.entity.bikeroute;

import java.util.Date;

/**
 * 自行车导航路书用户关系表
 * Created by Infi on 17/5/19.
 */
public class BikeRouteUser {

    private Long routeId;
    private Long userId;
    private Date createTime;

    /**
     * 获取路书ID
     *
     * @return routeId 路书ID
     */
    public Long getRouteId() {
        return routeId;
    }

    /**
     * 设置路书ID
     *
     * @param routeId 路书ID
     */
    public void setRouteId(Long routeId) {
        this.routeId = routeId;
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
