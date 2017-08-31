package com.yf.pet.entity.biketeam;

/**
 * 团队队员列表
 * Created by Infi on 17/5/12.
 */
public class BikeTeamUser {
    private Long teamId;
    private Long userId;
    private Long joinTime;
    private byte[] sharingData;
    private Long updateTime;
    private Integer ridingEnd;
    private boolean isExit;
    private String sharingDataStr;
    private Long lastUpdateTime;
    private String nickname;
    private String headPic;

    /**
     * 获取昵称
     *
     * @return nickname 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户头像地址
     *
     * @return headPic 用户头像地址
     */
    public String getHeadPic() {
        return headPic;
    }

    /**
     * 设置用户头像地址
     *
     * @param headPic 用户头像地址
     */
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
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
     * 获取用户地理位置数据byte[]转成字符串传递给APP
     *
     * @return sharingDataStr 用户地理位置数据byte[]转成字符串传递给APP
     */
    public String getSharingDataStr() {
        return sharingDataStr;
    }

    /**
     * 设置用户地理位置数据byte[]转成字符串传递给APP
     *
     * @param sharingDataStr 用户地理位置数据byte[]转成字符串传递给APP
     */
    public void setSharingDataStr(String sharingDataStr) {
        this.sharingDataStr = sharingDataStr;
    }

    /**
     * 获取是否退出
     *
     * @return isExit 是否退出
     */
    public boolean getIsExit() {
        return isExit;
    }

    /**
     * 设置是否退出
     *
     * @param isExit 是否退出
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * 获取结束骑行,1:表示结束骑行，0：表示未结束骑行
     *
     * @return ridingEnd 结束骑行,1:表示结束骑行，0：表示未结束骑行
     */
    public Integer getRidingEnd() {
        return ridingEnd;
    }

    /**
     * 设置结束骑行,1:表示结束骑行，0：表示未结束骑行
     *
     * @param ridingEnd 结束骑行,1:表示结束骑行，0：表示未结束骑行
     */
    public void setRidingEnd(Integer ridingEnd) {
        this.ridingEnd = ridingEnd;
    }


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
     * 获取加入团队的时间戳
     *
     * @return joinTime 加入团队的时间戳
     */
    public Long getJoinTime() {
        return joinTime;
    }

    /**
     * 设置加入团队的时间戳
     *
     * @param joinTime 加入团队的时间戳
     */
    public void setJoinTime(Long joinTime) {
        this.joinTime = joinTime;
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

    /**
     * 获取更新时间，地理位置和状态
     *
     * @return updateTime 更新时间，地理位置和状态
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间，地理位置和状态
     *
     * @param updateTime 更新时间，地理位置和状态
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
