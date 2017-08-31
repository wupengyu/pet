package com.yf.pet.entity.medal;

/**
 * 勋章规则表
 * Created by Infi on 17/6/13.
 */
public class MedalRule {
    private Integer id;
    private String name;
    private double standardValue;
    private String detail;
    private Integer medalId;
    private int mode;
    private int medalType;

    /**
     * 获取运动类型，8：跑步、9：骑行、10：游泳
     *
     * @return mode 运动类型，8：跑步、9：骑行、10：游泳
     */
    public int getMode() {
        return mode;
    }

    /**
     * 设置运动类型，8：跑步、9：骑行、10：游泳
     *
     * @param mode 运动类型，8：跑步、9：骑行、10：游泳
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * 获取勋章类型。1：单次达标获得勋章，2：累计里程获得勋章
     *
     * @return medalType 勋章类型。1：单次达标获得勋章，2：累计里程获得勋章
     */
    public int getMedalType() {
        return medalType;
    }

    /**
     * 设置勋章类型。1：单次达标获得勋章，2：累计里程获得勋章
     *
     * @param medalType 勋章类型。1：单次达标获得勋章，2：累计里程获得勋章
     */
    public void setMedalType(int medalType) {
        this.medalType = medalType;
    }

    /**
     * 获取勋章ID
     *
     * @return medalId 勋章ID
     */
    public Integer getMedalId() {
        return medalId;
    }

    /**
     * 设置勋章ID
     *
     * @param medalId 勋章ID
     */
    public void setMedalId(Integer medalId) {
        this.medalId = medalId;
    }

    /**
     * 获取勋章标准表
     *
     * @return id 勋章标准表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置勋章标准表
     *
     * @param id 勋章标准表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取勋章标准名称
     *
     * @return name 勋章标准名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置勋章标准名称
     *
     * @param name 勋章标准名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取标准值，达到这个标准值，就获得这个奖励
     *
     * @return standardValue 标准值，达到这个标准值，就获得这个奖励
     */
    public double getStandardValue() {
        return standardValue;
    }

    /**
     * 设置标准值，达到这个标准值，就获得这个奖励
     *
     * @param standardValue 标准值，达到这个标准值，就获得这个奖励
     */
    public void setStandardValue(double standardValue) {
        this.standardValue = standardValue;
    }

    /**
     * 获取说明备注信息
     *
     * @return detail 说明备注信息
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置说明备注信息
     *
     * @param detail 说明备注信息
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
