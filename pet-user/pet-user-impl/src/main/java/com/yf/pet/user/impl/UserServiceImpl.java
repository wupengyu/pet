package com.yf.pet.user.impl;

import com.google.gson.Gson;
import com.yf.pet.common.ApplicationConstants;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.cache.ProductModel;
import com.yf.pet.common.cache.ProductType;
import com.yf.pet.common.cache.RedisUtilsPet;
import com.yf.pet.common.cache.SessionCacheWrapper;
import com.yf.pet.common.enums.ServiceModeType;
import com.yf.pet.common.exception.YFException;
import com.yf.pet.common.utils.CodeGenerator;
import com.yf.pet.common.utils.YFOSSUtils;
import com.yf.pet.common.utils.YFResourceUtil;
import com.yf.pet.common.utils.primary.YFPrimaryKeyUtils;
import com.yf.pet.user.checkcode.CheckCode;
import com.yf.pet.user.checkcode.enums.CheckCodeEnums;
import com.yf.pet.user.dao.UserDao;
import com.yf.pet.user.dto.*;
import com.yf.pet.user.entity.User;
import com.yf.pet.user.entity.UserRegisterEnum;
import com.yf.pet.user.service.IUserService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: user</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/1
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;


//    @Autowired
//    private StringRedisTemplate redisTemplate;

    @Autowired
    private PetEmailService petEmailService;

    /**
     * 邮箱用户注册
     *
     * @param userRegisterDto
     * @return
     */
    @Override
    public User emailRegister(UserRegisterDto userRegisterDto) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterDto, user);
        user.setRegisterType(UserRegisterEnum.EMAIL);
