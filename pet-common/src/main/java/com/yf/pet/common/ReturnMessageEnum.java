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

    /**
     * 参数为空
     */
    PARAMETER_NULL(ModuleTypeEnum.SYSTEM,"004","parameter null"),


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

    /**
     * email格式不对
     */
    EMAIL_ERROR(ModuleTypeEnum.ACCOUNT, "011", "email format is wrong"),

    /**
     * 验证码不对或者过期
     */
    CODE_ERROR(ModuleTypeEnum.ACCOUNT, "012", "Validation code expired or error"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIT(ModuleTypeEnum.ACCOUNT, "013", "user not exit"),

    /**
     * 发送邮件失败
     */
    FAILED_TO_SEND_EMAIL(ModuleTypeEnum.ACCOUNT,"014","fail to send email");



    //-----------------------------------------------------账户模块结束--------------------------------------------//



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
