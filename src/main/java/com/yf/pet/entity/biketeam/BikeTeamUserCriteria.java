package com.yf.pet.entity.biketeam;

/**
 * 团队队员列表
 * Created by Infi on 17/5/12.
 */
public class BikeTeamUserCriteria {
    private byte[] sharingData;
    private Long lastUpdateTime;
    private Integer userId;
    private Long teamId;

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
     * 获取用户上一次同步的时间戳
     *
     * @return lastUpdateTime 用户上一次同步的时间戳
     */
    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置用户上一次同步的时间戳
     *
     * @param lastUpdateTime 用户上一次同步的时间戳
     */
    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取用户地理位置数据，APP需要自己设置存储格式，把用户的地理位置信息都放在一个byte[]中
     *
     * @return sharingData 用户地理位置数据，APP需要自己设置存储格式，把用户的地理位置信息都放在一个byte[]中
     */
    public byte[] getSharingData() {
        return sharingData;
    }

    /**
     * 设置用户地理位置数据，APP需要自己设置存储格式，把用户的地理位置信息都放在一个byte[]中
     *
     * @param sharingData 用户地理位置数据，APP需要自己设置存储格式，把用户的地理位置信息都放在一个byte[]中
     */
    public void setSharingData(byte[] sharingData) {
        this.sharingData = sharingData;
    }
}
