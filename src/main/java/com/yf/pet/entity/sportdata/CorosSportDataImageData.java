package com.yf.pet.entity.sportdata;

/**
 * Coros运动数据结构,包括sportData和imageData
 * Created by Infi on 17/5/10.
 */
public class CorosSportDataImageData {
    private String uuid;
    private byte[] Data;
    private Integer mode;

    private Integer dataLength;


    /**
     * 获取数据长度
     *
     * @return dataLength 数据长度
     */
    public Integer getDataLength() {
        return dataLength;
    }

    /**
     * 设置数据长度
     *
     * @param dataLength 数据长度
     */
    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    /**
     * 获取运动数据类型，8:跑步训练,9:骑行,10:游泳
     *
     * @return mode 运动数据类型，8:跑步训练,9:骑行,10:游泳
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 设置运动数据类型，8:跑步训练,9:骑行,10:游泳
     *
     * @param mode 运动数据类型，8:跑步训练,9:骑行,10:游泳
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    /**
     * 获取APP生成的运动数据UUID
     *
     * @return uuid APP生成的运动数据UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置APP生成的运动数据UUID
     *
     * @param uuid APP生成的运动数据UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取运动数据或者缩率图数据
     *
     * @return Data 运动数据或者缩率图数据
     */
    public byte[] getData() {
        return Data;
    }

    /**
     * 设置运动数据或者缩率图数据
     *
     * @param Data 运动数据或者缩率图数据
     */
    public void setData(byte[] Data) {
        this.Data = Data;
    }

}
