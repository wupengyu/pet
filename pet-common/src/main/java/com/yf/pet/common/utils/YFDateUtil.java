package com.yf.pet.common.utils;


import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@SuppressWarnings({"rawtypes", "unchecked"})
public class YFDateUtil {

    private static final Logger log = LoggerFactory.getLogger(YFDateUtil.class);

    /**
     * description：获取当前时间的时间戳yyyyMMdd
     *
     * @return
     * @throws Exception
     * @version 1.0
     * @author ok
     */
    public static Integer getDateNumber(Date date) {
        String dataStr = getString(date, YFFormat.YYYYMMDD);
        if (dataStr != null) {
            return Integer.parseInt(dataStr);
        }
        return null;
    }

    /**
     * description：获取当前日期，日期格式是yyyyMMdd
     */
    public static Integer getTodayNumber() {
        return getDateNumber(new Date());
    }


    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 根据日期,获取到当前日期，本周一共有多少天
     *
     * @param date
     * @return
     */
    public static int getCountDaysTodayOfWeeks(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return intWeek;
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekCodeOfDate(Date date) {
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }

    /**
     * 将指定的日期字符串转化为日期对象
     *
     * @return java.util.Date
     */
    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YFFormat.YYYYMMDDHHMMSS);
        return df.format(date);
    }

    /**
     * description：获取当前时间的时间戳
     *
     * @return
     * @throws Exception
     * @version 1.0
     * @author ok
     */
    public static String getNowStamp() throws Exception {
        String a = null;
        Date d1 = new Date();
        Date d2 = parseDate("20120330", YFFormat.YYYYMMDD);
        a = String.valueOf((d1.getTime() - d2.getTime()) / 1000);
        return a;
    }


    /**
     * 根据匹配格式将时间转换为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getString(Date date, String format) {
        if (date == null || format == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat(format);
        String res = f.format(date);
        return res;
    }

    /**
     * 获取几个月后的时间
     *
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date getDatenextMonth(int num) {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(nowCalendar.MONTH, nowCalendar.get(nowCalendar.MONTH) + num);
        return nowCalendar.getTime();
    }

    /**
     * 获取num月后的日期
     *
     * @param date
     * @param num
     * @param flag 1-返回开始日期 2-返回结束日期
     * @return
     */
    public static Date getDateNextMonth(Date date, int num, int flag) {
        Calendar nextDate = Calendar.getInstance();
        nextDate.setTime(date);
        nextDate.set(Calendar.DATE, 1);// 设为当前月的1号
        // 返回结束日期
        if (2 == flag) {
            nextDate.add(Calendar.MONTH, num + 1);// 加减num
            nextDate.add(Calendar.DATE, -1);
        } else {
            // 返回开始日期
            nextDate.add(Calendar.MONTH, num);// 加减num
        }
        return nextDate.getTime();
    }

    /**
     * 获取几天后的时间
     *
     * @return
     */
    public static Date getDatenextday(int num) {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.add(Calendar.DATE, num);
        return nowCalendar.getTime();
    }

    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        if (dateStr.length() < format.length()) {
            format = format.substring(0, dateStr.length());
        }
        try {
            java.text.DateFormat df = new SimpleDateFormat(format);
            date = df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();

            log.error("日期解析错误:" + dateStr);
        }
        return date;
    }



    /**
     * 获取几个月前的月份
     *
     * @return
     */
    public static String getPreviousMonthFirst(int n, String staredate) {
        String str = "";
        try {
            Date sDate = parseDate(staredate, YFFormat.YYYY_MM_DD);
            SimpleDateFormat sdf = new SimpleDateFormat(YFFormat.YYYY_MM_DD);
            Date pDate = getDateNextMonth(sDate, -n, 1);
            str = sdf.format(pDate);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return str;
    }


    /**
     * 功能描述：时间相减得到秒数
     *
     * @param beginDate
     * @param endDate
     * @return long
     */
    public static long getSecondOf2Date(Date beginDate, Date endDate) {
        long second = 0;
        try {
            second = (endDate.getTime() - beginDate.getTime()) / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return second;
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDate
     * @param endDate
     * @return long
     * @author Administrator
     */
    public static int getDaySub(Date beginDate, Date endDate) {
        Long day = 0l;
        try {
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
            // System.out.println("相隔的天数="+day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day.intValue();
    }

    /**
     * 得到某一天的该星期的第一日 00:00:00
     *
     * @param date 当前时间
     * @param firstDayOfWeek 一个星期的第一天为星期几
     * @return
     */
    public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //happenDate格式
    private static final SimpleDateFormat happenDateFormatForDay = new SimpleDateFormat("yyyyMMdd");

    /**
     * 把int值的时间转换为UTC时间<br>
     * 目前用在把数据库中的happenDay:20170810这样的int转成date类型
     *
     * @param happenDate   当前发送天,已经包含用户时区信息
     * @return 发送时间
     */
    public static Date getHappenDateUTCByInt(int happenDate) throws ParseException {
        happenDateFormatForDay.setTimeZone(TimeZone.getTimeZone("UTC"));
        return happenDateFormatForDay.parse(String.valueOf(happenDate));

    }

    /**
     * 得到某一天的该星期的第一日 00:00:00
     *
     * @param happenDate           当前发送天,已经包含用户时区信息
     * @param firstDayOfWeek 一个星期的第一天为星期几
     * @return 本周第一天的int值
     */
    public static Integer getFirstDayOfWeekInt(Date happenDate, int firstDayOfWeek) {
        // 这里不需要指定时区,因为happenDate里面已经包含当前时区信息了
        FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyyMMdd", TimeZone.getTimeZone("UTC"));
        return NumberUtils.toInt(fastDateFormat.format(YFDateUtil.getFirstDayOfWeek(happenDate,
                firstDayOfWeek)));
    }

    /**
     * 得到一个星期的最后一日的时间,默认这个星期的第一天为星期天 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        return getLastDayOfWeek(date, Calendar.MONDAY);
    }

    /**
     * 得到指定日期相关的周的最后一日 00:00:00
     *
     * @param date
     * @param firstDayOfWeek
     * @return
     */
    public static Date getLastDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 根据年月和这个月的第几个星期，返回那一周的开始日期，星期的第一天为星期天
     *
     * @author xuming 2014-05-21
     */

    public static Date getFirstDayOfWeek4Month(Date paraDate, int weekNum) {
        Date returnDate = weekCalendar(paraDate, weekNum, 1);
        return returnDate;
    }

    /**
     * 根据年月和这个月的第几个星期，返回那一周的结束日期，星期的第一天为星期天
     *
     * @author xuming 2014-05-21
     */

    public static Date getLastDayOfWeek4Month(Date paraDate, int weekNum) {
        Date returnDate = weekCalendar(paraDate, weekNum, 2);
        return returnDate;
    }

    /**
     * 根据年月，需要获取的第几周，返回第几周的开始日期或者结束日期
     *
     * @param paraDate
     * @param weekNum
     * @param flag    1-希望返回开始日期 2-希望返回结束日期
     * @return 返回 开始日期或者结束日期
     */

    public static Date weekCalendar(Date paraDate, int weekNum, int flag) {
        Date returnDate = null;
        Calendar c_now = new GregorianCalendar();
        Calendar c_begin = new GregorianCalendar();
        Calendar c_end = new GregorianCalendar();
        Calendar real_end_date = new GregorianCalendar();
        // DateFormatSymbols dfs = new DateFormatSymbols();
        // String[] weeks = dfs.getWeekdays();

        // 设置参数，年，月
        // String stDate = "2014-5-5";
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
        Date cDate = paraDate;
        c_now.setTime(cDate);
        int year = c_now.get(Calendar.YEAR);
        int month = c_now.get(Calendar.MONTH) + 1;
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 4 == 0)
            days[2] = 29;// 大年
        c_begin.set(year, month - 1, 1); // 月 0-11 天 0-
        c_end.set(year, month - 1, days[month]);
        real_end_date.set(year, month - 1, days[month]);

        int count = 1;
        c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
        while (c_begin.before(c_end)) {

            if (weekNum == count) {
                if (flag == 1) {// 找的是开始时间
                    returnDate = new java.sql.Date(c_begin.getTime().getTime());
                    break;
                } else {// 找的是结束时间
                    if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c_begin.compareTo(real_end_date) == 0) {
                        returnDate = new java.sql.Date(c_begin.getTime().getTime());
                        break;
                    }
                }

            }
        /*
         * System.out.println("第" + count + "周  日期：" + new
	     * java.sql.Date(c_begin.getTime().getTime()) + ", " +
	     * weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
	     */
            if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                count++;
            }
            c_begin.add(Calendar.DAY_OF_YEAR, 1);
        }
        // System.out.println("共计（跨越）：" + (count - 1) + "周");

        return returnDate;
    }

    /**
     * 计算传进去的年月，计算这个月一共有多少周
     *
     * @param paraDate
     * @return
     * @throws Exception
     */
    public static int getWeekCountOfMonth(Date paraDate) {
        int returnWeeks = 0;
        Calendar c_now = new GregorianCalendar();
        Calendar c_begin = new GregorianCalendar();
        Calendar c_end = new GregorianCalendar();
        // DateFormatSymbols dfs = new DateFormatSymbols();

        Date cDate = paraDate;
        c_now.setTime(cDate);
        int year = c_now.get(Calendar.YEAR);
        int month = c_now.get(Calendar.MONTH) + 1;
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 4 == 0)
            days[2] = 29;// 大年
        c_begin.set(year, month - 1, 1); // 月 0-11 天 0-
        c_end.set(year, month - 1, days[month]);

        int count = 1;
        c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
        while (c_begin.before(c_end)) {
            if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                count++;
            }
            c_begin.add(Calendar.DAY_OF_YEAR, 1);
        }
        // System.out.println("共计（跨越）：" + (count - 1) + "周");
        returnWeeks = count - 1;

        return returnWeeks;
    }

    /**
     * 添加天
     *
     * @param v
     * @param day
     * @return date
     */
    public static Date addDays(Date v, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(v);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 获取上周的最后一天日期
     *
     * @param firstDayOfWeek
     * @return
     */
    public static Date getLastDayOfLastWeek(int firstDayOfWeek) {
        Date date = getFirstDayOfWeek(new Date(), firstDayOfWeek);
        date = addDays(date, -1);// 本周第一天的上一天就是上周的最后一天
        return date;
    }

    /**
     * 获取上周的第一天的日期
     *
     * @param firstDayOfWeek
     * @return
     */
    public static Date getFirstDayOfLastWeek(int firstDayOfWeek) {
        Date date = getLastDayOfLastWeek(firstDayOfWeek);
        date = getFirstDayOfWeek(date, firstDayOfWeek);
        return date;
    }

    public static Date getMinDate(Date date1, Date date2) {
        Date returnDate = null;
        if (date1.before(date2)) {
            returnDate = date1;
        } else {
            returnDate = date2;
        }
        return returnDate;
    }

    public static Date getMaxDate(Date date1, Date date2) {
        Date returnDate = null;
        if (date1.before(date2)) {
            returnDate = date2;
        } else {
            returnDate = date1;
        }
        return returnDate;
    }

    // 判断输入的日期是否合法
    public static boolean isDateFormat(String str) {
        SimpleDateFormat format = new SimpleDateFormat(YFFormat.YYYY_MM_DD);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // 获取年份
    public static Date getYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(YFFormat.YYYY);

        String strDate = format.format(date);
        Date year = null;
        try {
            year = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year;
    }

    // 格式化日期
    public static Date formatDate(Date date, String sFormat) {
        SimpleDateFormat format = new SimpleDateFormat(sFormat);
        String strDate = format.format(date);
        Date formateDate = null;
        try {
            formateDate = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formateDate;
    }




    /**
     * 获取发送时间
     *
     * @param timestampInSecond 时间戳
     * @param timezoneInt       时区
     * @return 发送时间
     */
    public static Date getHappenDate(Long timestampInSecond, Integer timezoneInt) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Date happenDate = new Date(timestampInSecond * 1000);
        // 加上时间的偏移量
        return DateUtils.addMinutes(happenDate, 15 * timezoneInt);
    }

    /**
     * 使用UTC时间转换(0时区)
     * 获得happenDay int类型的数据,
     * 时间戳转换为utc时间,获得用户当地的时间
     * @author infi
     *
     * @param timestampInSecond 秒时间戳
     * @param timezoneInt 时区
     * @return happenDay
     */
    public static int getHappenDay(Long timestampInSecond, Integer timezoneInt) {
        Date happenDate = getHappenDate(timestampInSecond, timezoneInt);
        FastDateFormat happenDayFormat = FastDateFormat.getInstance("yyyyMMdd", TimeZone.getTimeZone("UTC"));
        return NumberUtils.toInt(happenDayFormat.format(happenDate));
    }

    /**
     * 使用UTC时间转换(0时区)
     * 获得happenDay int类型的数据,
     * 时间戳转换为utc时间,获得用户当地的时间
     * @author infi
     *
     * @param happenDateUTC  发送时间UTC时间
     * @return happenDay
     */
    public static int getHappenDay(Date happenDateUTC) {
        FastDateFormat happenDayFormat = FastDateFormat.getInstance("yyyyMMdd", TimeZone.getTimeZone("UTC"));
        return NumberUtils.toInt(happenDayFormat.format(happenDateUTC));
    }



    /**
     * 获得happenMonth int类型的数据,
     * 时间戳转换为utc时间,获得用户当地的时间
     * @author infi
     *
     * @param timestampInSecond 秒时间戳
     * @param timezoneInt 时区
     * @return happenDay
     */
    public static int getHappenMonth(Long timestampInSecond, Integer timezoneInt) {
        Date happenDate = getHappenDate(timestampInSecond, timezoneInt);
        FastDateFormat happenMonthFormat = FastDateFormat.getInstance("yyyyMM", TimeZone.getTimeZone("UTC"));
        return NumberUtils.toInt(happenMonthFormat.format(happenDate));
    }

    /**
     * 获得happenMonth int类型的数据,
     * 时间戳转换为utc时间,获得用户当地的时间
     *
     * @param happenDateUTC 发送时间的utc时间
     * @return happenMonth
     * @author infi
     */
    public static int getHappenMonth(Date happenDateUTC) {
        // 1. 在做时间转换
        FastDateFormat happenMonthFormat = FastDateFormat.getInstance("yyyyMM", TimeZone.getTimeZone("UTC"));
        return NumberUtils.toInt(happenMonthFormat.format(happenDateUTC));
    }

    /**
     * 获得happenYear int类型的数据,
     * 时间戳转换为utc时间,获得用户当地的时间
     *
     * @param happenDateUTC 时间
     * @return happenDay
     * @author infi
     */
    public static int getHappenYear(Date happenDateUTC) {
        // 1. 在做时间转换
        FastDateFormat happenYearFormat = FastDateFormat.getInstance("yyyy", TimeZone.getTimeZone("UTC"));
        return NumberUtils.toInt(happenYearFormat.format(happenDateUTC));
    }

    /**
     * 时间戳对应0时区的时间
     *
     * @param timestampInSecond 秒时间戳
     * @return
     */
    public static Date getUTCByTimestapInSecond(Long timestampInSecond) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return new Date(timestampInSecond * 1000);
    }

    /**
     * 获得指定时间在当天的第几个刻度,比如2017-01-01 00:12:00 是当天第一个15分钟刻度
     * 区间算法,前闭后开,如:[0,15),[15,30)...
     *
     * @param timestampInSecond   时间戳
     * @param timezoneIn15Minutes 时区
     * @param segmentInMinutes    统计时间间隔(分钟)
     * @return 当天刻度
     */
    public static int getTimeIndexInDaySegment(long timestampInSecond, int timezoneIn15Minutes, int segmentInMinutes) {
        // 1. 分钟数据当天时间戳
        Date happenDate = getHappenDate(timestampInSecond, timezoneIn15Minutes);
        Date happenDay = DateUtils.truncate(happenDate, Calendar.DAY_OF_MONTH);
        // 2. 计算该分钟在当天的第几个15分钟内,算法时间戳除以15分钟的秒数取整
        int index = (int) ((happenDate.getTime() / 1000 - happenDay.getTime() / 1000) / segmentInMinutes);
        // 当天一共86400秒,计算最大分段数量
        int count = 86400 / segmentInMinutes;
        // 3. 如果索引小余0 ,就返回0
        if (index < 0) {
            return 0;
        } else if (index > count) {
            // 4. 如果索引大于当天总刻度数量,就返回最大索引
            return count - 1;
        } else {
            return index;
        }
    }


    //计算花费多少毫秒
    public static long spendTime(long startTime) {
        return new Date().getTime() - startTime;
    }

    /**
     * 获得UTC时间<br>
     * infi<br>
     *
     * @return UTC时间
     */
    public static Date getUTCDate() {
        Calendar calendar = Calendar.getInstance();
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return calendar.getTime();
    }

}
