package com.yf.pet.entity.firmware;

/**
 * 测试用户
 * Created by Infi on 17/6/28.
 */
public class CSUser {
    private Integer id;
    private String deviceId;
    private String firmwareType;

    /**
     * 获取id
     *
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取/ 设备ID
     *
     * @return deviceId / 设备ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置/ 设备ID
     *
     * @param deviceId / 设备ID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取/ 固件类型
     *
     * @return firmwareType / 固件类型
     */
    public String getFirmwareType() {
        return firmwareType;
    }

    /**
     * 设置/ 固件类型
     *
     * @param firmwareType / 固件类型
     */
    public void setFirmwareType(String firmwareType) {
        this.firmwareType = firmwareType;
    }
}
