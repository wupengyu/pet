/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.service.impl;

import com.yf.pet.dao.sossms.SosDao;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.enums.ServiceModeType;
import com.yf.pet.entity.enums.SmsTypeEnum;
import com.yf.pet.entity.sossms.SmsLimit;
import com.yf.pet.entity.sossms.SmsRecord;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.SosService;
import com.yf.pet.utils.YFDateUtil;
import com.yf.pet.utils.YFResourceUtil;
import com.yf.pet.utils.primary.YFPrimaryKeyUtils;
import com.yf.pet.utils.twilio.TwilioYFUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

/**
 * sos短信发送接口实现层
 *
 * @author Infi
 */
@Service("sosService")
public class SosServiceImpl implements SosService {
    private static final Logger log = LoggerFactory.getLogger(SosServiceImpl.class);

    @Autowired
    private SosDao sosDao;

    /**
     * sos短信发送，半小时内只能发送一条
     */
    private final Integer sosNextInterval = NumberUtils.toInt(YFResourceUtil.getValueByKey("app-coros.properties", "sos.next.interval"));

    /**
     * sos短信发送一天只能发送10条
     */
    private final Integer sosMaxNumber = NumberUtils.toInt(YFResourceUtil.getValueByKey("app-coros.properties", "sos.maxnumber.today"));

    /**
     * twilio 账户发件人电话号码
     */
    private final String twilioFromNumber = YFResourceUtil.getValueByKey("app-coros.properties", "twilio.from.number");

    /**
     * sos 页面地址
     */
    private final String SOS_PAGE_URL = YFResourceUtil.getValueByKey("app-coros.properties", "sos.page.url");


    @Override
    public void addSosRecord(SmsRecord sosRecord) {
        // 1. 默认短信发送记录的创建时间
        Date nowDate = new Date();
        sosRecord.setCreateTime(nowDate);

        // 2. 查询用户sos短信发送统计信息
        Integer smsType = SmsTypeEnum.SOS;
        SmsLimit sosLimit = sosDao.findSosTimsByUserId(sosRecord.getUserId(), smsType);
        // 3. 如果该用户没有发送过sos短信，新增发送记录
        if (sosLimit == null) {
            SmsLimit saveSosLimit = new SmsLimit();
            saveSosLimit.setUserId(sosRecord.getUserId());
            saveSosLimit.setTimes(1);
            saveSosLimit.setSumTimes(1);
            saveSosLimit.setLastTime(nowDate);
            saveSosLimit.setSmsType(smsType);
            sosDao.addSosLimit(saveSosLimit);
        } else {
            // 4. 半小时内只能发送一次
            long interval = (nowDate.getTime() - sosLimit.getLastTime().getTime()) / 1000;
            if (Math.abs(interval) < sosNextInterval) {
                throw new YFException(ReturnMessageEnum.SMS_SEND_ONLY_30_MINUTE);
            }
            // 5. 同一天只能发送十条短信
            if (DateUtils.isSameDay(nowDate, sosLimit.getLastTime()) && sosLimit.getTimes() >= sosMaxNumber) {
                throw new YFException(ReturnMessageEnum.MAX_10_SMS_SEND_EVERYDAY);
            }

            // 6. 累加当天sos短信发送数量
            if (DateUtils.isSameDay(nowDate, sosLimit.getLastTime())) {
                sosDao.updateSosTimesAddUp(sosRecord.getUserId(), nowDate, smsType);
            } else {
                // 7. 如果不是同一天发送短信，就初始化当天短信发送次数
                sosDao.updateSosInit(sosRecord.getUserId(), nowDate, smsType);
            }
        }
        // 7. 添加sos短信发送记录
        Long sosId = YFPrimaryKeyUtils.getId(ServiceModeType.SOS_RECORD);
        sosRecord.setSosId(sosId);
        sosDao.addSosRecord(sosRecord);
        // 8. 发送短信
        //短信内容: Coros xxx fell down during cycling, but we can 't get his/her position, please go to help him/her.
        String smsBody = "Coros " + StringUtils.substring(sosRecord.getNickname(), 0, 10) + " fell down during cycling";

        // 9. 只有当经纬度不为空的情况下,发送的短信才会有连接地址
        if (StringUtils.isNotBlank(sosRecord.getSosLogLat())) {
            smsBody += ": " + SOS_PAGE_URL + "?sosid=" + sosRecord.getSosId();
        } else {
            smsBody += ", but we can 't get his/her position, please go to help him/her.";
        }
        try {
            TwilioYFUtils.sendSMS(sosRecord.getContactsMobile(), twilioFromNumber, smsBody);
        } catch (Exception e) {
            log.error("短信发送失败" + e.getMessage());
            throw new YFException(ReturnMessageEnum.SEND_SMS_FAILED);
        }
    }

