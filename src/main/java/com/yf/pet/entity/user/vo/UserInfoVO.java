package com.yf.pet.entity.user.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息返回对象
 * Created by Infi on 17/3/22.
 */
public class UserInfoVO {
    private String mobile;
    private String email;
    private String facebookId;
    private String weixinId;
    private String accessToken;
//    private Date validityDate;
    private Integer clientType;
    private Long userId;
    private String nickname;
    //start 20170710 edit by dinghui 新增微信和facebook昵称
    private String weixinName;//微信昵称
    private String facebookName;//facebook昵称
    //end
    private Integer sex;
    private Float stature;
    private Float weight;
    private Integer birthday;
    private String headPic;
    private String longitude;
    private String latitude;
    private Integer registerType;
    private String pwd;
    private String appKey;
    private String countryCode;
    private Integer unit;
    private Integer targetMotionTime;
    private Integer targetCalorie;
    private String ipAddress;
    private MultipartFile headPicFile;
//    private Date createDate;
    private String checkCode;
    private Integer activateStatus;
    private String newEmail;
    private Long registerDate;
    private String account;
    private Integer accountType;
    private Integer loginType;
    private Integer registerTimezone;
    private Long registerTimestamp;
    private Integer timezone;
    private String saveParseInfo;
    private Integer activateType;
    private String phoneCode;
    private String contactNote;
    private String contactsMobile;
    private String contactCountryCode;
    private String openNickname;
    private String unactivatedEmail;

    /**
     * 获取未激活的邮箱地址
     *
     * @return unactivatedEmail 未激活的邮箱地址
     */
    public String getUnactivatedEmail() {
        return unactivatedEmail;
    }

    /**
     * 设置未激活的邮箱地址
     *
     * @param unactivatedEmail 未激活的邮箱地址
     */
    public void setUnactivatedEmail(String unactivatedEmail) {
        this.unactivatedEmail = unactivatedEmail;
    }

    /**
     * 获取第三方帐号昵称
     *
     * @return openNickname 第三方帐号昵称
     */
    public String getOpenNickname() {
        return openNickname;
    }

    /**
     * 设置第三方帐号昵称
     *
     * @param openNickname 第三方帐号昵称
     */
    public void setOpenNickname(String openNickname) {
        this.openNickname = openNickname;
    }

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
     * 获取邮箱激活类型,1: 注册激活, 2:绑定激活
     *
     * @return activateType 邮箱激活类型,1: 注册激活, 2:绑定激活
     */
    public Integer getActivateType() {
        return activateType;
    }

    /**
     * 设置邮箱激活类型,1: 注册激活, 2:绑定激活
     *
     * @param activateType 邮箱激活类型,1: 注册激活, 2:绑定激活
     */
    public void setActivateType(Integer activateType) {
        this.activateType = activateType;
    }

    /**
     * 获取标签解析状态
     *
     * @return saveParseInfo 标签解析状态
     */
    public String getSaveParseInfo() {
        return saveParseInfo;
    }

    /**
     * 设置标签解析状态
     *
     * @param saveParseInfo 标签解析状态
     */
    public void setSaveParseInfo(String saveParseInfo) {
        this.saveParseInfo = saveParseInfo;
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
     * 获取登录类型 1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     *
     * @return loginType 登录类型 1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * 设置登录类型 1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     *
     * @param loginType 登录类型 1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }


    /**
     * 获取帐号，电话号码、邮箱地址、Facebook帐号ID，微信帐号ID
     *
     * @return account 帐号，电话号码、邮箱地址、Facebook帐号ID，微信帐号ID
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置帐号，电话号码、邮箱地址、Facebook帐号ID，微信帐号ID
     *
     * @param account 帐号，电话号码、邮箱地址、Facebook帐号ID，微信帐号ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取帐号类型，1：电话号码、2：邮箱地址、3：Facebook帐号ID，4：微信帐号ID
     *
     * @return accountType 帐号类型，1：电话号码、2：邮箱地址、3：Facebook帐号ID，4：微信帐号ID
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 设置帐号类型，1：电话号码、2：邮箱地址、3：Facebook帐号ID，4：微信帐号ID
     *
     * @param accountType 帐号类型，1：电话号码、2：邮箱地址、3：Facebook帐号ID，4：微信帐号ID
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     * 获取注册时间
     *
     * @return registerDate 注册时间
     */
    public Long getRegisterDate() {
        return registerDate;
    }

    /**
     * 设置注册时间
     *
     * @param registerDate 注册时间
     */
    public void setRegisterDate(Long registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 获取邮箱激活时，可以修改邮箱地址
     *
     * @return newEmail 邮箱激活时，可以修改邮箱地址
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * 设置邮箱激活时，可以修改邮箱地址
     *
     * @param newEmail 邮箱激活时，可以修改邮箱地址
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }


    /**
     * 获取是否激活，用于邮箱激活功能
     *
     * @return activateStatus 是否激活，用于邮箱激活功能
     */
    public Integer getActivateStatus() {
        return activateStatus;
    }

