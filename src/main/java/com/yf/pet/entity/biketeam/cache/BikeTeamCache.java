package com.yf.pet.entity.biketeam.cache;


import com.yf.pet.entity.biketeam.BikeTeamUser;

import java.util.HashMap;

/**
 * 团队缓存对象
 * Created by Infi on 17/5/17.
 */
public class BikeTeamCache {

    private long teamId;
    private HashMap<Long, BikeTeamUser> userDataMap;
    private long teamUpdateTime;

    /**
     * 获取团队ID
     *
     * @return teamId 团队ID
     */
    public long getTeamId() {
        return teamId;
    }

    /**
     * 设置团队ID
     *
     * @param teamId 团队ID
     */
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取用户缓存对象
     *
     * @return userDataMap 用户缓存对象
     */
    public HashMap<Long, BikeTeamUser> getUserDataMap() {
        return userDataMap;
    }

    /**
     * 设置用户缓存对象
     *
     * @param userDataMap 用户缓存对象
     */
    public void setUserDataMap(HashMap<Long, BikeTeamUser> userDataMap) {
        this.userDataMap = userDataMap;
    }

    /**
     * 获取团队信息最后更新时间
     *
     * @return teamUpdateTime 团队信息最后更新时间
     */
    public long getTeamUpdateTime() {
        return teamUpdateTime;
    }

    /**
     * 设置团队信息最后更新时间
     *
     * @param teamUpdateTime 团队信息最后更新时间
     */
    public void setTeamUpdateTime(long teamUpdateTime) {
        this.teamUpdateTime = teamUpdateTime;
    }
}
