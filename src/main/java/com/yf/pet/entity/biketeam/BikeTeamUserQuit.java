package com.yf.pet.entity.biketeam;

/**
 * 团队对应退出信息表
 * Created by Infi on 17/5/12.
 */
public class BikeTeamUserQuit {

    private Long teamId;
    private Integer userId;
    private Long quitTime;


    /**
     * 获取团队主键
     *
     * @return teamId 团队主键
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * 设置团队主键
     *
     * @param teamId 团队主键
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取用户ID
     *
     * @return userId 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取更新时间，地理位置和状态
     *
     * @return quitTime 更新时间，地理位置和状态
     */
    public Long getQuitTime() {
        return quitTime;
    }

    /**
     * 设置更新时间，地理位置和状态
     *
     * @param quitTime 更新时间，地理位置和状态
     */
    public void setQuitTime(Long quitTime) {
        this.quitTime = quitTime;
    }
}
