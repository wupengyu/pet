package com.yf.pet.entity.medal;

/**
 * 勋章实体
 * Created by Infi on 17/6/13.
 */
public class Medal {

    private Integer id;
    private String name;
    private String detail;

    /**
     * 获取勋章主键
     *
     * @return id 勋章主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置勋章主键
     *
     * @param id 勋章主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取勋章名称
     *
     * @return name 勋章名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置勋章名称
     *
     * @param name 勋章名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取勋章描述
     *
     * @return detail 勋章描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置勋章描述
     *
     * @param detail 勋章描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
