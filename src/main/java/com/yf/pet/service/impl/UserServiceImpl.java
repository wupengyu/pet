package com.yf.pet.service.impl;

import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.cache.RedisUtilsPet;
import com.yf.pet.common.utils.YFOSSUtils;
import com.yf.pet.common.utils.YFResourceUtil;
import com.yf.pet.common.utils.primary.YFPrimaryKeyUtils;
import com.yf.pet.dao.user.UserDao;
import com.yf.pet.common.enums.ServiceModeType;
import com.yf.pet.entity.user.User;
import com.yf.pet.common.ApplicationConstants;
import com.yf.pet.common.utils.CodeGenerator;
import com.yf.pet.entity.user.UserRegisterEnum;
import com.yf.pet.entity.user.dto.*;
import com.yf.pet.exception.YFException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    /**
     * 邮箱用户注册
     *
     * @param userRegisterDto
     * @return
     */
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
    public User openLogin(UserOpenIdLoginDto userOpenIdLoginDto) {
        //根据openId查找用户数据
        User user = userDao.findByOpenId(userOpenIdLoginDto.getOpenId(), userOpenIdLoginDto.getOpenType());

        //用户不存在，则注册第三方账户
        if (user == null) {
            if (userOpenIdLoginDto.getRegisterTimezone() == null) {
                throw new YFException(ReturnMessageEnum.REGISTER_TIMEZONE_NULL);
            }
            user = new User();
            BeanUtils.copyProperties(userOpenIdLoginDto, user);
            user.setRegisterType(UserRegisterEnum.OPENID);
            user = register(user);
            return user;
        }

        //用户存在则执行登录
        //验证密码是否正确
        if (!user.getPwd().equals(userOpenIdLoginDto.getPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_ERROR);
        }
        //执行登录保存
        user = loginSave(user);
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
    public User findAccessTokenIsValid(String accessToken) {
        return userDao.findAccessTokenIsValid(accessToken);
    }

    /**
     * 重置密码
     *
     * @param userPwdResetDto
     */
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
     * 登出
     *
     * @param accessToken
     */
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
    public User getUserInfo(String accessToken) {
        //token修改为空
        User user = userDao.findByAccessToken(accessToken);
        return user;
    }

    public User updateUserInfo(UserUpdateInfoDto userUpdateInfoDto) throws IOException {
        //查询用户信息
        User user = userDao.findByAccessToken(userUpdateInfoDto.getAccessToken());
        if (user == null) {
            throw new YFException(ReturnMessageEnum.TOKEN_INVALID);
        }

        //上传头像文件到oss
        if (userUpdateInfoDto.getHeadPicFile() != null) {
            String accessKeyId = YFResourceUtil.getValueByKey("resource.properties", "oss.accessKeyId");
            String accessKeySecret = YFResourceUtil.getValueByKey("resource.properties", "oss.accessKeySecret");
            String endpoint = YFResourceUtil.getValueByKey("resource.properties", "oss.endpoint");
            String bucketName = YFResourceUtil.getValueByKey("resource.properties", "oss.bucketName");
            String folderStr = YFResourceUtil.getValueByKey("resource.properties", "oss.headpic.folderStr");
            String headPicUrl = YFOSSUtils.PutObject(accessKeyId, accessKeySecret, endpoint, userUpdateInfoDto.getHeadPicFile().getInputStream(), bucketName, folderStr, ".jpg",
                    userUpdateInfoDto.getHeadPicFile().getInputStream().available());
            user.setHeadPic(headPicUrl);
        }

        BeanUtils.copyProperties(userUpdateInfoDto,user);

        userDao.updateUserInfo(user);
        return user;
    }

    public void getBackPwd(UserForgetPwdDto userForgetPwdDto){

    }
}
