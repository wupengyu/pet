package com.yf.pet.entity.resource;

/**
 * 资源版本信息
 * Created by Infi on 17/6/9.
 */
public class ResourceVersion {

    private Integer id;
    private String resourceType;
    private Integer version;

    /**
     * 获取主键
     *
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源类型
     *
     * @return resourceType 资源类型
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型
     *
     * @param resourceType 资源类型
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取资源版本号
     *
     * @return version 资源版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置资源版本号
     *
     * @param version 资源版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}
