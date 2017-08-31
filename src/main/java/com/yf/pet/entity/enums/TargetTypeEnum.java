package com.yf.pet.entity.enums;

/**
 * 目标值类型枚举类<br>
 * 枚举说明 枚举类只读<br>
 * MOTION_TIME 表示目标运动时间,<br>
 * CALORIE 表示目标卡路里,<br>
 * Created by Infi on 17/2/19.
 */
public interface TargetTypeEnum {
    /**
     * 运动时间目标值
     */
    public static final int MOTION_TIME = 1;
    /**
     * 卡路里目标值
     */
    public static final int CALORIE = 2;
}
