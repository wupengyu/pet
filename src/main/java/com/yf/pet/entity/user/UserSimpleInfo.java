package com.yf.pet.entity.user;

/**
 * 用户主要信息,其他模块提供用户主要信息
 * Created by Infi on 17/3/22.
 */
public class UserSimpleInfo {

    private Long userId;
    private Integer sex;
    private Float stature;
    private Float weight;
    private Integer birthday;

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
     * 获取性别 0-男性，1-女性，2-其他
     *
     * @return sex 性别 0-男性，1-女性，2-其他
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别 0-男性，1-女性，2-其他
     *
     * @param sex 性别 0-男性，1-女性，2-其他
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取身高，单位为CM
     *
     * @return stature 身高，单位为CM
     */
    public Float getStature() {
        return stature;
    }

    /**
     * 设置身高，单位为CM
     *
     * @param stature 身高，单位为CM
     */
    public void setStature(Float stature) {
        this.stature = stature;
    }

    /**
     * 获取体重，单位为公斤
     *
     * @return weight 体重，单位为公斤
     */
    public Float getWeight() {
        return weight;
    }

    /**
     * 设置体重，单位为公斤
     *
     * @param weight 体重，单位为公斤
     */
    public void setWeight(Float weight) {
        this.weight = weight;
    }

    /**
     * 获取出生年份
     *
     * @return birthday 出生年份
     */
    public Integer getBirthday() {
        return birthday;
    }

    /**
     * 设置出生年份
     *
     * @param birthday 出生年份
     */
    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }
}
