package com.yf.pet.entity.biketeam;

/**
 * 自行车导航团队实体类
 * Created by Infi on 17/5/12.
 */
public class BikeTeam {
    private Long teamId;
    private Integer verifyCode;
    private Long admin;
    private String teamName;
    private byte[] routeFile;
    private String routeFileStr;
    private Long creator;
    private Long createTime;
    private Long overTime;
    private Integer status;
    private Long headUser;
    private Long tailUser;
    private Double longitude;
    private Double latitude;
    private Long updateTime;
    private Long gridId;
    private Long teamUpdateTime;
    private Integer isValid;


    /**
     * 获取是否有效，1：有效团队，0：无效团队，被删除或者已过期
     *
     * @return isValid 是否有效，1：有效团队，0：无效团队，被删除或者已过期
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置是否有效，1：有效团队，0：无效团队，被删除或者已过期
     *
     * @param isValid 是否有效，1：有效团队，0：无效团队，被删除或者已过期
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * 获取团队路书转成成字符串放在json中一起返回给APP
     *
     * @return routeFileStr 团队路书转成成字符串放在json中一起返回给APP
     */
    public String getRouteFileStr() {
        return routeFileStr;
    }

    /**
     * 设置团队路书转成成字符串放在json中一起返回给APP
     *
     * @param routeFileStr 团队路书转成成字符串放在json中一起返回给APP
     */
    public void setRouteFileStr(String routeFileStr) {
        this.routeFileStr = routeFileStr;
    }

    /**
     * 获取团队指定的路书文件，可以是从其他平台下载下来的路书
     *
     * @return routeFile 团队指定的路书文件，可以是从其他平台下载下来的路书
     */
    public byte[] getRouteFile() {
        return routeFile;
    }

    /**
     * 设置团队指定的路书文件，可以是从其他平台下载下来的路书
     *
     * @param routeFile 团队指定的路书文件，可以是从其他平台下载下来的路书
     */
    public void setRouteFile(byte[] routeFile) {
        this.routeFile = routeFile;
    }

    /**
     * 获取状态：0:未开始，未结束，（默认值），1：开始，2：结束，团队第一个人开始骑行团队骑行就开始，最后一个人结束骑行，团队状态就是结束，当团队中所有用户都退出以后，团队是已删除状态
     *
     * @return status 状态：0:未开始，未结束，（默认值），1：开始，2：结束，团队第一个人开始骑行团队骑行就开始，最后一个人结束骑行，团队状态就是结束，当团队中所有用户都退出以后，团队是已删除状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0:未开始，未结束，（默认值），1：开始，2：结束，团队第一个人开始骑行团队骑行就开始，最后一个人结束骑行，团队状态就是结束，当团队中所有用户都退出以后，团队是已删除状态
     *
     * @param status 状态：0:未开始，未结束，（默认值），1：开始，2：结束，团队第一个人开始骑行团队骑行就开始，最后一个人结束骑行，团队状态就是结束，当团队中所有用户都退出以后，团队是已删除状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     * 获取团队口令，团队验证码，4位数字
     *
     * @return verifyCode 团队口令，团队验证码，4位数字
     */
    public Integer getVerifyCode() {
        return verifyCode;
    }

    /**
     * 设置团队口令，团队验证码，4位数字
     *
     * @param verifyCode 团队口令，团队验证码，4位数字
     */
    public void setVerifyCode(Integer verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * 获取群主，用户的ID
     *
     * @return admin 群主，用户的ID
     */
    public Long getAdmin() {
        return admin;
    }

    /**
     * 设置群主，用户的ID
     *
     * @param admin 群主，用户的ID
     */
    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    /**
     * 获取团队名称
     *
     * @return teamName 团队名称
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 设置团队名称
     *
     * @param teamName 团队名称
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    /**
     * 获取创建人的ID，用户ID
     *
     * @return creator 创建人的ID，用户ID
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建人的ID，用户ID
     *
     * @param creator 创建人的ID，用户ID
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间，服务器utc时间
     *
     * @return createTime 创建时间，服务器utc时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间，服务器utc时间
     *
     * @param createTime 创建时间，服务器utc时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取过期时间，目前一周以后为过期
     *
     * @return overTime 过期时间，目前一周以后为过期
     */
    public Long getOverTime() {
        return overTime;
    }

    /**
     * 设置过期时间，目前一周以后为过期
     *
     * @param overTime 过期时间，目前一周以后为过期
     */
    public void setOverTime(Long overTime) {
        this.overTime = overTime;
    }


    /**
     * 获取队头用户ID
     *
     * @return headUser 队头用户ID
     */
    public Long getHeadUser() {
        return headUser;
    }

    /**
     * 设置队头用户ID
     *
     * @param headUser 队头用户ID
     */
    public void setHeadUser(Long headUser) {
        this.headUser = headUser;
    }

    /**
     * 获取队尾用户ID
     *
     * @return tailUser 队尾用户ID
     */
    public Long getTailUser() {
        return tailUser;
    }

    /**
     * 设置队尾用户ID
     *
     * @param tailUser 队尾用户ID
     */
    public void setTailUser(Long tailUser) {
        this.tailUser = tailUser;
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

    /**
     * 获取团队更新时间戳
     *
     * @return updateTime 团队更新时间戳
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置团队更新时间戳
     *
     * @param updateTime 团队更新时间戳
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取团队经纬度所在网格ID
     *
     * @return gridId 团队经纬度所在网格ID
     */
    public Long getGridId() {
        return gridId;
    }

    /**
     * 设置团队经纬度所在网格ID
     *
     * @param gridId 团队经纬度所在网格ID
     */
    public void setGridId(Long gridId) {
        this.gridId = gridId;
    }
}
