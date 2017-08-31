package com.yf.pet.entity.enums;

/**
 * 日常数据类型<br>
 * ADD 需要新增的数据<br>
 * MODIFY 需要修改的数据<br>
 * NO_CHANGE 不需要修改不需要新增的数据<br>
 * <p>
 * Created by Infi on 17/2/19.
 */
public interface DailyDataStatusEnum {
    /**
     * 需要新增的数据
     */
    public static final int ADD = 1;
    /**
     * 需要修改的数据
     */
    public static final int MODIFY = 2;
    /**
     * 不需要修改不需要新增的数据
     */
    public static final int NO_CHANGE = 3;
}
