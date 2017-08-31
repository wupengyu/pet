package com.yf.pet.entity.biketeam;

/**
 * 自行车导航团队条件实体类
 * Created by Infi on 17/5/12.
 */
public class BikeTeamCriteria {
    private String verifyCode;
    private Double longitude;
    private Double latitude;
    private Long userId;

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
     * 获取团队口令，团队验证码，4位数字
     *
     * @return verifyCode 团队口令，团队验证码，4位数字
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * 设置团队口令，团队验证码，4位数字
     *
     * @param verifyCode 团队口令，团队验证码，4位数字
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * 获取团队经度：创建人的经度保留4位小数
     *
     * @return longitude 团队经度：创建人的经度保留4位小数
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 设置团队经度：创建人的经度保留4位小数
     *
     * @param longitude 团队经度：创建人的经度保留4位小数
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取团队纬度：创建人的纬度保留4位小数
     *
     * @return latitude 团队纬度：创建人的纬度保留4位小数
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 设置团队纬度：创建人的纬度保留4位小数
     *
     * @param latitude 团队纬度：创建人的纬度保留4位小数
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
