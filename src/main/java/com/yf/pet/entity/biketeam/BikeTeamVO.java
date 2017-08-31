package com.yf.pet.entity.biketeam;

import java.util.List;

/**
 * 自行车导航团队实体类
 * Created by Infi on 17/5/12.
 */
public class BikeTeamVO {
    private Long teamId;
    private Long teamUpdateTime;
    private List<BikeTeamUser> userList;
    private boolean isAllUsers;

    /**
     * 获取是否返回全部的用户信息
     *
     * @return isAllUsers 是否返回全部的用户信息
     */
    public boolean getIsAllUsers() {
        return isAllUsers;
    }

    /**
     * 设置是否返回全部的用户信息
     *
     * @param isAllUsers 是否返回全部的用户信息
     */
    public void setIsAllUsers(boolean isAllUsers) {
        this.isAllUsers = isAllUsers;
    }

    /**
     * 获取用户信息列表
     *
     * @return userList 用户信息列表
     */
    public List<BikeTeamUser> getUserList() {
        return userList;
    }

    /**
     * 设置用户信息列表
     *
     * @param userList 用户信息列表
     */
    public void setUserList(List<BikeTeamUser> userList) {
        this.userList = userList;
    }

    /**
     * 获取团队信息更新时间戳
     *
     * @return teamUpdateTime 团队信息更新时间戳
     */
    public Long getTeamUpdateTime() {
        return teamUpdateTime;
    }

    /**
     * 设置团队信息更新时间戳
     *
     * @param teamUpdateTime 团队信息更新时间戳
     */
    public void setTeamUpdateTime(Long teamUpdateTime) {
        this.teamUpdateTime = teamUpdateTime;
    }

    /**
     * 获取团队主键,使用分布是主键生成器生成
     *
     * @return teamId 团队主键,使用分布是主键生成器生成
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * 设置团队主键,使用分布是主键生成器生成
     *
     * @param teamId 团队主键,使用分布是主键生成器生成
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

}
