/**
 * @author administrator
 * @date 2011-12-15
 * @package com.lingdian.tools.data
 * @class DataType.java
 */
package com.yf.pet.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 数据对象操作类,封闭对类型的判断,数据类型的转换.
 * 对转换类型按规则执行,转换失败取默认转换值.
 *
 * @author administrator
 * @version V100R100C01
 * @package com.lingdian.tools.data
 * @class DataType.java
 */
public class YFDataType {
    /**
     * 默认私有构造函数,不允许创建此类的实例.
     */
    private YFDataType() {
    } // End DataType

    /**
     * 判断对象类型是否为字节类型
     *
     * @param value 数据对象
     * @return boolean true:字节对象;false:非字节对象.
     */
    public static boolean isByte(Object value) {
        return value instanceof Byte;
    } // End isByte

    /**
     * 判断对象类型是否为短整型
     *
     * @param value 数据对象
     * @return boolean true:短整型对象; false:非短整型对象;
     */
    public static boolean isShort(Object value) {
        return value instanceof Short;
    } // End isShort

    /**
     * 判断对象类型是否为整型
     *
     * @param value 数据对象
     * @return boolean true:整型对象; false:非整型对象;
     */
    public static boolean isInteger(Object value) {
        return value instanceof Integer;
    } // End isInteger

    /**
     * 判断对象类型是否为长整型
     *
     * @param value 数据对象
     * @return boolean true:长整型对象; false:非长整型对象;
     */
    public static boolean isLong(Object value) {
        return value instanceof Long;
    } // End isLong

    /**
     * 判断对象类型是否为双精度浮点型
     *
     * @param value 数据对象
     * @return boolean true:双精度浮点型对象; false:非双精度浮点型对象;
     */
    public static boolean isDouble(Object value) {
        return value instanceof Double;
    } // End isDouble

    /**
     * 判断对象类型是否为单精度浮点型
     *
     * @param value 数据对象
     * @return boolean true:单精度浮点型对象; false:非单精度浮点型对象;
     */
    public static boolean isFloat(Object value) {
        return value instanceof Float;
    } // End isFloat

    /**
     * 判断数据类型是否为字符类型
     *
     * @param value 数据对象
     * @return boolean true:字符对象; false:非字符对象;
     */
    public static boolean isChar(Object value) {
        return value instanceof Character;
    } // End isChar

    /**
     * 判断数据类型是否为字符串类型
     *
     * @param value 数据对象
     * @return boolean true:字符串对象; false:非字符串对象;
     */
    public static boolean isString(Object value) {
        return value instanceof String || value instanceof StringBuffer || value instanceof StringBuilder;
    } // End isString

    /**
     * 判断数据类型是否为空
     *
     * @param value 数据对象
     * @return boolean true:空对象;false:非空对象;
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object value) {
        boolean result = false;
        // 对象为null时,返回true
        if (null == value) {
            result = true;
        }

        // 字符串长度为0时,返回true,否则返回false
        else if (isString(value) || isChar(value)) {
            result = (value + "").trim().length() == 0;
        }

        // 集合，则判断长度是否为0
        else if (value instanceof Collection) {
            result = ((Collection) value).size() == 0;
        }

        // 集合，则判断长度是否为0
        else if (value instanceof Map) {
            result = ((Map) value).size() == 0;
        }

        //文件，判断文件是否存在
        else if (value instanceof File) {
            result = !((File) value).exists();
        }

        // 需要时再增加判断
        return result;
    } // End isEmpty

    /**
     * 判断对象类型是否为数字类型
     *
     * @param value 数据对象
     * @return boolean true:数字型对象; false:非数字型对象;
     */
    public static boolean isNumeric(Object value) {
        boolean result = isInteger(value) || isShort(value) || isLong(value) || isFloat(value);
        result = result || isDouble(value) || isByte(value) || isChar(value);
        return result;
    } // End isNumeric

    /**
     * 判断对象类型是否为布尔类型
     *
     * @param value 数据对象
     * @return boolean true:布尔类型对象; false:非布尔类型对象;
     */
    public static boolean isBoolean(Object value) {
        return value instanceof Boolean;
    } // End isBoolean

    /**
     * 判断对象类型是否为时间对象(Date or Calendar)
     *
     * @param value 数据对象
     * @return boolean true:时间对象; false:非时间对象;
     */
    public static boolean isDateTime(Object value) {
        return value instanceof Date || value instanceof Calendar;
    } // End isDateTime

    /**
     * 根据提供的数据对象,获取其双精度值
     *
     * @param value 数据对象
     * @return Double 双精度值
     */
    public static double getDouble(Object value) {
        return getDouble0(value);
    } // End getDouble

    /**
     * 根据给出的数据对象,获取其双精度值
     *
     * @param value 数据对象
     * @return Double 双精度值
     */
    private static Double getDouble0(Object value) {
        double result;

        // 为空时,返回0.0
        if (null == value) {
            result = 0.0d;

            // 布尔值,返回1 或 0
        } else if (isBoolean(value)) {
            result = ((Boolean) value ? 1 : 0) + 0d;

            // 字符型,返回ASCII码
        } else if (isChar(value)) {
            result = ((Character) value) + 0d;

            // 数值类型,直接返回取值
        } else if (isNumeric(value)) {
            result = Double.parseDouble(value.toString());

            // 其它类型,强转.错误返回0.0
        } else if (isDateTime(value)) {
            result = ((value instanceof Date) ? ((Date) value).getTime() : ((Calendar) value).getTimeInMillis()) + 0d;
        } else {
            result = 0.0d;
        }

        return result;
    } // End getDouble0

    /**
     * 根据提供的数据对象,获取其整型值
     *
     * @param value 数据对象
     * @return int 整型结果
     */
    public static int getInteger(Object value) {
        return getDouble0(value).intValue();
    } // End getInteger

    /**
     * 根据提供的数据对象,获取其短整型值
     *
     * @param value 数据对象
     * @return short 短整型结果
     */
    public static short getShort(Object value) {
        return getDouble0(value).shortValue();
    } // End getShort

    /**
     * 根据提供的数据对象,获取其长整型值
     *
     * @param value 数据对象
     * @return short 长整型结果
     */
    public static long getLong(Object value) {
        return getDouble0(value).longValue();
    } // End getLong

    /**
     * 根据提供的数据对象,获取其单精度值
     *
     * @param value 数据对象
     * @return short 单精度结果
     */
    public static float getFloat(Object value) {
        return getDouble0(value).floatValue();
    } // End getFloat

    /**
     * 根据提供的数据对象,获取其字节类型
     *
     * @param value 数据对象
     * @return short 字节结果
     */
    public static byte getByte(Object value) {
        return getDouble0(value).byteValue();
    } // End getByte

    /**
     * 根据提供的数据对象,获取其布尔类型
     *
     * @param value 数据对象
     * @return 获取其布尔值
     */
    public static boolean getBoolean(Object value) {
        // 数据对象为null, 返回false;
        if (isEmpty(value)) {
            return false;
        }

        // 数据为布尔对象, 返回其自身值
        if (isBoolean(value)) {
            return ((Boolean) value).booleanValue();
        }

        // 字符Y和1.字符串的Y,1,true都转为true.其余都为false;
        if (isChar(value) || isString(value)) {
            String bln = ((String) value).toUpperCase();
            return bln.equals("Y") || bln.equals("TRUE") || bln.equals("1");
        }

        // 数据类型, 大于0返回true; 否则返回false;
        if (isNumeric(value)) {
            return getDouble0(value) > 0;
        }

        // 其它类型.返回false;
        return false;
    } // End getBoolean

} // End class DataType