//        user.setActivateStatus(UserActivateStatus.ACTIVATE);//邮箱激活
//        user.setLoginType(LoginTypeEnum.EMAIL);
        user = register(user);
        return user;
    }

    /**
     * 注册逻辑
     *
     * @param user
     * @return
     */
    private User register(User user) {
        //设置属性
        user.setAccessToken(CodeGenerator.getAccessToken());
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Calendar calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();
        Date validityDate = DateUtils.addDays(DateUtils.truncate(nowDate, Calendar.DAY_OF_MONTH),
                ApplicationConstants.TOKEN_VALID_DAY_COUNT);
        user.setValidityDate(validityDate);
        user.setCreateDate(nowDate);

        //设置userId
        Long userId = YFPrimaryKeyUtils.getId(ServiceModeType.USER);
        user.setUserId(userId);
        userDao.addUser(user);

        //把用户信息保存到redis
        RedisUtilsPet.putUserKeyToken(user);
        return user;
    }

    /**
     * 用户email登录
     *
     * @param userEmailLoginDto
     * @return
     */
    @Override
    public User emailLogin(UserEmailLoginDto userEmailLoginDto) {
        //根据email查找用户数据
        User user = userDao.findByEmail(userEmailLoginDto.getEmail());

        //用户不存在
        if (user == null) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_NOT_EXIST);
        }
        //验证密码是否正确
        if (!user.getPwd().equals(userEmailLoginDto.getPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_ERROR);
        }

        //登录逻辑
        user = loginSave(user);
        return user;
    }

    /**
     * 第三方账户登录
     *
     * @param userOpenIdLoginDto
     * @return
     */
    @Override
    public User openLogin(UserOpenIdLoginDto userOpenIdLoginDto) throws IOException {
        //根据openId查找用户数据
        User user = userDao.findByOpenId(userOpenIdLoginDto.getOpenId(), userOpenIdLoginDto.getOpenType());

        //用户不存在，则执行注册逻辑
        if (user == null) {
            openRegister(userOpenIdLoginDto);
        }

        //用户存在则执行登录
        user = loginSave(user);
        return user;
    }

    /**
     * 第三方账号注册
     *
     * @param userOpenIdLoginDto
     * @return
     * @throws IOException
     */
    private User openRegister(UserOpenIdLoginDto userOpenIdLoginDto) throws IOException {
        //如果是第三方账号的邮箱且邮箱已存在，则直接合并第三方账号到现有账号里
        User user = new User();
        Boolean marge = false;
        if (userOpenIdLoginDto.getOpenEmail() != null && userOpenIdLoginDto.getOpenEmail()
                && !StringUtils.isEmpty(userOpenIdLoginDto.getEmail())) {
            user = userDao.findByEmail(userOpenIdLoginDto.getEmail());
            if (user != null) {
                marge = true;
            }
        }

        //复制属性
        BeanUtils.copyProperties(userOpenIdLoginDto, user);
        //上传头像
        if (userOpenIdLoginDto.getHeadPicFile() != null && userOpenIdLoginDto.getHeadPicFile().length > 0) {
            String headPicUrl = uploadHeadPicToOss(userOpenIdLoginDto.getHeadPicFile());
            user.setHeadPic(headPicUrl);
        }

        if (marge) {
            //合并账号,更新资料，以facebook的为主
            userDao.updateUserInfo(user);
        } else {
            //不存在则执行注册流程
            if (userOpenIdLoginDto.getRegisterTimezone() == null) {
                throw new YFException(ReturnMessageEnum.REGISTER_TIMEZONE_NULL);
            }
            user.setRegisterType(UserRegisterEnum.OPENID);
            user = register(user);
        }
        return user;
    }

    /**
     * 登录保存
     *
     * @param user
     * @return
     */
    private User loginSave(User user) {
        if (!StringUtils.isEmpty(user.getAccessToken())) {
            //删除redis里以前的Key
            RedisUtilsPet.evictUserByToken(user.getAccessToken());
        }
        //生成新的令牌
        user.setAccessToken(CodeGenerator.getAccessToken());
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));//线上服务器在0时区
        Calendar calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();
        Date validityDate = DateUtils.addDays(DateUtils.truncate(nowDate, Calendar.DAY_OF_MONTH),
                ApplicationConstants.TOKEN_VALID_DAY_COUNT);
        user.setValidityDate(validityDate);

        //更新数据库信息
        userDao.updateUserLoginInfo(user);
        //更新redis存储的用户信息
        RedisUtilsPet.putUserKeyToken(user);
        return user;
    }

    /**
     * 查询账号是否已经存在
     *
     * @param
     * @return
     */
    @Override
    public Boolean findAccountIsExist(String email, String openId) {
        Integer result = userDao.findAccountIsExist(email, openId);
        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证accessToken是否有效
     *
     * @param accessToken accessToken
     * @return 用户ID, token过期时间信息
     */
    @Override
    public User findAccessTokenIsValid(String accessToken) {
        return userDao.findAccessTokenIsValid(accessToken);
    }

    /**
     * 重置密码
     *
     * @param userPwdResetDto
     */
    @Override
    public void pwdreset(UserPwdResetDto userPwdResetDto) {
        User user = userDao.findByEmail(userPwdResetDto.getEmail());
        if (user == null) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_EXIST);
        }

        //比对旧密码
        if (!userPwdResetDto.getPwd().equals(user.getPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_ERROR);
        }
        //修改密码
        user.setPwd(userPwdResetDto.getNewPwd());
        userDao.pwdReset(user);
    }

    /**
     * 根据验证码修改密码
     *
     * @param userPwdResetDto
     */
    @Override
    public void resetPwdByCode(UserPwdResetDto userPwdResetDto) {
        User user = userDao.findByEmail(userPwdResetDto.getEmail());
        if (user == null) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_EXIST);
        }

        //比对验证码
        String key = ProductType.PET + ":" + ProductModel.EMAIL_CODE + ":" + userPwdResetDto.getEmail();
        String code = (String) SessionCacheWrapper.getRedisTemplate().opsForValue().get(key);
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(userPwdResetDto.getCode())) {
            throw new YFException(ReturnMessageEnum.CODE_ERROR);
        }
        Gson gson = new Gson();
        CheckCode checkCode = gson.fromJson(code, CheckCode.class);
        if (checkCode == null || StringUtils.isEmpty(checkCode.getCheckCode()) || !checkCode.getCheckCode().equals(userPwdResetDto.getCode())) {
            throw new YFException(ReturnMessageEnum.CODE_ERROR);
        }
        //有效次数为一次，一次验证通过就失效
        SessionCacheWrapper.getRedisTemplate().delete(key);

        //修改密码
        user.setPwd(userPwdResetDto.getPwd());
        userDao.pwdReset(user);
    }


