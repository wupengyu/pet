package com.yf.pet.entity.user;

import java.util.Date;

/**
 * 用户信息
 * Created by Infi on 17/3/22.
 */
public class UserInfo {

    private Long userId;
    private String nickname;
    private Integer sex;
    private Float stature;
    private Float weight;
    private Integer birthday;
    private String headPic;
    private String longitude;
    private String latitude;
    private String countryCode;
    private Integer unit;
    private Date updateDate;
    private Date createDate;
    private Integer registerTimezone;
    private Long registerTimestamp;
    private Integer timezone;
    private Integer firstDayOfWeek;
    private String phoneCode;
    private String contactNote;
    private String contactsMobile;
    private String contactCountryCode;

    /**
     * 获取经济联系人所在国家区域代码
     *
     * @return contactCountryCode 经济联系人所在国家区域代码
     */
    public String getContactCountryCode() {
        return contactCountryCode;
    }

    /**
     * 设置经济联系人所在国家区域代码
     *
     * @param contactCountryCode 经济联系人所在国家区域代码
     */
    public void setContactCountryCode(String contactCountryCode) {
        this.contactCountryCode = contactCountryCode;
    }

    /**
     * 获取紧急联系人电话国际区号，如中国的区号是+86
     *
     * @return phoneCode 紧急联系人电话国际区号，如中国的区号是+86
     */
    public String getPhoneCode() {
        return phoneCode;
    }

    /**
     * 设置紧急联系人电话国际区号，如中国的区号是+86
     *
     * @param phoneCode 紧急联系人电话国际区号，如中国的区号是+86
     */
    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    /**
     * 获取紧急联系人备注，一般保存紧急联系人名字
     *
     * @return contactNote 紧急联系人备注，一般保存紧急联系人名字
     */
    public String getContactNote() {
        return contactNote;
    }

    /**
     * 设置紧急联系人备注，一般保存紧急联系人名字
     *
     * @param contactNote 紧急联系人备注，一般保存紧急联系人名字
     */
    public void setContactNote(String contactNote) {
        this.contactNote = contactNote;
    }

    /**
     * 获取紧急联系人电话
     *
     * @return contactsMobile 紧急联系人电话
     */
    public String getContactsMobile() {
        return contactsMobile;
    }

    /**
     * 设置紧急联系人电话
     *
     * @param contactsMobile 紧急联系人电话
     */
    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
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

    /**
     * 获取用户当前所在时区
     *
     * @return timezone 用户当前所在时区
     */
    public Integer getTimezone() {
        return timezone;
    }

    /**
     * 设置用户当前所在时区
     *
     * @param timezone 用户当前所在时区
     */
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    /**
     * 获取用户注册时所在时区（正负96之间）
     *
     * @return registerTimezone 用户注册时所在时区（正负96之间）
     */
    public Integer getRegisterTimezone() {
        return registerTimezone;
    }

    /**
     * 设置用户注册时所在时区（正负96之间）
     *
     * @param registerTimezone 用户注册时所在时区（正负96之间）
     */
    public void setRegisterTimezone(Integer registerTimezone) {
        this.registerTimezone = registerTimezone;
    }

    /**
     * 获取用户注册时所在时区的时间戳(秒)
     *
     * @return registerTimestamp 用户注册时所在时区的时间戳(秒)
     */
    public Long getRegisterTimestamp() {
        return registerTimestamp;
    }

    /**
     * 设置用户注册时所在时区的时间戳(秒)
     *
     * @param registerTimestamp 用户注册时所在时区的时间戳(秒)
     */
    public void setRegisterTimestamp(Long registerTimestamp) {
        this.registerTimestamp = registerTimestamp;
    }
    /**
     * 获取用户所在国家编码，比如中国：CN，使用全大写字母表示
     *
     * @return countryCode 用户所在国家编码，比如中国：CN，使用全大写字母表示
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置用户所在国家编码，比如中国：CN，使用全大写字母表示
     *
     * @param countryCode 用户所在国家编码，比如中国：CN，使用全大写字母表示
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 获取公英制。0：表示公制，1：表示英制，默认是公制
     *
     * @return unit 公英制。0：表示公制，1：表示英制，默认是公制
     */
    public Integer getUnit() {
        return unit;
    }

    /**
     * 设置公英制。0：表示公制，1：表示英制，默认是公制
     *
     * @param unit 公英制。0：表示公制，1：表示英制，默认是公制
     */
    public void setUnit(Integer unit) {
        this.unit = unit;
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
     * 获取昵称
     *
     * @return nickname 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    /**
     * 获取头像的URL
     *
     * @return headPic 头像的URL
     */
    public String getHeadPic() {
        return headPic;
    }

    /**
     * 设置头像的URL
     *
     * @param headPic 头像的URL
     */
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }


    /**
     * 获取最后上传的经度
     *
     * @return longitude 最后上传的经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置最后上传的经度
     *
     * @param longitude 最后上传的经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取最后上传的纬度
     *
     * @return latitude 最后上传的纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置最后上传的纬度
     *
     * @param latitude 最后上传的纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取修改时间
     *
     * @return updateDate 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
}