    @Override
    public void updateSosRecordAddress(Integer sosId, String sosAddress) {
        sosDao.updateSosRecordAddress(sosId, sosAddress);
    }

    @Override
    public SmsRecord findSosRecordById(Long sosId) {
        SmsRecord smsRecord = sosDao.findSosRecordById(sosId);
        if (smsRecord != null) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
//            calendar.setTimeInMillis(smsRecord.getTumbleTime() * 1000);
            // 1. 跌倒时间UTC时间
            Date tumbleDate = YFDateUtil.getHappenDate(smsRecord.getTumbleTime(), smsRecord.getTumbleTimezone());
            smsRecord.setTumbleDate(tumbleDate);
            // 2. 时区名称显示: (GTM+08:45 Asia/Shanghai) 16:35:23, 07/11/2017
            StringBuilder timeZoneStringBuilder = new StringBuilder("(");

            // 3. 时区显示格式:(GMT+08:45)
            BigDecimal timeZoneDecimal = new BigDecimal(smsRecord.getTumbleTimezone()).divide(new BigDecimal(4))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            String hour = null;
            if (timeZoneDecimal.intValue() >= 0) {
                hour = String.format("%02d", timeZoneDecimal.intValue());
                timeZoneStringBuilder.append("GMT+");
            } else {
                hour = String.format("%03d", timeZoneDecimal.intValue());
                timeZoneStringBuilder.append("GMT");
            }
            timeZoneStringBuilder.append(hour);
            timeZoneStringBuilder.append(":");
            timeZoneStringBuilder.append(String.format("%02d", Math.abs((int) ((timeZoneDecimal.doubleValue() - timeZoneDecimal.intValue()) * 60))));
            // 3. 时区名称显示
            if (StringUtils.isNotBlank(smsRecord.getTimezoneName())) {
                timeZoneStringBuilder.append(" ");
                timeZoneStringBuilder.append(smsRecord.getTimezoneName());
            }
            timeZoneStringBuilder.append(")");

            // 4. sos页面显示的跌倒时间,格式:当前时间+ MM/DD/YYYY 比如：16:35:23, 07/11/2017
//            TimeZone timeZone = YFDateUtil.getTimezoneDefault(smsRecord.getTumbleTimezone());
            FastDateFormat fastDateFormat = FastDateFormat.getInstance("HH:mm:ss,MM/dd/yyyy", TimeZone.getTimeZone("UTC"));
            timeZoneStringBuilder.append(" ");
            timeZoneStringBuilder.append(fastDateFormat.format(tumbleDate));
            smsRecord.setSosShowTime(timeZoneStringBuilder.toString());
        }
        return smsRecord;
    }

    @Override
    public SmsLimit saveSmsContact(Long userId) {
        // 短信类型，验证紧急联系人
        Integer smsType = SmsTypeEnum.CONTACT;
        Date nowDate = new Date();
        // 1. 查询短信记录
        SmsLimit smsLimit = sosDao.findSosTimsByUserId(userId, smsType);
        // 3. 如果该用户没有发送过sos短信，新增发送记录
        if (smsLimit == null) {
            SmsLimit saveSosLimit = new SmsLimit();
            saveSosLimit.setUserId(userId);
            saveSosLimit.setTimes(1);
            saveSosLimit.setSumTimes(1);
            saveSosLimit.setLastTime(nowDate);
            saveSosLimit.setSmsType(smsType);
            sosDao.addSosLimit(saveSosLimit);
        } else if (DateUtils.isSameDay(smsLimit.getLastTime(), nowDate)) {
            // 4. 同一天只能发送十条短信
            if (smsLimit.getTimes() >= sosMaxNumber) {
                throw new YFException(ReturnMessageEnum.MAX_10_SMS_SEND_EVERYDAY);
            } else {
                // 5. 累加当天sos短信发送数量
                sosDao.updateSosTimesAddUp(userId, nowDate, smsType);
            }
        } else {
            // 6. 如果不是同一天发送短信，就初始化当天短信发送次数
            sosDao.updateSosInit(userId, nowDate, smsType);
        }
        return sosDao.findSosTimsByUserId(userId, smsType);
    }

}