//    public static void main(String[] args) {
//        System.out.println(YFMD5.getMD5("abcdef"));
//    }

    /**
     * 登出
     *
     * @param accessToken
     */
    @Override
    public void loginOut(String accessToken) {
        //token修改为空
        userDao.updateTokenIsNull(accessToken);
        //清除redis里的用户信息
        RedisUtilsPet.evictUserByToken(accessToken);
    }

    /**
     * 获取用户信息
     *
     * @param accessToken
     */
    @Override
    public User getUserInfo(String accessToken) {
        //token修改为空
        User user = userDao.findByAccessToken(accessToken);
        return user;
    }

    /**
     * 修改用户信息
     *
     * @param userUpdateInfoDto
     * @return
     * @throws IOException
     */
    @Override
    public User updateUserInfo(UserUpdateInfoDto userUpdateInfoDto) throws IOException {
        //查询用户信息
        User user = userDao.findByAccessToken(userUpdateInfoDto.getAccessToken());
        if (user == null) {
            throw new YFException(ReturnMessageEnum.TOKEN_INVALID);
        }
        //上传头像文件到oss
        if (userUpdateInfoDto.getHeadPicFile() != null) {
            String headPicUrl = uploadHeadPicToOss(userUpdateInfoDto.getHeadPicFile());
            user.setHeadPic(headPicUrl);
        }

        BeanUtils.copyProperties(userUpdateInfoDto, user);

        userDao.updateUserInfo(user);
        return user;
    }

    /**
     * 上传头像到oss
     *
     * @param headPicBytes
     * @return
     * @throws IOException
     */
    private String uploadHeadPicToOss(byte[] headPicBytes) throws IOException {
        String accessKeyId = YFResourceUtil.getValueByKey("resource.properties", "oss.accessKeyId");
        String accessKeySecret = YFResourceUtil.getValueByKey("resource.properties", "oss.accessKeySecret");
        String endpoint = YFResourceUtil.getValueByKey("resource.properties", "oss.endpoint");
        String bucketName = YFResourceUtil.getValueByKey("resource.properties", "oss.bucketName");
        String folderStr = YFResourceUtil.getValueByKey("resource.properties", "oss.headpic.folderStr");
        InputStream inputStream = new ByteArrayInputStream(headPicBytes);
        String headPicUrl = YFOSSUtils.PutObject(accessKeyId, accessKeySecret, endpoint, inputStream, bucketName, folderStr, ".jpg",
                inputStream.available());
        return headPicUrl;
    }

    /**
     * 发送邮件重置密码
     *
     * @param email
     */
    @Override
    public void sendEmailResetPwd(String email) {
        //先验证邮箱是否已注册
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_NOT_EXIST);
        }

        //生成邮件重置密码的验证码，有效时间15分钟，15分钟过后验证码失效;验证码放入redis缓存
        String code = CodeGenerator.getCodeString(ApplicationConstants.EMAIL_CODE);
        CheckCode checkCode = new CheckCode(code, new Date(), CheckCodeEnums.EAMIL, user.getEmail());
        Gson gson = new Gson();
        String value = gson.toJson(checkCode);
        String key = ProductType.PET + ":" + ProductModel.EMAIL_CODE + ":" + user.getEmail();
        SessionCacheWrapper.getRedisTemplate().opsForValue().set(key, value, 1, TimeUnit.HOURS);

        //发送邮件
        petEmailService.sendMail(user.getEmail(), "yftech", petEmailService.createResetPwdHtml(user.getEmail(), code));
    }
}
