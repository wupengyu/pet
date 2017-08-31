package com.yf.pet.service.impl;

import com.yf.pet.dao.dailytarget.DailyTargetDao;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.dailytarget.DailyTarget;
import com.yf.pet.entity.enums.TargetTypeEnum;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.DailyTargetService;
import com.yf.pet.utils.YFDateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用户目标值记录接口实现层
 * Created by Infi on 17/3/23.
 */
@Service("dailyTargetService")
public class DailyTargetServiceImpl implements DailyTargetService {
    @Autowired
    private DailyTargetDao dailyTargetDao;
    @Override
    public void addDailyTarget(Long userId, Integer userTimezone, Integer targetMotionTime, Integer targetCalorie) {
        // 时区为空就抛出异常
        if (userTimezone == null) {
            throw new YFException(ReturnMessageEnum.TIMEZONE_IS_NULL);
        }
        // 2. 获得当天时间happenDate
        Date nowDate = DateUtils.addMinutes(YFDateUtil.getUTCDate(), 15 * userTimezone);
        Integer happenDate = YFDateUtil.getHappenDay(System.currentTimeMillis() / 1000, userTimezone);
        // 3. 本次上传的目标值大于0;当天没有目标值设置
        List<DailyTarget> addDailyTargets = new ArrayList<DailyTarget>();
        // 4. 目标运动时间
        if (targetMotionTime != null && targetMotionTime > 0) {
            DailyTarget dailyTarget = new DailyTarget();
            dailyTarget.setUserId(userId);
            dailyTarget.setTargetType(TargetTypeEnum.MOTION_TIME);
            dailyTarget.setTargetValue(targetMotionTime);
            dailyTarget.setHappenDate(happenDate);
            dailyTarget.setCreateDate(nowDate);
            addDailyTargets.add(dailyTarget);
        }
        // 5. 目标运动卡路里
        if (targetCalorie != null && targetCalorie > 0) {
            DailyTarget dailyTarget = new DailyTarget();
            dailyTarget.setUserId(userId);
            dailyTarget.setTargetType(TargetTypeEnum.CALORIE);
            dailyTarget.setTargetValue(targetCalorie);
            dailyTarget.setHappenDate(happenDate);
            dailyTarget.setCreateDate(nowDate);
            addDailyTargets.add(dailyTarget);
        }
        // 6. 目标值不为空就保存到数据库
        if (addDailyTargets != null && addDailyTargets.size() > 0) {
            dailyTargetDao.addDailyTarget(addDailyTargets);
        }
    }

    @Override
    public List<DailyTarget> findDailyTargetsLast(Long userId) {
        return dailyTargetDao.findDailyTargetsLast(userId);
    }

    @Override
    public List<DailyTarget> findDailyTargetByHappenDate(Long userId, Integer happenDate) {
        return dailyTargetDao.findDailyTargetsByHappenDate(userId, happenDate);
    }

    @Override
    public List<DailyTarget> findDailyTargetsByGTHappenDate(Long userId, Integer happenDate) {
        // 1. 查询小余等于当前时间的最近一次目标值变更记录
        Integer motionTimeDate = dailyTargetDao.findRecentHappenDate(userId, happenDate, TargetTypeEnum.MOTION_TIME);
        Integer calorieDate = dailyTargetDao.findRecentHappenDate(userId, happenDate, TargetTypeEnum.CALORIE);
        Integer lastDate = null;
        if (motionTimeDate != null && calorieDate != null) {
            lastDate = motionTimeDate < calorieDate ? motionTimeDate : calorieDate;
        } else if (motionTimeDate != null) {
            lastDate = motionTimeDate;
        } else {
            lastDate = calorieDate;
        }
        // 2. 如果当前时间之前用户没有目标值变更记录,就查询用户所有的目标值变更记录信息
        return dailyTargetDao.findDailyTargetsByDayLastData(userId, lastDate);
    }

    @Override
    public Integer findDailyTargetCalorieInWeekInWeek(Long userId, Integer firstDayOfWeek, Integer timezone) {
        // 1. 根据用户所在时间计算用户所在本周首日
        Date nowDate = DateUtils.addMinutes(YFDateUtil.getUTCDate(), 15 * timezone);
        Date nowDay = DateUtils.truncate(nowDate, Calendar.DAY_OF_MONTH);
        int firstDayInWeek = YFDateUtil.getFirstDayOfWeekInt(nowDay, firstDayOfWeek);

        // 2. 查询小余等于当前时间的最近一次目标值变更记录
        Integer calorieDate = dailyTargetDao.findRecentHappenDate(userId, firstDayInWeek, TargetTypeEnum.CALORIE);
        List<DailyTarget> dailyTargets = dailyTargetDao.findDailyTargetsByDayLastData(userId, calorieDate);

        // 3. 计算周首日
        Integer calorieTargetTotal = 0;
        if (dailyTargets == null) {
            return calorieTargetTotal;
        } else {
            for (int i = 0; i < 7; i++) {
                for (DailyTarget dailyTarget : dailyTargets) {
                    if (firstDayInWeek >= dailyTarget.getHappenDate() && TargetTypeEnum.CALORIE == dailyTarget.getTargetType()) {
                        calorieTargetTotal += dailyTarget.getTargetValue();
                        break;
                    }
                }
                firstDayInWeek++;
            }
            return calorieTargetTotal;
        }
    }
}
