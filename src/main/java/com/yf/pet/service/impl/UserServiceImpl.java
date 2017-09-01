package com.yf.pet.service.impl;

import com.yf.pet.dao.user.UserDao;
import com.yf.pet.entity.enums.UserTypeEnum;
import com.yf.pet.entity.user.User;
import com.yf.pet.utils.ApplicationConstants;
import com.yf.pet.utils.CodeGenerator;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>Description: user</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/1
 */
@Service("userService")
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;


    public User addUser(User user){
        //设置属性
        user.setAccessToken(CodeGenerator.getAccessToken());
        Date nowDate = new Date();
        Date validityDate = DateUtils.addDays(DateUtils.truncate(nowDate, Calendar.DAY_OF_MONTH),
                ApplicationConstants.TOKEN_VALID_DAY_COUNT);
        user.setValidityDate(validityDate);
        user.setCreateDate(nowDate);
        Integer userId = userDao.addUser(user);
        user.setUserId(userId);
        return user;
    }

    /**
     * 查询账号是否已经存在
     *
     * @param user
     * @return
     */
    public Boolean findAccountIsExist(User user){
        Integer result = userDao.findAccountIsExist(user);
        if(result <= 0 ){
            return false;
        }else {
            return true;
        }
    }
}
