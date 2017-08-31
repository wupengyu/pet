package com.yf.pet.entity.bikeroute;

/**
 * 自行车导航路书实体类
 * Created by Infi on 17/5/19.
 */
public class BikeRouteCritera {
    private Long routeId;
    private String routeName;
    private String remarks;
    private Integer pageIndex;
    private Integer userId;
    private Long labelId;

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
     * 获取分页查询索引，查询第几页数据
     *
     * @return pageIndex 分页查询索引，查询第几页数据
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置分页查询索引，查询第几页数据
     *
     * @param pageIndex 分页查询索引，查询第几页数据
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
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

}
