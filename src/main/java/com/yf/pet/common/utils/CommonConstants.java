package com.yf.pet.common.utils;

/**
 * 常量接口
 *
 * @author pichunhan
 */

public interface CommonConstants {

    // 配置文件地址
    public final static String CONFIG_PATH = "app-coros.properties";

    public final static String WEATHER_COUNTY_PATH = "config/china.xml";

    public final static String WEATHER_PATH = "config/weather.xml";

    public final static String THINK_WEATHER_PATH = "config/think_weather.xml";

    // 微信APPID
    public final static String WEI_CHAT_APPID = "appid";

    // 微信secret
    public final static String WEI_CHAT_SECRET = "secret";

    // WEB_HTTP_URL
    public final static String WEB_HTTP_URL = "WEB_HTTP_URL";

    // 合并标签模式1:累加;2:覆盖 ;针对分钟数据
    public final static String MERGE_ADD = "MERGE_ADD";

    // 百度缩微图API地址
    public static final String BAIDU_MAP_URL = "BAIDU_MAP_URL";

    // 静态资源目录
    public final static String UPLOAD_FILE_PATH = "UPLOAD_FILE_PATH";

    // 天分钟数据存放目录
    public final static String MINUTE_FILE_PATH = "MINUTE_FILE_PATH";

    // FILE_HTTP_URL
    public final static String FILE_HTTP_URL = "FILE_HTTP_URL";

    // BONG_SERVER
    public final static String BONG_SERVER = "BONG_SERVER";

    // BONG_ACCESSTOKEN
    public final static String BONG_ACCESSTOKEN = "BONG_ACCESSTOKEN";

    // BONG_SECRETKEY
    public final static String BONG_SECRETKEY = "BONG_SECRETKEY";

    // 激活
    public final static byte IS_TRUE = 1;

    // 未激活
    public final static byte IS_FASLE = 0;

    // 微信token过期
    public final static String ERRCODE = "ERRCODE";

    // k780数据接口密钥
    public static final String K780_SIGN = "K780_SIGH";
    // K780数据接口APPKEY
    public static final String K780_APP_KEY = "K780_APP_KEY";

    // 心知天气密钥
    public static final String THINKPAGE_KEY = "THINKPAGE_KEY";

    // 验证码短信后缀
    public static final String MESSAGE_SUFFIX = "MESSAGE_SUFFIX";

    /**
     * 睡眠
     */
    static public final int TYPE_SLEEP = 1;
    /**
     * bong
     */
    static public final int TYPE_BONG = 2;
    /**
     * 非bong
     */
    static public final int TYPE_NON_BONG = 3;
    /**
     * 摘下
     */
    static public final int TYPE_TAKE_OFF = 4;
    /**
     * 充电
     */
    static public final int TYPE_CHARGING = 5;
    /**
     * 热身运动
     */
    static public final int SUB_TYPE_WARM_UP = 1;
    /**
     * 健走
     */
    static public final int SUB_TYPE_SPORT_WALKING = 2;
    /**
     * 运动
     */
    static public final int SUB_TYPE_SPORT = 3;
    /**
     * 跑步
     */
    static public final int SUB_TYPE_RUNNING = 4;
    /**
     * 静坐
     */
    public static final int SUB_NO_BONG_SIT_DOWN = 1;
    /**
     * 散步
     */
    public static final int SUB_NO_BONG_WALK = 2;
    /**
     * 静态，自己合并的标签状态 type==3 && subType == 0
     */
    public static final int SUB_NO_BONG_TYPE_STATIC = 0;
    /**
     * 活动 type == 3 && subType == 4
     */
    public static final int SUB_NO_BONG_TYPE_ACTIVITY = 4;
    public static final int SUB_NO_BONG_TYPE_RUNNING = 11;
    public static final int SUB_NO_BONG_TYPE_DRIVING = 10;

    /**
     * Sport mode:<br/>
     * takeOff = 1;//摘下<br/>
     * sleep = 2;//睡眠<br/>
     * walk = 3;//步行<br/>
     * run = 4;//跑步<br/>
     * light_exercise = 5;//低运动量<br/>
     * exercise = 6;//运动<br/>
     * fastWalk = 7;//快走<br/>
     * runTraining = 8;//跑步训练<br/>
     * riding = 9;//骑行<br/>
     * swimming = 10;//游泳<br/>
     */
    /**
     * 摘下
     */
    public final static short takeOff = 1;

    /**
     * 睡眠
     */
    public final static short sleep = 2;

