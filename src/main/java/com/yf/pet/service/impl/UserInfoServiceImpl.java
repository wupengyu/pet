package com.yf.pet.service.impl;

import com.twilio.sdk.resource.instance.lookups.PhoneNumber;
import com.yf.pet.dao.user.UserInfoDao;
import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.entity.dailytarget.DailyTarget;
import com.yf.pet.entity.enums.SmsTypeEnum;
import com.yf.pet.entity.enums.TargetTypeEnum;
import com.yf.pet.entity.sossms.LookupNumber;
import com.yf.pet.entity.sossms.SmsLimit;
import com.yf.pet.entity.user.Account;
import com.yf.pet.entity.user.UserInfo;
import com.yf.pet.entity.user.UserSimpleInfo;
import com.yf.pet.entity.user.UserTimezoneModify;
import com.yf.pet.entity.user.vo.UserInfoVO;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.*;
import com.yf.pet.utils.*;
import com.yf.pet.utils.regex.YFRegextUtils;
import com.yf.pet.utils.twilio.TwilioYFUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 用户接口实现类
 * Created by Infi on 17/3/26.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DailyTargetService dailyTargetService;

    @Autowired
    private UserTimezoneModifyService userTimezoneModifyService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private LookupNumberService lookupNumberService;

    @Autowired
    private AccountTemporaryService accountTemporaryService;


    /**
     * sos短信发送一天只能发送10条
     */
    private final Integer sosMaxNumber = NumberUtils.toInt(YFResourceUtil.getValueByKey("app-coros.properties", "sos.maxnumber.today"));
    /**
     * twilio 账户发件人电话号码
     */
    private final String twilioFromNumber = YFResourceUtil.getValueByKey("app-coros.properties", "twilio.from.number");


    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoDao.addUserInfo(userInfo);
    }

    @Override
    public void updateUserInfoByUserId(UserInfo userInfo) {
        userInfoDao.updateUserInfoByUserId(userInfo);
    }

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoDao.findUserInfoByUserId(userId);
    }

    @Override
    public UserInfo findUserInfoByToken(String accessToken) {
        UserSimpleInfo userSimpleInfo = accountService.findSimpleUserInfoByToken(accessToken);
        return userInfoDao.findUserInfoByUserId(userSimpleInfo.getUserId());
    }

    @Override
    public Long updateUserInfoByToken(String accessToken, UserInfoVO userInfoVO) throws IOException {
        // 1. 根据token查询帐号信息
        Account account = accountService.findAccountByAccessToken(accessToken);
        userInfoVO.setUserId(account.getUserId());
        // 2. 帐号名称,顺序:mobile,email,facebookId,weixinId,用户保存文件的路径,mobile不为空,就保存在mobile命名的文件夹中
        String accountName = "";
        if (StringUtils.isNotBlank(account.getMobile())) {
            accountName = account.getMobile();
        } else if (StringUtils.isNotBlank(account.getEmail())) {
            accountName = account.getEmail();
        } else if (StringUtils.isNotBlank(account.getFacebookId())) {
            accountName = account.getFacebookId();
        } else if (StringUtils.isNotBlank(account.getWeixinId())) {
            accountName = account.getWeixinId();
        }

        // 7. 保存头像文件
        if (userInfoVO.getHeadPicFile() != null) {
            //获取存储路径
            Integer dateFileDir = YFDateUtil.getTodayNumber();
            String uploadPath = YFEnumFileKey.HEAD_PIC.getLocalPath(String.valueOf(dateFileDir));
            //生成文件名
            String tmpfileName = CodeGenerator.getFileNameCodeString();
            // 扩展名
            String fileExt = StringUtils.substringAfterLast(userInfoVO.getHeadPicFile().getOriginalFilename(), ".");
            // 文件名加上扩展名
            if (StringUtils.isNotBlank(fileExt)) {
                tmpfileName = tmpfileName + "." + fileExt;
            }
            //将标签byte数据保存到本地文件
            FileProecssUtil.saveLocalFile(userInfoVO.getHeadPicFile().getBytes(), tmpfileName, uploadPath);
            //存储路径是帐号加斜杠加文件名: 13710637126/1490681576S8WNUA79L3P1SLQR7S20
            userInfoVO.setHeadPic(dateFileDir + "/" + tmpfileName);
        }

        // 4. 保存用户信息
        UserInfo userInfo = userInfoVOtoUserInfo(userInfoVO);
        // 1. 这里有可能不会修改用户信息,只修改目标值
        if (StringUtils.isNotBlank(userInfo.getNickname())
                || userInfo.getSex() != null
                || userInfo.getStature() != null
                || userInfo.getWeight() != null
                || userInfo.getBirthday() != null
                || StringUtils.isNotBlank(userInfo.getHeadPic())
                || StringUtils.isNotBlank(userInfo.getLongitude())
                || StringUtils.isNotBlank(userInfo.getLatitude())
                || StringUtils.isNotBlank(userInfo.getCountryCode())
                || userInfo.getUnit() != null) {
            userInfoDao.updateUserInfoByUserId(userInfo);
        }
        // TODO 这里优化,查询用户信息次数太多
        UserInfo findUserInfo = userInfoDao.findUserInfoByUserId(account.getUserId());
        // 5. 保存运动目标
        if ((userInfoVO.getTargetCalorie() != null && userInfoVO.getTargetCalorie() > 0)
                || (userInfoVO.getTargetMotionTime() != null && userInfoVO.getTargetMotionTime() > 0)) {
            dailyTargetService.addDailyTarget(account.getUserId(), findUserInfo.getTimezone(),
                    userInfoVO.getTargetMotionTime(), userInfoVO.getTargetCalorie());
            // 用户修改信息时的时区
            Integer timezone = userInfoVO.getTimezone() == null ? 0 : userInfoVO.getTimezone();
            // 6. 更新日常数据天统计表中的目标值
//            dailyDataService.updateTodayTarget(account.getUserId(), userInfoVO.getTargetCalorie(),
//                    userInfoVO.getTargetMotionTime(), timezone);
        }

        return account.getUserId();
    }

    @Override
    public UserInfoVO findAccountAndUserInfoAndTargetByUserId(Long userId) {
        //edit by dinghui 20170710 此处需要修改findAccountByUserId的返回字段
        Account account = accountService.findAccountByUserId(userId);
        UserInfo userInfo = userInfoDao.findUserInfoByUserId(userId);
        // 7. 查询目标值
        List<DailyTarget> dailyTargets = dailyTargetService.findDailyTargetsLast(userId);
        // 3. 查询未激活的邮箱地址
        String unactivatedEmail = accountTemporaryService.findLastEmailByUserId(userId);
        return userToUserInfoVO(account, userInfo, dailyTargets, unactivatedEmail);
    }

    @Override
    public UserInfoVO findAccountAndUserInfoByAccessToken(String accessToken) {
        Account account = accountService.findAccountByAccessToken(accessToken);
        UserInfo userInfo = userInfoDao.findUserInfoByUserId(account.getUserId());
        // 7. 查询目标值
        List<DailyTarget> dailyTargets = dailyTargetService.findDailyTargetsLast(account.getUserId());
        // 3. 查询未激活的邮箱地址
        String unactivatedEmail = accountTemporaryService.findLastEmailByUserId(account.getUserId());
        return userToUserInfoVO(account, userInfo, dailyTargets, unactivatedEmail);
    }

    @Override
    public List<UserInfo> findSimpleUserInfoListByUserId(List<Long> userIds) {
        return userInfoDao.findSimpleUserInfoListByUserId(userIds);
    }

    @Override
    public void updateTimezone(UserInfo userInfo, Integer timezone) {
        if (timezone == null) {
            return;
        }
        // 1. 当APP上传的时区和用户信息中的时区不相同的时候才会修改用户信息
        if (!timezone.equals(userInfo.getTimezone())) {
            userInfoDao.updateTimezoneByUserId(userInfo.getUserId(), timezone);
        }
        // 2. 新增一条用户时区变更记录信息
        Date nowDate = new Date();
        UserTimezoneModify userTimezoneModify = new UserTimezoneModify();
        userTimezoneModify.setUserId(userInfo.getUserId());
        userTimezoneModify.setTimezone(timezone);
        userTimezoneModify.setCreateTime(nowDate);
        userTimezoneModifyService.addUserTimezone(userTimezoneModify);
    }

    @Override
    public void saveUserToContact(Long userId, String contactsMobile, String nickName, String contactNote, String phoneCode, String contactCountryCode) {
        // 1. 检查参数
        if (userId == null || StringUtils.isBlank(contactsMobile) || StringUtils.isBlank(phoneCode)) {
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
        //2. 检查电话号码是否包含字母,包含字母的电话号码是错误的
        if (!YFRegextUtils.checkIsNotContainLetters(contactsMobile)) {
            throw new YFException(ReturnMessageEnum.INCOMPLETE_PHONE_NO);
        }
        // 2. 先查看用户信息是否存在
        UserInfo userInfo = userInfoDao.findUserInfoByUserId(userId);
        // 3. 如果电话号码不相同，就发送短信
        if (StringUtils.isNotBlank(contactsMobile) && StringUtils.isNotBlank(phoneCode)
                && (!contactsMobile.equals(userInfo.getContactsMobile()) || !phoneCode.equals(userInfo.getPhoneCode()))) {
            sendSms(userId, contactsMobile, nickName, phoneCode);
        }
        userInfoDao.updateUserToContact(userId, contactsMobile, contactNote, phoneCode, contactCountryCode);
    }


    /**
     * 发送添加紧急联系人验证短信
     *
     * @param userId         用户id
     * @param contactsMobile 紧急联系人电话
     * @param nickName       用户昵称
     * @param phoneCode      紧急联系人电话区号
     */
    private void sendSms(Long userId, String contactsMobile, String nickName, String phoneCode) {
        // 电话号码格式，区号+电话号码
        String phoneNumber = phoneCode + contactsMobile;
        // 1. 检查电话号码
        boolean isTrue = lookupsNumber(phoneNumber);
        if (!isTrue) {
            throw new YFException(ReturnMessageEnum.INCOMPLETE_PHONE_NO);
        }

        // 3. 添加短信发送记录
        Integer smsType = SmsTypeEnum.CONTACT;
        Date nowDate = new Date();
        SmsLimit smsLimit = smsService.findSmsTimsByUserId(userId, smsType);
        // 4. 如果该用户没有发送过sos短信，新增发送记录
        if (smsLimit == null) {
            SmsLimit saveSosLimit = new SmsLimit();
            saveSosLimit.setUserId(userId);
            saveSosLimit.setTimes(1);
            saveSosLimit.setSumTimes(1);
            saveSosLimit.setLastTime(nowDate);
            saveSosLimit.setSmsType(smsType);
            smsService.addSmsLimit(saveSosLimit);
        } else if (DateUtils.isSameDay(smsLimit.getLastTime(), nowDate)) {
            // 5. 同一天只能发送十条短信
            if (smsLimit.getTimes() >= sosMaxNumber) {
                throw new YFException(ReturnMessageEnum.MAX_10_SMS_SEND_EVERYDAY);
            } else {
                // 6. 累加当天sos短信发送数量
                smsService.updateSmsTimesAddUp(userId, nowDate, smsType);
            }
        } else {
            // 7. 如果不是同一天发送短信，就初始化当天短信发送次数
            smsService.updateSmsInit(userId, nowDate, smsType);
        }

        // 8. 发送短信
        nickName = nickName == null ? "" : nickName;
        // String smsBody = "【Coros】Hi," + StringUtils.substring(nickName, 0, 10) + " has set up you as emergency contact in Coros App";
        String smsBody = "Coros Hi," + StringUtils.substring(nickName, 0, 10) + " has set up you as emergency contact in Coros App";

        try {
            TwilioYFUtils.sendSMS(phoneNumber, twilioFromNumber, smsBody);
        } catch (Exception e) {
            log.error("短信发送失败" + e.getMessage());
            throw new YFException(ReturnMessageEnum.SEND_SMS_FAILED);
        }
    }

    /**
     * 检查电话号码
     *
     * @param number 电话号码
     * @return 是否是正确的电话号码
     */
    private boolean lookupsNumber(String number) {
        // 1. 在lookup表中查询，该电话号码是否已经检测通过了。
        LookupNumber lookupNumber = lookupNumberService.findNumber(number);
        if (lookupNumber != null && StringUtils.isNotBlank(lookupNumber.getNumber())) {
            return true;
        } else {
            // 2. 通过twilio的api接口来检查电话号码是否正确
            PhoneNumber phoneNumber = TwilioYFUtils.lookupsCheckPhoneNumber(number);
            // 3. 如果lookups返回的结果是null，就说明这个电话号码是错误的，就直接返回false
            if (phoneNumber == null) {
                return false;
            } else {
                // 3. 把验证通过的电话号码，添加到
                String type = phoneNumber.getType() == null ? "" : phoneNumber.getType().name();
                lookupNumberService.addNumber(number, new Date(), type, phoneNumber.getCarrierName(), phoneNumber.getCountryCode());
                return true;
            }
        }
    }


    /**
     * 把帐号、用户,目标值转换成userInfoVO对象返回给前台
     *
     * @param account      帐号信息
     * @param userInfo     用户信息
     * @param dailyTargets 用户目标值信息
     * @param unactivatedEmail 未激活的邮箱地址
     * @return UserInfoVO 用户信息
     */
    private UserInfoVO userToUserInfoVO(Account account, UserInfo userInfo, List<DailyTarget> dailyTargets, String unactivatedEmail) {
        UserInfoVO userInfoVO = new UserInfoVO();
        // 1. 赋值帐号信息
        userInfoVO.setUserId(account.getUserId());
        userInfoVO.setMobile(account.getMobile());
        userInfoVO.setEmail(account.getEmail());
        userInfoVO.setFacebookId(account.getFacebookId());
        userInfoVO.setWeixinId(account.getWeixinId());
        userInfoVO.setAccessToken(account.getAccessToken());
        userInfoVO.setClientType(account.getClientType());
        userInfoVO.setRegisterType(account.getRegisterType());
        userInfoVO.setLoginType(account.getLoginType());
        // 注册时间戳,精确到秒
        userInfoVO.setRegisterDate(account.getCreateDate().getTime() / 1000);
        // 邮箱激活状态为空,就返回未激活
        userInfoVO.setActivateStatus(account.getActivateStatus());

        // 2. 赋值用户信息
        userInfoVO.setNickname(userInfo.getNickname());
        //start 20170710 edit by dinghui 新增微信和facebook昵称
        userInfoVO.setWeixinName(account.getWeixinName());
        userInfoVO.setFacebookName(account.getFacebookName());
        //end

        userInfoVO.setSex(userInfo.getSex());
        userInfoVO.setStature(userInfo.getStature());
        userInfoVO.setWeight(userInfo.getWeight());
        userInfoVO.setBirthday(userInfo.getBirthday());
        userInfoVO.setHeadPic(userInfo.getHeadPic());
        if (StringUtils.isNotBlank(userInfo.getHeadPic())) {
            String headPicUrl = YFEnumFileKey.HEAD_PIC.getHttpPath() + userInfo.getHeadPic();
            userInfoVO.setHeadPic(headPicUrl);
        }
        userInfoVO.setLongitude(userInfo.getLongitude());
        userInfoVO.setLatitude(userInfo.getLatitude());
        userInfoVO.setRegisterTimezone(userInfo.getRegisterTimezone());
        userInfoVO.setRegisterTimestamp(userInfo.getRegisterTimestamp());
        userInfoVO.setTimezone(userInfo.getTimezone());
        userInfoVO.setCountryCode(userInfo.getCountryCode());
        userInfoVO.setUnit(userInfo.getUnit());
        userInfoVO.setContactsMobile(userInfo.getContactsMobile());
        userInfoVO.setContactCountryCode(userInfo.getContactCountryCode());
        userInfoVO.setPhoneCode(userInfo.getPhoneCode());
        userInfoVO.setContactNote(userInfo.getContactNote());

        // 3. 赋值未激活的邮箱地址
        userInfoVO.setUnactivatedEmail(unactivatedEmail);

        // 4. 赋值目标值
        if (dailyTargets != null) {
            for (DailyTarget dailyTarget : dailyTargets) {
                if (TargetTypeEnum.MOTION_TIME == dailyTarget.getTargetType()) {
                    userInfoVO.setTargetMotionTime(dailyTarget.getTargetValue());
                } else if (TargetTypeEnum.CALORIE == dailyTarget.getTargetType()) {
                    userInfoVO.setTargetCalorie(dailyTarget.getTargetValue());
                }
            }
        }
        return userInfoVO;
    }

    /**
     * 把userInfoVo 转换成userInfo
     *
     * @param userInfoVO userInfoVO
     * @return 用户信息
     */
    private UserInfo userInfoVOtoUserInfo(UserInfoVO userInfoVO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userInfoVO.getUserId());
        userInfo.setNickname(userInfoVO.getNickname());
        userInfo.setSex(userInfoVO.getSex());
        userInfo.setStature(userInfoVO.getStature());
        userInfo.setWeight(userInfoVO.getWeight());
        userInfo.setBirthday(userInfoVO.getBirthday());
        userInfo.setHeadPic(userInfoVO.getHeadPic());
        userInfo.setLongitude(userInfoVO.getLongitude());
        userInfo.setLatitude(userInfoVO.getLatitude());
        userInfo.setCountryCode(userInfoVO.getCountryCode());
        userInfo.setUnit(userInfoVO.getUnit());
        return userInfo;
    }

}
