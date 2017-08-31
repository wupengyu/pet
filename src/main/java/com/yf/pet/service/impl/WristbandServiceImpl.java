package com.yf.pet.service.impl;

import com.yf.pet.dao.wristband.WristbandInfoDao;
import com.yf.pet.dao.wristband.WristbandUserDao;
import com.yf.pet.entity.enums.ServiceModeType;
import com.yf.pet.entity.wristband.WristbandInfo;
import com.yf.pet.service.WristbandService;
import com.yf.pet.utils.primary.YFPrimaryKeyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 设备使用或激活信息接口实现层
 * Created by Infi on 17/6/26.
 */
@Service("wristbandService")
public class WristbandServiceImpl implements WristbandService {
    @Autowired
    private WristbandInfoDao wristbandInfoDao;

    @Autowired
    private WristbandUserDao wristbandUserDao;

    @Override
    public void saveWristbands(List<WristbandInfo> wristbands) {
        // 1. 查询那些设备信息已经保存到设备激活表
        List<WristbandInfo> findWristbandActivates = wristbandInfoDao.findWristbandInfos(wristbands);
        // 2. 查询那些设备信息已经保存到设备使用表
        List<WristbandInfo> findWristbandUsers = wristbandUserDao.findWristbandUserList(wristbands);

        // 3. 设备激活、使用新增修改对象分类
        List<WristbandInfo> addWristbandActivates = new ArrayList<WristbandInfo>();
        List<WristbandInfo> addWristbandUsers = new ArrayList<WristbandInfo>();
        List<WristbandInfo> updateWristbandUsers = new ArrayList<WristbandInfo>();
        Date nowDate = new Date();
        for (WristbandInfo wristband : wristbands) {
            wristband.setId(YFPrimaryKeyUtils.getId(ServiceModeType.WRISTBAND));
            wristband.setActivateTime(nowDate);
            wristband.setCreateDate(nowDate);
            wristband.setUpdateDate(nowDate);
            // 4. 如果设备ID为空,就直接跳出
            if (StringUtils.isBlank(wristband.getMac())) {
                break;
            }
            // 4. 设备激活信息分类
            if (findWristbandActivates == null || findWristbandActivates.size() == 0) {
                addWristbandActivates.add(wristband);
            } else {
                boolean isExist = false;
                for (WristbandInfo findWristbandActivate : findWristbandActivates) {
                    if (wristband.getDeviceId().equals(findWristbandActivate.getDeviceId())) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    addWristbandActivates.add(wristband);
                }
            }

            // 5. 设备使用信息分类
            if (findWristbandUsers == null || findWristbandUsers.size() == 0) {
                addWristbandUsers.add(wristband);
            } else {
                boolean isExist = false;
                for (WristbandInfo findWristbandUser : findWristbandUsers) {
                    if (wristband.getDeviceId().equals(findWristbandUser.getDeviceId())) {
                        updateWristbandUsers.add(wristband);
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    addWristbandUsers.add(wristband);
                }
            }
        }
        // 6. 保存设备激活信息
        if (addWristbandActivates != null && addWristbandActivates.size() > 0) {
            wristbandInfoDao.addWristbandInfos(addWristbandActivates);
        }
        // 7. 保存设备使用信息
        if (addWristbandUsers != null && addWristbandUsers.size() > 0) {
            wristbandUserDao.addWristbandUsers(addWristbandUsers);
        }
        if (updateWristbandUsers != null && updateWristbandUsers.size() > 0) {
            wristbandUserDao.updateWristbandUsers(updateWristbandUsers);
        }
    }

    @Override
    public void saveWristbandUser(final WristbandInfo wristband) {
        // 2. 查询那些设备信息已经保存到设备使用表
        WristbandInfo findWristbandUser = wristbandUserDao.findWristbandUser(wristband.getUserId(), wristband.getDeviceId());
        Date nowDate = new Date();
        wristband.setUpdateDate(nowDate);
        if (findWristbandUser == null) {
            wristband.setCreateDate(nowDate);
            List<WristbandInfo> addWristbandUsers = new ArrayList<WristbandInfo>();
            wristband.setId(YFPrimaryKeyUtils.getId(ServiceModeType.WRISTBAND));
            addWristbandUsers.add(wristband);
            wristbandUserDao.addWristbandUsers(addWristbandUsers);
        } else {
            wristband.setId(findWristbandUser.getId());
            List<WristbandInfo> updateWristbandUsers = new ArrayList<WristbandInfo>();
            updateWristbandUsers.add(wristband);
            wristbandUserDao.updateWristbandUsers(updateWristbandUsers);
        }
    }
}
