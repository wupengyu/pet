package com.yf.pet.entity.dailytarget;

import java.util.Date;

/**
 * 用户目标值记录
 * Created by Infi on 17/3/23.
 */
public class DailyTarget {

    private Integer id;
    private Long userId;
    private Integer targetType;
    private Integer targetValue;
    private Integer happenDate;
    private Date createDate;
    private Integer status;


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
     * 获取设置目标类型，1-目标运动时间(秒)，2-目标卡路里，单位（小卡）APP把大卡转化为小卡提交到服务器
     *
     * @return targetType 设置目标类型，1-目标运动时间(秒)，2-目标卡路里，单位（小卡）APP把大卡转化为小卡提交到服务器
     */
    public Integer getTargetType() {
        return targetType;
    }

    /**
     * 设置设置目标类型，1-目标运动时间(秒)，2-目标卡路里，单位（小卡）APP把大卡转化为小卡提交到服务器
     *
     * @param targetType 设置目标类型，1-目标运动时间(秒)，2-目标卡路里，单位（小卡）APP把大卡转化为小卡提交到服务器
     */
    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    /**
     * 获取目标值，如果是运动目标-卡路里数(单位：大卡，即千卡)，如果是睡眠-是睡眠时间(单位:秒)
     *
     * @return targetValue 目标值，如果是运动目标-卡路里数(单位：大卡，即千卡)，如果是睡眠-是睡眠时间(单位:秒)
     */
    public Integer getTargetValue() {
        return targetValue;
    }

    /**
     * 设置目标值，如果是运动目标-卡路里数(单位：大卡，即千卡)，如果是睡眠-是睡眠时间(单位:秒)
     *
     * @param targetValue 目标值，如果是运动目标-卡路里数(单位：大卡，即千卡)，如果是睡眠-是睡眠时间(单位:秒)
     */
    public void setTargetValue(Integer targetValue) {
        this.targetValue = targetValue;
    }

    /**
     * 获取设置日期，发生日期，因为存在客户端设置，隔天上传的情况，所以此日期为实际的日期
     *
     * @return happenDate 设置日期，发生日期，因为存在客户端设置，隔天上传的情况，所以此日期为实际的日期
     */
    public Integer getHappenDate() {
        return happenDate;
    }

    /**
     * 设置设置日期，发生日期，因为存在客户端设置，隔天上传的情况，所以此日期为实际的日期
     *
     * @param happenDate 设置日期，发生日期，因为存在客户端设置，隔天上传的情况，所以此日期为实际的日期
     */
    public void setHappenDate(Integer happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取创建时间
     *
     * @return createDate 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取标签状态（没用到，不知道什么含义）
     *
     * @return status 标签状态（没用到，不知道什么含义）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置标签状态（没用到，不知道什么含义）
     *
     * @param status 标签状态（没用到，不知道什么含义）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
