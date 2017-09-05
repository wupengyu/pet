package com.yf.pet.common;

/**
 * 状态结果枚举定义<br>
 * 1. 与丁慧 ReturnMessage 重合, 讨论优化。<br>
 * 2. 确保定义的result 与旧版的保持一致。<br>
 * <p>
 * 返回编码结构定义: x-xxx或者xx-xxx,[模块][错误]。<br>
 * 编码结构说明:<br>
 * 1. 如果是四位编码,第一位表示模块;如果是五位编码,前两位表示模块。<br>
 * 2. 在错误编码中模块可以是业务逻辑上的"模块",比如说公共模块,如果有必要可以分为手环绑定"模块",验证码"模块"<br>
 * 3. 最后三位表示错误编号。<br>
 * <p>
 * 返回编号分段定义:
 * 1. 0000: 表示操作成功
 * 2. 1001 - 1999: 表示系统错误信息
 * 3. 2001 - 2999: 表示账户模块
 * 4. 3001 - 3999: 表示用户模块
 * 6. 5001 - 5999: 表示数据同步模块
 * 7. 6001 - 6999: 表示用户活动模块
 * 9. 8001 - 8999: 表示公共模块(短信功能,手环激活功能)
 * 10. 9001 - 9999: 表示表盘固件模块
 * 11. 10001 - 10999: 表示数据统计模块
 * 13. 80001 - 81999: 表示服务器后台页面模块
 *
 * @author hudahua
 */
public enum ReturnMessageEnum {

