package com.yf.pet.service.impl;

import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.cache.RedisUtilsPet;
import com.yf.pet.common.utils.primary.YFPrimaryKeyUtils;
import com.yf.pet.dao.user.UserDao;
import com.yf.pet.common.enums.ServiceModeType;
import com.yf.pet.entity.user.User;
import com.yf.pet.common.ApplicationConstants;
import com.yf.pet.common.utils.CodeGenerator;
import com.yf.pet.entity.user.UserRegisterEnum;
import com.yf.pet.entity.user.vo.UserEmailLoginVo;
import com.yf.pet.entity.user.vo.UserOpenIdLoginVo;
import com.yf.pet.entity.user.vo.UserPwdResetVo;
import com.yf.pet.entity.user.vo.UserRegisterVo;
import com.yf.pet.exception.YFException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param userRegisterVo
     * @return
     */
    public User emailRegister(UserRegisterVo userRegisterVo) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterVo, user);
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
     * @param userEmailLoginVo
     * @return
     */
    public User emailLogin(UserEmailLoginVo userEmailLoginVo) {
        //根据email查找用户数据
        User user = userDao.findByEmail(userEmailLoginVo.getEmail());

        //用户不存在
        if (user == null) {
            throw new YFException(ReturnMessageEnum.ACCOUNT_NOT_EXIST);
        }
        //验证密码是否正确
        if (!user.getPwd().equals(userEmailLoginVo.getPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_ERROR);
        }

        //登录逻辑
        user = loginSave(user);
        return user;
    }

    /**
     * 第三方账户登录
     *
     * @param userOpenIdLoginVo
     * @return
     */
    public User openLogin(UserOpenIdLoginVo userOpenIdLoginVo) {
        //根据openId查找用户数据
        User user = userDao.findByOpenId(userOpenIdLoginVo.getOpenId(), userOpenIdLoginVo.getOpenType());

        //用户不存在，则注册第三方账户
        if (user == null) {
            if (userOpenIdLoginVo.getRegisterTimezone() == null) {
                throw new YFException(ReturnMessageEnum.REGISTER_TIMEZONE_NULL);
            }
            user = new User();
            BeanUtils.copyProperties(userOpenIdLoginVo,user);
            user.setRegisterType(UserRegisterEnum.OPENID);
            user = register(user);
            return user;
        }

        //用户存在则执行登录
        //验证密码是否正确
        if (!user.getPwd().equals(userOpenIdLoginVo.getPwd())) {
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
     * @param userPwdResetVo
     */
    public void pwdreset(UserPwdResetVo userPwdResetVo) {
        User user = userDao.findByEmail(userPwdResetVo.getEmail());
        if(user == null ){
            throw new YFException(ReturnMessageEnum.ACCOUNT_IS_EXIST);
        }

        //比对旧密码
        if (!userPwdResetVo.getPwd().equals(user.getPwd())) {
            throw new YFException(ReturnMessageEnum.PASSWORD_ERROR);
        }
        //修改密码
        user.setPwd(userPwdResetVo.getNewPwd());
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
}
