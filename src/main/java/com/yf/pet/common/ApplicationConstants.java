package com.yf.pet.common;

import com.yf.pet.common.utils.*;
import com.yf.pet.common.utils.aes.AESUtil;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 整个应用的常量类
 *
 * @author xuming
 */
public class ApplicationConstants {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConstants.class);

    public static final String CHAR_ENCODE = "UTF-8";
    public static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    public static final String ACCESS_TOKEN = "accessToken";
    // WEB_HTTP_URL
    public final static String WEB_HTTP_URL = "WEB_HTTP_URL";
    // 短信相关
    public static int SMS_VERIFY_CODE_LEN = 4;// 手机验证码的长度
    public static int SMS_VERIFY_CODE_VALID = 30 * 60;// 验证码有效时间，单位秒(30分钟)
    /**
     * 重置密码过期时间48小时,172800,激活邮箱邮件过期时间,48小时
     */
    public static int SMS_VERIFY_CODE_VALID_BY_RESET_PWD = 172800;

    public static int SMS_VERIFY_CODE_COUNT_OF_DAY = 10;// 一个手机号码，每天能申请的验证码，防止恶意申请验证码
    public final static int TOKEN_LENGTH = 32;
    public final static int TOKEN_VALID_DAY_COUNT = 30;

    public final static String CLIENT_TYPE_ANDRIND = "1";
    public final static String CLIENT_TYPE_IOS = "2";

    public final static String DEFAULT_PASSWORD = "123456";

    public final static int FORGET_PWD_CODE_VALID_DAY_COUNT = 3;

    public final static int BUFFER_SIZE = 1024;

    // 发布类型 1-正式发布， 2-公测，3-内测
    public static String WATCH_RELEASE_TYPR_ISSUE = "1";
    public static String WATCH_RELEASE_TYPR_BETA = "2";
    public static String WATCH_RELEASE_TYPR_TEST = "3";
    public final static String SERVER_STATE = "1"; // 服务器状态 1： 正常 2 ：维护

    public final static int PAGE_SIZE = 10;
    //每个页面设置最多显示多少个可以翻页的页签
    public final static int PAGE_MENU_SIZE = 10;

    /**
     * 访问次数上限，包含尝试重置密码和验证验证码合法性的总和
     * 验证码错误次数达到10次以上就限制当天继续访问
     */
    public final static int UP_ACCESS_COUNT = 10;

    // 激活
    public final static byte IS_TRUE = 1;

    // 未激活
    public final static byte IS_FASLE = 0;

    public static Map<String, String> operateMap() {
        Map<String, String> mp = new HashMap<String, String>();
        mp.put("0", "用户注册");
        mp.put("1", "登录");
        mp.put("2", "第三方登录");
        mp.put("3", "退出");
        mp.put("4", "设置账号信息");
        mp.put("5", "登记个人资料");
        mp.put("6", "获取本人详细信息");
        mp.put("7", "修改个人资料");
        mp.put("8", "修改个人头像");
        mp.put("9", "隐私设置");
        mp.put("10", "上传经纬度与推送相关信息");
        mp.put("11", "登录退出");
        mp.put("12", "修改密码");
        mp.put("13", "忘记密码");
        mp.put("14", "重置密码(Email注册用户)");
        mp.put("15", "重置密码(手机注册用户)");
        mp.put("16", "个人首页");
        mp.put("17", "获取用户信息");
        mp.put("18", "设置运动目标");
        mp.put("19", "空气质量信息");
        mp.put("20", "通知消息");
        mp.put("21", "通讯录匹配注册用户");
        mp.put("22", "搜索用户");
        mp.put("23", "添加好友");
        mp.put("24", "接受好友");
        mp.put("25", "我的好友排行榜(排名)");
        mp.put("26", "我的好友列表");
        mp.put("27", "删除好友");
        mp.put("28", "好友请求列表");
        mp.put("29", "是否有好友请求");
        mp.put("30", "清空好友请求标识");
        mp.put("31", "获取表盘信息");
        mp.put("32", "获取表盘信息列表");
        mp.put("33", "表盘下载成功通知");
        mp.put("34", "提交数据同步");
        mp.put("35", "获取数据同步");
        mp.put("36", "判断token是否失效");
        mp.put("37", "客户端版本检查");
        mp.put("38", "获取最新固件信息");
        mp.put("39", "手环激活");
        mp.put("40", "收藏表盘");
        mp.put("41", "设置闹钟");
        mp.put("42", "设置勿扰信息");
        mp.put("43", "设置事件提醒");
        mp.put("44", "设置开关");
        mp.put("45", "设置消息过滤");
        mp.put("46", "获取账号设置信息");

        mp.put("60", "管理后台登陆");
        mp.put("61", "管理后台退出");
        mp.put("62", "添加后台用户");
        mp.put("63", "删除后台用户");
        mp.put("64", "删除表盘");
        mp.put("65", "上传表盘");
        mp.put("66", "清除注册用户信息");
        mp.put("67", "绑定或者解绑用户");
        mp.put("68", "客服人员登录");
        return mp;
    }


    // 获取头像显示目录
    public static String getHeadPicShowUrl(String fileName) {
        // 对图片做压缩，长宽限定在120
        return YFEnumFileKey.HEAD_PIC.getHttpPath() + fileName + AliyunOssUtils.IMG_FORMAT;
        // return FILE_HTTP_URL + HEAD_IMG_FILE_PATH + "/";
    }

}
