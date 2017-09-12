/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.utils.regex;

import org.apache.commons.lang.ArrayUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式字符串提取帮助类
 *
 * @author Infi
 */
public class YFRegextUtils {

    /**
     * 包含日期的字段,找出第一个日期返回
     *
     * @param dateStr 日期字符串
     * @return 返回日期
     */
    public static String findDate(String dateStr) {
        // 日期正则表达式
        String re0 = "\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}";
        String re1 = "\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}";
        Pattern p = Pattern.compile(re0 + "|" + re1, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        String date = null;
        Matcher m = p.matcher(dateStr);
        if (m.find()) {
            date = m.group(0);
        }
        return date;
    }

    /**
     * 包含电话号码的字段，找出第一个电话号码返回
     *
     * @param mobileStr 电话号码字符串
     * @return 返回电话号码
     */
    public static String findMobile(String mobileStr) {
        // 电话号码正则表达式
        String re0 = "\\d{7,8}";
        String re1 = "\\d{3,4}\\-\\d{7,8}";
        String re2 = "\\d{3}\\-?\\d{4}\\-?\\d{4}";
        Pattern p = Pattern.compile(re0 + "|" + re1 + "|" + re2, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(mobileStr);
        String mobile = null;
        if (m.find()) {
            mobile = m.group(0);
        }
        return mobile;
    }

    /**
     * 字符串中包含中文、字母、数字的字符，并返回
     *
     * @return 字符串
     */
    public static String findChineseAndLetterAndNumber(String resultStr) {
        String re4 = "[\u4e00-\u9fa5_a-zA-Z0-9]\\(?\\)?\\（?\\）?\\.?\\-{0,}";
        Pattern p = Pattern.compile(re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = p.matcher(resultStr);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group());
        }
        return result.toString();
    }

    /**
     * 字符串中包含中文的字符，并返回
     *
     * @return 字符串
     */
    public static String findChinese(String resultStr) {
        String re4 = "[\u4e00-\u9fa5]\\(?\\)?\\（?\\）?\\[?\\]?\\-{0,}";
        Pattern p = Pattern.compile(re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = p.matcher(resultStr);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group());
        }
        return result.toString();
    }

    /**
     * 字符串中包含数字的字符，并返回数字数组
     *
     * @return 字符串
     */
    public static String[] findNumber(String resultStr) {
        String re4 = "\\d{1,}\\.?\\d{0,}";
        Pattern p = Pattern.compile(re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = p.matcher(resultStr);
        String[] numbers = {};
        while (matcher.find()) {
            numbers = (String[]) ArrayUtils.add(numbers, matcher.group());
        }
        return numbers;
    }

    /**
     * 检查字符串是电话号码字符串，含数字、横杠、空格、括号
     *
     * @return 判断结果
     */
    public static boolean checkPhoneNumber(String resultStr) {
        String re4 = "^[0-9-\\s()]+$";
        Pattern p = Pattern.compile(re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = p.matcher(resultStr);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串中是否不包含字母<br>
     * 如果包含字母就返回false,不包含字母就返回true
     *
     * @param resultStr 字符串
     * @return 判断结果
     */
    public static boolean checkIsNotContainLetters(String resultStr) {
        String re1 = "[^a-z]+$";
        Pattern p = Pattern.compile(re1, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = p.matcher(resultStr);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmail(String email) {
        //正则表达式
        String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(regex);
    }

}