    /**
     * 设置是否激活，用于邮箱激活功能
     *
     * @param activateStatus 是否激活，用于邮箱激活功能
     */
    public void setActivateStatus(Integer activateStatus) {
        this.activateStatus = activateStatus;
    }

    /**
     * 获取验证码
     *
     * @return checkCode 验证码
     */
    public String getCheckCode() {
        return checkCode;
    }

    /**
     * 设置验证码
     *
     * @param checkCode 验证码
     */
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

//    /**
//     * 获取注册日期
//     *
//     * @return createDate 注册日期
//     */
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    /**
//     * 设置注册日期
//     *
//     * @param createDate 注册日期
//     */
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }


    /**
     * 获取用户头像文件
     *
     * @return headPicFile 用户头像文件
     */
    public MultipartFile getHeadPicFile() {
        return headPicFile;
    }

    /**
     * 设置用户头像文件
     *
     * @param headPicFile 用户头像文件
     */
    public void setHeadPicFile(MultipartFile headPicFile) {
        this.headPicFile = headPicFile;
    }

    /**
     * 获取最后登录的IP地址
     *
     * @return ipAddress 最后登录的IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置最后登录的IP地址
     *
     * @param ipAddress 最后登录的IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


    /**
     * 获取目标运动时间,单位（秒）
     *
     * @return targetMotionTime 目标运动时间,单位（秒）
     */
    public Integer getTargetMotionTime() {
        return targetMotionTime;
    }

    /**
     * 设置目标运动时间,单位（秒）
     *
     * @param targetMotionTime 目标运动时间,单位（秒）
     */
    public void setTargetMotionTime(Integer targetMotionTime) {
        this.targetMotionTime = targetMotionTime;
    }

    /**
     * 获取目标卡路里,单位（小卡）APP把大卡转化为小卡提交到服务器
     *
     * @return targetCalorie 目标卡路里,单位（小卡）APP把大卡转化为小卡提交到服务器
     */
    public Integer getTargetCalorie() {
        return targetCalorie;
    }

    /**
     * 设置目标卡路里,单位（小卡）APP把大卡转化为小卡提交到服务器
     *
     * @param targetCalorie 目标卡路里,单位（小卡）APP把大卡转化为小卡提交到服务器
     */
    public void setTargetCalorie(Integer targetCalorie) {
        this.targetCalorie = targetCalorie;
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
     * 获取密码解密密钥
     *
     * @return appKey 密码解密密钥
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 设置密码解密密钥
     *
     * @param appKey 密码解密密钥
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * 获取加密后的密码，
     *
     * @return pwd 加密后的密码，
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置加密后的密码，
     *
     * @param pwd 加密后的密码，
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    /**
     * 获取用户注册方式，1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     *
     * @return registerType 用户注册方式，1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     */
    public Integer getRegisterType() {
        return registerType;
    }

    /**
     * 设置用户注册方式，1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     *
     * @param registerType 用户注册方式，1：表示mobile，2：表示email，3：表示facebook，4：表示微信
     */
    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
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
     * 获取电话号码
     *
     * @return mobile 电话号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置电话号码
     *
     * @param mobile 电话号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取email
     *
     * @return email email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取facebook open id
     *
     * @return facebookId facebook open id
     */
    public String getFacebookId() {
        return facebookId;
    }

    /**
     * 设置facebook open id
     *
     * @param facebookId facebook open id
     */
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    /**
     * 获取微信open id
     *
     * @return weixinId 微信open id
     */
    public String getWeixinId() {
        return weixinId;
    }

    /**
     * 设置微信open id
     *
     * @param weixinId 微信open id
     */
    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }


    /**
     * 获取登陆成功，返回访问的令牌
     *
     * @return accessToken 登陆成功，返回访问的令牌
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置登陆成功，返回访问的令牌
     *
     * @param accessToken 登陆成功，返回访问的令牌
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

//    /**
//     * 获取令牌的有效期
//     *
//     * @return validityDate 令牌的有效期
//     */
//    public Date getValidityDate() {
//        return validityDate;
//    }
//
//    /**
//     * 设置令牌的有效期
//     *
//     * @param validityDate 令牌的有效期
//     */
//    public void setValidityDate(Date validityDate) {
//        this.validityDate = validityDate;
//    }


    /**
     * 获取客户端类型。1-Android，2-iOS
     *
     * @return clientType 客户端类型。1-Android，2-iOS
     */
    public Integer getClientType() {
        return clientType;
    }

    /**
     * 设置客户端类型。1-Android，2-iOS
     *
     * @param clientType 客户端类型。1-Android，2-iOS
     */
    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    /**
     * 获取微信昵称
     * @return
     */
    public String getWeixinName() {
        return weixinName;
    }

    public void setWeixinName(String weixinName) {
        this.weixinName = weixinName;
    }

    /**
     * 获取facebook昵称
     * @return
     */
    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

}
