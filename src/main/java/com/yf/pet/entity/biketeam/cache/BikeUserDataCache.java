package com.yf.pet.entity.biketeam.cache;

/**
 * 用户数据缓存对象
 * Created by Infi on 17/5/17.
 */
public class BikeUserDataCache {

    private Integer userId;
    private byte[] sharingData;
    private boolean isExit;
    private long lastUpdateTime;

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
     * 获取用户数据，包括地理位置信息
     *
     * @return sharingData 用户数据，包括地理位置信息
     */
    public byte[] getSharingData() {
        return sharingData;
    }

    /**
     * 设置用户数据，包括地理位置信息
     *
     * @param sharingData 用户数据，包括地理位置信息
     */
    public void setSharingData(byte[] sharingData) {
        this.sharingData = sharingData;
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
     * 获取最后更新时间
     *
     * @return lastUpdateTime 最后更新时间
     */
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param lastUpdateTime 最后更新时间
     */
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
