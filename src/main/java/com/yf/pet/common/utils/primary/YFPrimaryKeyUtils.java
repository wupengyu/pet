package com.yf.pet.common.utils.primary;

import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.exception.YFException;
import com.yf.pet.common.utils.YFResourceUtil;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 主键生成工具
 * 主键共64位: 2位(保留位) + 34位(时间戳) + 7位(机器码) + 7位(模块编号) + 14位(序列号)
 * Created by Infi on 17/5/9.
 */
public class YFPrimaryKeyUtils {
    private final static Logger log = LoggerFactory.getLogger(YFPrimaryKeyUtils.class);
    /**
     * 序列号占14位
     */
    private final static long SEQUENCE_BITS = NumberUtils.toLong(YFResourceUtil.getValueByKey("resource.properties", "sequence.bits"));
    /**
     * 模块号占7位
     */
    private final static long MODE_BITS = NumberUtils.toLong(YFResourceUtil.getValueByKey("resource.properties", "mode.bits"));
    /**
     * 机器码或者服务实例编号占7位
     */
    private final static long WORKER_ID_BITS = NumberUtils.toLong(YFResourceUtil.getValueByKey("resource.properties", "workerid.bits"));
    /**
     * 机器码或者服务实例编号配置(每台机器或者同一台机器上面的每个实例配置一个编号)
     */
    private final static long WORKER_ID = NumberUtils.toLong(YFResourceUtil.getValueByKey("resource.properties", "workerid"));

    /**
     * 模块号位移运算左移索引
     */
    private final static long modeLeftShift = SEQUENCE_BITS;
    /**
     * 机器码或者服务实例编号位移运算左移索引
     */
    private final static long workerLeftShift = SEQUENCE_BITS + MODE_BITS;
    /**
     * 时间戳位移运算左移索引
     */
    private final static long timestampLeftShift = SEQUENCE_BITS + MODE_BITS + WORKER_ID_BITS;

    /**
     * 序列号上限最大值(不能超过该值)
     */
    private final static long sequenceMask = -1L ^ (-1L << SEQUENCE_BITS);
//    private final static long sequenceMask = -1L ^ 16L;
    /**
     * 模块编号上限最大值(不能超过该值)
     */
    private final static long maxModeId = -1L ^ (-1L << MODE_BITS);
    /**
     * 机器码或者服务实例编号上限最大值(不能超过该值)
     */
    private final static long maxWorkerId = -1L ^ (-1L << WORKER_ID_BITS);

    /**
     * 初始化变量,下一个时间戳(秒)
     */
    private static long lastTimestampInSecond = -1L;

    /**
     * 每个模块的序列号,同意秒钟序列号累加,
     */
    private static Map<Integer, ModeSequence> sequenceMap = new ConcurrentHashMap<Integer, ModeSequence>();

    public static long getId(Integer modeId) {
        long timestampInSecond = timeGen();
        if (timestampInSecond < lastTimestampInSecond) {
            throw new YFException(ReturnMessageEnum.SYSTEM_ERROR);
        }
        ModeSequence modeSequence = sequenceMap.get(modeId);
        if (modeSequence == null) {
            modeSequence = new ModeSequence();
            modeSequence.setSequence(0);
            modeSequence.setTimestampInSecond(timestampInSecond);
            sequenceMap.put(modeId, modeSequence);
        } else if (lastTimestampInSecond == modeSequence.getTimestampInSecond()) {
            long sequence = (modeSequence.getSequence() + 1) & sequenceMask;
            if (sequence == 0) {
                timestampInSecond = tilNextMillis(lastTimestampInSecond);
                modeSequence.setTimestampInSecond(timestampInSecond);
                modeSequence.setSequence(0);
            } else {
                modeSequence.setSequence(modeSequence.getSequence() + 1);
            }
        } else {
            modeSequence.setTimestampInSecond(timestampInSecond);
            modeSequence.setSequence(0);
        }
        //TODO 测试使用 start
//        log.info(String.valueOf(timestampInSecond) + ":" + String.valueOf(modeSequence.getSequence()) + ":" + String.valueOf(modeId)
//                + ":" + String.valueOf((modeSequence.getTimestampInSecond() << timestampLeftShift) |
//                (WORKER_ID << workerLeftShift) |
//                (modeId << modeLeftShift) |
//                modeSequence.getSequence()));
//        TODO 测试使用 end

        lastTimestampInSecond = timestampInSecond;
        return (modeSequence.getTimestampInSecond() << timestampLeftShift) |
                (WORKER_ID << workerLeftShift) |
                (modeId << modeLeftShift) |
                modeSequence.getSequence();
    }

    /**
     * 等待下一秒时间戳(秒)
     *
     * @param lastTimestamp 时间戳(秒)
     * @return 返回下一秒的时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while ((timestamp <= lastTimestamp)) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取系统当前时间戳(秒)
     *
     * @return 返回时间戳(秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis() / 1000;
    }
}