    /**
     * 成功
     */
    RETURN_OK("0000", "ok"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR("1001", "system error"),

    //---------------------------------------------系统模块-------------------------------------------------------------//
    /**
     * token 为空
     */
    TOKEN_NULL(ModuleTypeEnum.SYSTEM,"001","token is null"),

    /**
     * token 无效
     */
    TOKEN_INVALID(ModuleTypeEnum.SYSTEM,"002","token is invalid"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(ModuleTypeEnum.SYSTEM,"003","parameter error"),


    //------------------------------------------------账户模块----------------------------------------------------------//

    /**
     * 用户注册方式不能为空
     */
    REGISTER_TEYP_NULL(ModuleTypeEnum.ACCOUNT, "001", "register type can not be null"),

    /**
     * 邮箱不能为空
     */
    EMAIL_NULL(ModuleTypeEnum.ACCOUNT, "002", "email can not be null"),
    /**
     * 密码不能为空
     */
    PASSWORD_NULL(ModuleTypeEnum.ACCOUNT, "003", "password can not be null"),

    /**
     * facebook id 不能为空
     */
    OPEN_ID_NULL(ModuleTypeEnum.ACCOUNT, "004", "open id can not be null"),

    /**
     * 注册时区不能为空
     */
    REGISTER_TIMEZONE_NULL(ModuleTypeEnum.ACCOUNT, "005", "register timezone can not be null"),
    /**
     * 帐号已经存在
     */
    ACCOUNT_IS_EXIST(ModuleTypeEnum.ACCOUNT, "006", "account is exist"),
    /**
     * 帐号不存在
     */
    ACCOUNT_NOT_EXIST(ModuleTypeEnum.ACCOUNT, "007", "account not exist"),

    /**
     * 这里只能用邮箱注册
     */
    REGISTER_TEYP_EMAIL(ModuleTypeEnum.ACCOUNT, "008", "register type must be email"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR(ModuleTypeEnum.ACCOUNT, "009", "password error"),

    /**
     * 第三方账户类型不能为空
     */
    OPEN_TYPE_NULL(ModuleTypeEnum.ACCOUNT, "010", "open account type can not be null"),

//    /**
//     * 只用一种帐号类型存在,不能解除绑定
//     */
//    ACCOUNT_IS_ONLY_ONE(ModuleTypeEnum.ACCOUNT, "002", "There is only one account type, cannot remove binding"),
//    /**
//     * 帐号没有绑定,比如mobile没有绑定过,不能解除mobile的绑定
//     */
//    ACCOUNT_NOT_BIND(ModuleTypeEnum.ACCOUNT, "003", "account not bind"),
//    /**
//     * 邮箱地址还没有绑定,不能激活
//     */
//    EMAIL_NOT_BIND(ModuleTypeEnum.ACCOUNT, "004", "emal not bind, cannot activate"),
//
//    /**
//     * 邮箱已经注册,已经存在
//     */
//    EMAIL_IS_EXIST(ModuleTypeEnum.ACCOUNT, "005", "emal is exist"),
//    /**
//     * 邮箱已经被激活
//     */
//    EMAIL_IS_ACTIVATED(ModuleTypeEnum.ACCOUNT, "006", "email is activated"),
//
//    /**
//     * 邮箱已注册,邮件已过期,请重新发送激活邮件
//     */
//    EMIL_NOT_ACTIVATED_MAIL_EXPIRED(ModuleTypeEnum.ACCOUNT, "010", "邮箱未激活,激活邮件已过期,请重新发送邮件"),
//    /**
//     * 邮箱已注册,邮件未过期,请打开激活邮件进行激活
//     */
//    EMIL_NOT_ACTIVATED_MAIL_NOT_EXPIRED(ModuleTypeEnum.ACCOUNT, "011", "邮箱已注册,邮件未过期,请打开激活邮件进行激活"),
//    /**
//     * 邮箱已经绑定,未激活
//     */
//    EMAIL_IS_BIND_NOT_ACTIVATED(ModuleTypeEnum.ACCOUNT, "012", "邮箱已经绑定,未激活,请激活"),
//    /**
//     * 帐号无效
//     */
//    ACCOUNT_IS_INVALID(ModuleTypeEnum.ACCOUNT, "013", "account is invalid"),
//    /**
//     * 帐号已经被注册，不能绑定
//     */
//    ACCOUNT_REGISTERED(ModuleTypeEnum.ACCOUNT, "014", "User has been registered, Cannot bind"),
//    /**
//     * 邮箱已经绑定,不能重复绑定
//     */
//    EMAIL_IS_BIND(ModuleTypeEnum.ACCOUNT, "015", "emal is bind"),





    //-----------------------------------------------------账户模块结束--------------------------------------------//
    /**
     * 时区信息为空
     */
    TIMEZONE_IS_NULL(ModuleTypeEnum.USER, "001", "timezone is null"),
    /**
     * 紧急联系人电话号码格式错误
     */
    INCOMPLETE_PHONE_NO(ModuleTypeEnum.USER, "002", "Incomplete phone No"),
    /**
     * 紧急联系人电话和备注没有变化，不需要修改
     */
    CONTACT_UNCHANGED(ModuleTypeEnum.USER, "003", "Emergency Contact and remark unchanged, no need to update"),
    /**
     * 邮件发送失败
     */
    FAILED_TO_SEND_EMAIL(ModuleTypeEnum.SYSTEM, "080", "Failed to send email"),
    /**
     * 数据为空
     */
    DATA_IS_NULL(ModuleTypeEnum.DATA_SYNC, "081", "Data is null"),
    /**
     * 用户已经加入团队
     */
    USER_JOIN_TEAM(ModuleTypeEnum.BIKE_TEAM, "001", "The user has to join the team"),
    /**
     * 用户没有加入团队
     */
    USER_NOT_JOIN_TEAM(ModuleTypeEnum.BIKE_TEAM, "002", "The user not join the team"),
    /**
     * 团队无效,不存在或者已经过期
     */
    USER_TEAM_IS_INVALID(ModuleTypeEnum.BIKE_TEAM, "003", "team is invalid"),
    /**
     * 用户不是团队管理员,不能设置团队信息
     */
    USER_IS_NOT_ADMIN(ModuleTypeEnum.BIKE_TEAM, "004", "user is not admin"),
    /**
     * 管理员不能删除他自己
     */
    ADMIN_CONNOT_BE_DELETE(ModuleTypeEnum.BIKE_TEAM, "005", "The administrator cannot be deleted"),
    /**
     * 路书已经上传
     */
    ROUTE_IS_EXSIT(ModuleTypeEnum.BIKE_ROUTE, "001", "route is exsit"),
    /**
     * 找不到路书
     */
    ROUTE_IS_NOT_FOUND(ModuleTypeEnum.BIKE_ROUTE, "002", "route is not found"),
    /**
     * 只有路书作者才能修改路书
     */
    ONLY_AUTHOR_CAN_ROAD_ROUTE(ModuleTypeEnum.BIKE_ROUTE, "003", "Only the author can modify the road route"),
    /**
     * 自行车导航路书文件大小超过上限
     */
    COROS_ROUTE_FILE_TOO_LARGE(ModuleTypeEnum.BIKE_ROUTE, "004", "The file size over upper limit"),
    /**
     * 缩率图文件大小超过上限
     */
    IMAGE_DATA_FILE_TOO_LARGE(ModuleTypeEnum.SPORT_DATA, "001", "The file size over upper limit"),
    /**
     * 运动数据文件大小超过上限
     */
    SPORT_DATA_FILE_TOO_LARGE(ModuleTypeEnum.SPORT_DATA, "002", "The file size over upper limit"),
    /**
     * 运动数据重复提交
     */
    SPORT_DATA_REPEAT_SUBMIT(ModuleTypeEnum.SYSTEM, "082", "Sport data repeat submitting"),

    /**
     * （紧急联系人）半小时内只能发送1条短信
     */
    SMS_SEND_ONLY_30_MINUTE(ModuleTypeEnum.SOS, "001", "1 SMS sending only every half an hour"),

    /**
     * （紧急联系人）一天内只能发送10条短信
     */
    MAX_10_SMS_SEND_EVERYDAY(ModuleTypeEnum.SOS, "002", "Max 10 SMS sending everyday"),

    /**
     * 短信发送失败，请检查紧急联系人电话。
     */
    SEND_SMS_FAILED(ModuleTypeEnum.SOS, "003", "Failed to send SMS, please check emergency contact phone No"),

    /**
     * 固件校验码错误
     */
    FIRMWARE_MD5_CODE_ERROR(ModuleTypeEnum.FIRMWARE_WATCHFACE, "001", "固件校验码错误"),
    /**
     * 字库校验码错误
     */
    FONT_MD5_CODE_ERROR(ModuleTypeEnum.FIRMWARE_WATCHFACE, "002", "字库校验码错误"),

    /**
     * 上传固件失败
     */
    UPLOAD_FIRMWARE_FAILURE(ModuleTypeEnum.FIRMWARE_WATCHFACE, "003", "上传固件失败"),

    /**
     * 固件是最新版本,不需要更新
     */
    FIRMWARE_IS_LATSET(ModuleTypeEnum.FIRMWARE_WATCHFACE, "004", "The firmware is the latest version, do not need to be updated"),
    /**
     * 固件已经存在,不需要重复发布
     */
    FIRMWARE_IS_EXIST(ModuleTypeEnum.FIRMWARE_WATCHFACE, "005", "Firmware is exist"),

    /**
     * 固件压缩文件超过上限
     */
    FIRMWARE_FILE_TOO_LARGE(ModuleTypeEnum.FIRMWARE_WATCHFACE, "005", "The firmware file size exceeds limit"),
    /**
     * 问题反馈日志文件超过上限
     */
    FEEDBACK_FILE_TOO_LARGE(ModuleTypeEnum.FEEDBACK, "001", "The feedback file size exceeds limit");



    private String result;
    private String message;
    private ModuleTypeEnum moduleType;

    /**
     * 新版定义的返回编码信息<br>
     *
     * @param moduleType 模块类型枚举<br>
     * @param result     返回编码<br>
     * @param message    返回信息<br>
     */
    private ReturnMessageEnum(ModuleTypeEnum moduleType, String result, String message) {
        this.moduleType = moduleType;
        // 返回编码是模块编码和错误编码组成
        this.result = moduleType.getValue().concat(result);
        this.message = message;
    }


    /**
     * 兼容老版本的系统信息返回<br>
     *
     * @param result  状态码<br>
     * @param message 状态消息<br>
     */
    private ReturnMessageEnum(String result, String message) {
        this.result = result;
        this.message = message;
    }


    /**
     * 获取返回编码<br>
     *
     * @return 返回编码<br>
     */
    public String getResult() {
        return result;
    }

    /**
     * 获取返回信息<br>
     *
     * @return 返回信息<br>
     */
    public String getMessage() {
        return message;
    }

    /**
     * 获取模块枚举对象<br>
     *
     * @return 返回模块枚举对象<br>
     */
    public ModuleTypeEnum getModuleType() {
        return moduleType;
    }

    /**
     * 支持自定义消息体
     * @param message
     * @return
     */
    public ReturnMessageEnum getMessageEnum(String message)
    {
        this.message = message;
        return this;
    }

}
