package com.yf.pet.entity.country;

/**
 * 国家信息实体类
 * Created by Infi on 17/6/9.
 */
public class CorosCountry {

    private String code;
    private String countryName;
    private String countryNameEN;
    private Integer firstDayOfWeek;

    /**
     * 获取国家编码
     *
     * @return code 国家编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置国家编码
     *
     * @param code 国家编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取国家中文名称
     *
     * @return countryName 国家中文名称
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 设置国家中文名称
     *
     * @param countryName 国家中文名称
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 获取国家英文名称
     *
     * @return countryNameEN 国家英文名称
     */
    public String getCountryNameEN() {
        return countryNameEN;
    }

    /**
     * 设置国家英文名称
     *
     * @param countryNameEN 国家英文名称
     */
    public void setCountryNameEN(String countryNameEN) {
        this.countryNameEN = countryNameEN;
    }

    /**
     * 获取周首日：1：周日，2：周一，3：周二，4：周三，5：周四，6：周五，7：周六
     *
     * @return firstDayOfWeek 周首日：1：周日，2：周一，3：周二，4：周三，5：周四，6：周五，7：周六
     */
    public Integer getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    /**
     * 设置周首日：1：周日，2：周一，3：周二，4：周三，5：周四，6：周五，7：周六
     *
     * @param firstDayOfWeek 周首日：1：周日，2：周一，3：周二，4：周三，5：周四，6：周五，7：周六
     */
    public void setFirstDayOfWeek(Integer firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }
}
