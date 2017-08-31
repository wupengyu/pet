package com.yf.pet.utils.cache;

import com.yf.pet.utils.YFResourceUtil;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 团队信息缓存:
 * key:团队ID
 * value:团队信息最后更新时间,团队队员地理位置信息缓存
 * 背景:
 * 1. 用户上传地理位置,如果没有发现团队缓存,就创建团队缓存,并且把团队中所有用户加入到缓存中
 * 2. 如果团队缓存存在,就更新用户本人的缓存数据
 * 3. 团队过期以后,把过期的团队缓存持久化到数据库团队用户信息表(t_bike_team_user)
 * TODO 团队缓存定时刷新缓存,然后把数据持久化到mysql。定义定时刷新的频率和业务逻辑(这个工作放在后面做)
 * Created by Infi on 17/5/17.
 */

public class YFTeamCache {

    private static final Logger log = LoggerFactory.getLogger(YFTeamCache.class);

    /**
     * 缓存最大容量
     */
    private static final long CACHE_MAXIMUM_SIZE = NumberUtils.toLong(YFResourceUtil.getValueByKey("app-coros.properties", "cache.maximum.size"));
    /**
     * 缓存过期时间,10小时过期
     */
    private static final long CACHE_EXPIRE_TIME_SECONDS = NumberUtils.toLong(YFResourceUtil.getValueByKey("app-coros.properties", "cache.expire.time.seconds"));

//    private static Cache<Object, Object> teamCache = CacheBuilder.newBuilder()
//            .maximumSize(CACHE_MAXIMUM_SIZE)
//            .expireAfterWrite(CACHE_EXPIRE_TIME_SECONDS, TimeUnit.SECONDS)
//            .recordStats()
//            //设置缓存的移除通知
//            .removalListener(new RemovalListener<Object, Object>() {
//                @Override
//                public void onRemoval(RemovalNotification<Object, Object> notification) {
//                    //回收原因分为：
//                    //a. EXPLICIT 手动回收
//                    //b. REPLACED被替换，如put，refresh
//                    //c. COLLECTED 被gc（软引用，弱引用）
//                    //d. EXPIRED 过期
//                    //e. SIZE 超过大小
//
//                    //1. 缓存被替换或刷新是不做持久化操作,持久化到数据库
//                    if (notification != null
//                            && !RemovalCause.REPLACED.equals(notification.getCause())
//                            && notification.getValue() != null) {
//                        HashMap<Integer, BikeTeamUser> bikeTeamUserHashMap = (HashMap<Integer, BikeTeamUser>) notification.getValue();
//                        List<BikeTeamUser> updateBikeTeamUsers = new ArrayList<BikeTeamUser>();
//                        for (BikeTeamUser bikeTeamUser : bikeTeamUserHashMap.values()) {
//                            if (!bikeTeamUser.getIsExit()) {
//                                updateBikeTeamUsers.add(bikeTeamUser);
//                            }
//                        }
//                        if (updateBikeTeamUsers.size() > 0) {
//                            TeamServiceUtils.getTeamServiceUtils().getBikeTeamUserService().updateBatchTeamUserByUserId(updateBikeTeamUsers);
//                        }
//                    }
//                }
//            })
//            .build();


    /**
     * 获得团队缓存数据
     *
     * @param key          团队ID
     * @param bikeTeamUser 用户缓存数据
     * @return 团队缓存数据, 包含了团队中所有队员的缓存数据
     */
//    public static Object getTeamCache(final long key, final BikeTeamUser bikeTeamUser) {
//        try {
//
//            Object var = teamCache.get(key, new Callable<Object>() {
//                // 以下是诶呦获取到缓存对象的操作
//                @Override
//                public Object call() throws Exception {
//                    //1. 如果是用户退出时还没有缓存,就不创建缓存
//                    if (bikeTeamUser.getIsExit()) {
//                        return null;
//                    }
//
//                    //1. 查询团队最后的更新时间,团队ID错误的话,就抛出异常
//                    Long teamUpdateTime = TeamServiceUtils.getTeamServiceUtils().getBikeTeamService().findTeamUpdateTime(key);
//                    if (teamUpdateTime == null || teamUpdateTime == 0) {
//                        throw new YFException(ReturnMessageEnum.USER_NOT_JOIN_TEAM);
//                    }
//                    //2. 更新用户的地理位置信息到数据库
//                    TeamServiceUtils.getTeamServiceUtils().getBikeTeamUserService().updateTeamUserSharingData(key, bikeTeamUser.getUserId(), bikeTeamUser.getSharingData());
//
//                    //3. 查询团队所有用户的信息
//                    List<BikeTeamUser> bikeTeamUsers = TeamServiceUtils.getTeamServiceUtils().getBikeTeamUserService().findTeamUserAll(key);
//                    //4. 查询用户昵称和头像
//                    List<Long> userIds = new ArrayList<Long>();
//                    for (BikeTeamUser teamUser : bikeTeamUsers) {
//                        userIds.add(teamUser.getUserId());
//                    }
//                    List<UserInfo> userInfos = TeamServiceUtils.getTeamServiceUtils().getUserInfoService().findSimpleUserInfoListByUserId(userIds);
//
//                    //5. 构建用户缓存信息
//                    HashMap<Long, BikeTeamUser> bikeTeamUserMap = new HashMap<Long, BikeTeamUser>();
//                    for (BikeTeamUser teamUser : bikeTeamUsers) {
//                        for (UserInfo userInfo : userInfos) {
//                            if (teamUser.getUserId().equals(userInfo.getUserId())) {
//                                teamUser.setNickname(userInfo.getNickname());
//                                teamUser.setHeadPic(userInfo.getHeadPic());
//                                break;
//                            }
//                        }
//                        bikeTeamUserMap.put(teamUser.getUserId(), teamUser);
//                    }
//                    // 4. 构建团队缓存
//                    BikeTeamCache bikeTeamCache = new BikeTeamCache();
//                    bikeTeamCache.setTeamId(key);
//                    bikeTeamCache.setUserDataMap(bikeTeamUserMap);
//                    bikeTeamCache.setTeamUpdateTime(teamUpdateTime);
//                    return bikeTeamCache;
//                }
//            });
//            return var;
//        } catch (ExecutionException e) {
////            e.printStackTrace();
//            log.error("创建缓存异常", e);
//            return null;
//        } catch (CacheLoader.InvalidCacheLoadException e) {
//            // 5. 不需要创建缓存,返回null值时会抛异常,此时拦截异常,保证正常的业务逻辑
//            log.info("不创建缓存,返回null,Guava Cache抛出正常的业务异常");
////            e.printStackTrace();
//            return null;
//        } catch (Exception e) {
//            log.error("缓存异常");
//            return null;
//        }
//    }

    /**
     * 写入缓存
     *
     * @param key   teamId
     * @param value 缓存对象
     */
//    public static void put(Object key, Object value) {
//        teamCache.put(key, value);
//    }


}