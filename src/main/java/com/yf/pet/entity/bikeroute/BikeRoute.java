package com.yf.pet.entity.bikeroute;

import java.util.Date;

/**
 * 自行车导航路书实体类
 * Created by Infi on 17/5/19.
 */
public class BikeRoute {
    private Long routeId;
    private String routeName;
    private Long labelId;
    private byte[] routeFile;
    private String routeMD5;
    private Long creator;
    private String remarks;
    private Date createTime;
    private Integer status;
    private Double distance;
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
     * 获取路书距离距离（米）
     *
     * @return distance 路书距离距离（米）
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * 设置路书距离距离（米）
     *
     * @param distance 路书距离距离（米）
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * 获取路书ID，使用分布式主键生成器生成
     *
     * @return routeId 路书ID，使用分布式主键生成器生成
     */
    public Long getRouteId() {
        return routeId;
    }

    /**
     * 设置路书ID，使用分布式主键生成器生成
     *
     * @param routeId 路书ID，使用分布式主键生成器生成
     */
    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    /**
     * 获取路书名称
     *
     * @return routeName 路书名称
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * 设置路书名称
     *
     * @param routeName 路书名称
     */
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /**
     * 获取骑行数据ID，路书是骑行轨迹转换过来的，用于判断路书是否重复提交
     *
     * @return labelId 骑行数据ID，路书是骑行轨迹转换过来的，用于判断路书是否重复提交
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 设置骑行数据ID，路书是骑行轨迹转换过来的，用于判断路书是否重复提交
     *
     * @param labelId 骑行数据ID，路书是骑行轨迹转换过来的，用于判断路书是否重复提交
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * 获取路书文件，APP压缩如果文件在65kb以内，就用blob数据类型保存，如果大于65kb，就使用MEDIUMBLOB（16mb存储容量）保存
     *
     * @return routeFile 路书文件，APP压缩如果文件在65kb以内，就用blob数据类型保存，如果大于65kb，就使用MEDIUMBLOB（16mb存储容量）保存
     */
    public byte[] getRouteFile() {
        return routeFile;
    }

    /**
     * 设置路书文件，APP压缩如果文件在65kb以内，就用blob数据类型保存，如果大于65kb，就使用MEDIUMBLOB（16mb存储容量）保存
     *
     * @param routeFile 路书文件，APP压缩如果文件在65kb以内，就用blob数据类型保存，如果大于65kb，就使用MEDIUMBLOB（16mb存储容量）保存
     */
    public void setRouteFile(byte[] routeFile) {
        this.routeFile = routeFile;
    }

    /**
     * 获取路书文件md5校验码，用于APP校验下载的路书文件的完整性
     *
     * @return routeMD5 路书文件md5校验码，用于APP校验下载的路书文件的完整性
     */
    public String getRouteMD5() {
        return routeMD5;
    }

    /**
     * 设置路书文件md5校验码，用于APP校验下载的路书文件的完整性
     *
     * @param routeMD5 路书文件md5校验码，用于APP校验下载的路书文件的完整性
     */
    public void setRouteMD5(String routeMD5) {
        this.routeMD5 = routeMD5;
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
     * 获取路书说明
     *
     * @return remarks 路书说明
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置路书说明
     *
     * @param remarks 路书说明
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取创建时间，时间戳，服务器utc时间
     *
     * @return createTime 创建时间，时间戳，服务器utc时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间，时间戳，服务器utc时间
     *
     * @param createTime 创建时间，时间戳，服务器utc时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /**
     * 获取路书状态，1：正常，2：删除只有作者能删除该路书
     *
     * @return status 路书状态，1：正常，2：删除只有作者能删除该路书
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置路书状态，1：正常，2：删除只有作者能删除该路书
     *
     * @param status 路书状态，1：正常，2：删除只有作者能删除该路书
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