    /**
     * 步行
     */
    public final static short walk = 3;

    /**
     * 跑步
     */
    public final static short run = 4;

    /**
     * 低运动量
     */
    public final static short lightExercise = 5;

    /**
     * 运动
     */
    public final static short exercise = 6;

    /**
     * 快走
     */
    public final static short fastWalk = 7;

    /**
     * 跑步训练
     */
    public final static short runTraining = 8;

    /**
     * 骑行
     */
    public final static short riding = 9;

    /**
     * 游泳
     */
    public final static short swimming = 10;

    /**
     * 清醒
     */
    public final static byte AWAKE = 0;
    /**
     * 浅睡
     */
    public final static byte LIGHT = 1;
    /**
     * 深睡
     */
    public final static byte DEEP = 2;

    // 默认目标值10000
    public final Integer DEFAULT_TARGET = 1000;

    // 手机号登录
    public final String LOGIN_TYPE_NORMAL = "1";
    // 微信登录
    public final String LOGIN_TYPE_WX = "2";
    // QQ登录
    public final String LOGIN_TYPE_QQ = "3";
    //facebook 
    public final String LOGIN_TYPE_FB = "4";

    // 表盘的类别，00-图形表盘,01-数字表盘，02-指针表盘
    public static String WATCH_CLASS_IMAGE = "00";
    public static String WATCH_CLASS_DIGITAL = "01";
    public static String WATCH_CLASS_POINTER = "02";

    // JSON对象的参数名
    public static String JSON_PARAMETER_DAILY_TARGET = "dailyTarget";
    public static String JSON_PARAMETER_DAILY_GAIN = "dailyGain";
    public static String JSON_PARAMETER_INIT_DATA = "activityInitData";
    public static String JSON_PARAMETER_SLEEP = "sleep";
    public static String JSON_PARAMETER_ACTIVITY = "activity";
    public static String JSON_PARAMETER_SLEEP_INIT_DATA = "sleepInitData";
    public static String JSON_PARAMETER_LOCUS_INFO = "locusInfo";

    public static String JSON_PARAMETER_MID = "mid";

    public static String JSON_PARAMETER_TARGET_TYPE = "targetType";
    public static String JSON_PARAMETER_TARGET_VALUE = "targetValue";
    public static String JSON_PARAMETER_HAPPEN_DATE = "happenDate";
    public static String JSON_PARAMETER_BEGIN_TIME = "beginTime";
    public static String JSON_PARAMETER_END_TIME = "endTime";

    public static String JSON_PARAMETER_TOTAL_TIME = "totalTime";
    public static String JSON_PARAMETER_STEP_COUNT = "stepCount";
    public static String JSON_PARAMETER_LEVEL = "level";
    public static String JSON_PARAMETER_RUN_TYPE = "runType";
    public static String JSON_PARAMETER_KM_TIME = "kmTime";
    public static String JSON_PARAMETER_DISTANCE = "distance";
    public static String JSON_PARAMETER_FOOD_TYPE = "foodType";

    public static String JSON_PARAMETER_GAIN_VALUE = "gainValue";
    public static String JSON_PARAMETER_TARGET_POINT = "targetPoint";
    public static String JSON_PARAMETER_LONGITUDE = "longitude";
    public static String JSON_PARAMETER_LATITUDE = "latitude";
    public static String JSON_PARAMETER_ALTITUDE = "altitude";
    public static String JSON_PARAMETER_ANGLE = "angle";
    public static String JSON_PARAMETER_SPEED = "speed";
    public static String JSON_PARAMETER_LOCUS_TYPE = "locusType";
    public static String JSON_PARAMETER_CONTENT = "content";

    public static String JSON_PARAMETER_INIT_DATA_TYPE = "initDataType";
    public static String JSON_PARAMETER_DEEP_SLEEP_TIME = "deepSleepTime";
    public static String JSON_PARAMETER_SHALLOW_SLEEP_TIME = "shallowSleepTime";
    public static String JSON_PARAMETER_CLEAR_TIME = "clearTime";
    public static String JSON_PARAMETER_CALORIE = "calorie";

    public static String JSON_PARAMETER_RESULT = "result";
    public static String JSON_PARAMETER_MESSAGE = "message";
    public static String JSON_PARAMETER_SLEEP_TYPE = "sleepType";

    public static String JSON_PARAMETER_POINT_COUNT = "pointCount";
    public static String JSON_PARAMETER_ACTIVITY_TYPE = "activityType";
}
