package com.yf.pet.common.utils;

import com.yf.pet.common.ApplicationConstants;

import java.util.HashMap;
import java.util.Map;

public class YFFormat {

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";

    public static String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYYMM = "yyyyMM";

    public static String YYYY = "yyyy";

    public static String MM = "MM";

    public static String HH_MM = "HH:mm";

    public static String H24 = "HH";

    public static String NEW_LINE = System.getProperty("line.separator");

    static Map<Short, String> MAP_LABEL_MODE = null;

    public static String formatLabelMode(Short mode) {
        //1:摘下,2:睡眠,3:步行,4:跑步,5:低运动量,6:运动,7:快走,8:跑步训练,9:骑行,10:游泳
        if (YFTools.isEmpty(MAP_LABEL_MODE)) {
            MAP_LABEL_MODE = new HashMap<Short, String>();
            MAP_LABEL_MODE.put(ApplicationConstants.takeOff, "[1:摘下]");
            MAP_LABEL_MODE.put(ApplicationConstants.sleep, "[2:睡眠]");
            MAP_LABEL_MODE.put(ApplicationConstants.walk, "[3:步行]");
            MAP_LABEL_MODE.put(ApplicationConstants.run, "[4:跑步]");
            MAP_LABEL_MODE.put(ApplicationConstants.lightExercise, "[5:低运动量]");
            MAP_LABEL_MODE.put(ApplicationConstants.exercise, "[6:运动]");
            MAP_LABEL_MODE.put(ApplicationConstants.fastWalk, "[7:快走]");
            MAP_LABEL_MODE.put(ApplicationConstants.runTraining, "[8:跑步训练]");
            MAP_LABEL_MODE.put(ApplicationConstants.riding, "[9:骑行]");
            MAP_LABEL_MODE.put(ApplicationConstants.swimming, "[10:游泳]");
        }
        return MAP_LABEL_MODE.get(mode);
    }


}
