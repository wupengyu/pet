package com.yf.pet.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public final static String CONFIG_PATH = "resource.properties";

   public final static Integer EMAIL_CODE = 6;

    public final static int TOKEN_LENGTH = 32;
    public final static int TOKEN_VALID_DAY_COUNT = 30;

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

}
