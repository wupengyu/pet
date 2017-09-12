package com.yf.pet.common.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

/**
 * 远峰IOUtils工具类
 * Created by Infi on 17/5/11.
 */
public class YFIOUtils {
    /**
     * 读取Coros运动数据或者轨迹缩率图数据<br>
     * 数据格式如下:<br>
     * Head=UUID[16Byte]Mode[1Byte]Length[4Byte]<br>
     * Data=Count[2Byte]Head1…HeadnData1…Datan<br>
     *
     * @param sportData 运动数据
     * @return uuid对应的运动数据或者轨迹缩率图列表
     */
//    public static List<CorosSportDataImageData> readSportData(byte[] sportData) throws IOException {
//        List<CorosSportDataImageData> corosDatas = new ArrayList<CorosSportDataImageData>();
//        // 1. 读取文件数据内容并解析成算法需要的byte数组
//        ByteBuffer byteBuffer = ByteBuffer.wrap(sportData).order(ByteOrder.LITTLE_ENDIAN);
//        // 2. 读取Count[2Byte]
//        int count = (int) 0xff & byteBuffer.getShort();
//        if (count < 1) {
//            return null;
//        }
//        int indexCount = 2 + 21 * count;
//        int offset = indexCount;
//        for (int i = 0; i < count; i++) {
//            // 3. 读取UUID[16Byte]
//            byte[] UUIDBytes = new byte[16];
//            byteBuffer.get(UUIDBytes, 0, UUIDBytes.length);
////            String UUID = IOUtils.toString(UUIDBytes, "UTF-8");
//            String UUID = YFTools.toHexString(UUIDBytes);
////            System.arraycopy(byteBuffer.array(), offset, UUIDBytes, 0, UUIDBytes.length);
//            offset += 16;
//            // 4. 读取Mode[1Byte]
//            int mode = (int) 0xff & byteBuffer.get();
//            // 5. 读取数据长度Length[4Byte]
//            int dataLength = byteBuffer.getInt();
//            byte[] data = new byte[dataLength];
//            byteBuffer.get(data, 0, data.length);
////            System.arraycopy(byteBuffer.array(), offset, data, 0, data.length);
//            offset += dataLength;
//            CorosSportDataImageData corosSportDataImageData = new CorosSportDataImageData();
//            corosSportDataImageData.setUuid(UUID);
//            corosSportDataImageData.setMode(mode);
//            corosSportDataImageData.setData(data);
//            corosDatas.add(corosSportDataImageData);
//        }
//        return corosDatas;
//    }

    /**
     * 读取Coros运动数据或者轨迹缩率图数据<br>
     * 数据格式如下:<br>
     * Head=UUID[16Byte]Mode[1Byte]Length[4Byte]<br>
     * Data=Count[2Byte]Head1…HeadnData1…Datan<br>
     *
     * @param sportData 运动数据
     * @return uuid对应的运动数据或者轨迹缩率图列表
     */
//    public static List<CorosSportDataImageData> readImageDataAndSportData(byte[] sportData) throws IOException {
//        List<CorosSportDataImageData> corosDatas = new ArrayList<CorosSportDataImageData>();
//        // 1. 读取文件数据内容并解析成算法需要的byte数组
//        ByteBuffer byteBuffer = ByteBuffer.wrap(sportData).order(ByteOrder.LITTLE_ENDIAN);
//        // 2. 读取Count[2Byte]
//        int count = (int) 0xff & byteBuffer.getShort();
//        if (count < 1) {
//            return null;
//        }
////        int indexCount = 2 + 21 * count;
//        // 3. 获取运动数据的头部信息,UUID[16Byte]Mode[1Byte]Length[4Byte]
//        for (int i = 0; i < count; i++) {
//            // 3. 读取UUID[16Byte]
//            byte[] UUIDBytes = new byte[16];
//            byteBuffer.get(UUIDBytes, 0, UUIDBytes.length);
//            String UUID = YFTools.toHexString(UUIDBytes);
//            // 4. 读取Mode[1Byte]
//            int mode = (int) 0xff & byteBuffer.get();
//            // 5. 读取数据长度Length[4Byte]
//            int dataLength = byteBuffer.getInt();
//            CorosSportDataImageData sportDataImageData = new CorosSportDataImageData();
//            sportDataImageData.setUuid(UUID);
//            sportDataImageData.setMode(mode);
//            sportDataImageData.setDataLength(dataLength);
//            corosDatas.add(sportDataImageData);
//        }
//        // 4. 读取data的内容
//        for (int i = 0; i < count; i++) {
//            CorosSportDataImageData sportDataImageData = corosDatas.get(i);
//            int dataLength = sportDataImageData.getDataLength();
//            byte[] data = new byte[dataLength];
//            byteBuffer.get(data, 0, data.length);
//            sportDataImageData.setData(data);
//        }
//        return corosDatas;
//    }

    /**
     * weloop的轨迹解气方法
     *
     * @param sportData
     * @return
     * @throws IOException
     */
    public Map<String, byte[]> readSportData2(byte[] sportData) throws IOException {
        Map<String, byte[]> map = new HashMap<String, byte[]>();
        // 1. 读取文件数据内容并解析成算法需要的byte数组
        ByteBuffer byteBuffer = ByteBuffer.wrap(sportData).order(ByteOrder.LITTLE_ENDIAN);
        int count = (int) 0xff & byteBuffer.getShort();
        if (count < 1) {
            return map;
        }
        int indexCount = 2 + 21 * count;
        int offset = indexCount;
        for (int i = 0; i < count; i++) {
            byte[] byteUuid = new byte[16];
            byteBuffer.get(byteUuid);
            String labelUuid = YFTools.toHexString(byteUuid).toLowerCase();
            int mode = (int) 0xff & byteBuffer.get();
            int byteCount = byteBuffer.getInt();
            byte[] data = new byte[byteCount];
            System.arraycopy(byteBuffer.array(), offset, data, 0, data.length);
            map.put(labelUuid, data);
            offset += byteCount;
        }
        return map;
    }
}
