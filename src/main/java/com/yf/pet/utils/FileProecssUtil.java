package com.yf.pet.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * @author infi
 */
public class FileProecssUtil {
    private static Logger logger = LoggerFactory.getLogger(FileProecssUtil.class);

    public static void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 将byte[]保存到文件
     *
     * @param fileData 文件数据
     * @param fileName 文件名
     * @param filePath 文件路径
     */
    public static File saveLocalFile(byte[] fileData, String fileName, String filePath) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                logger.warn("1.目录不存在,准备创建" + filePath);
                dir.mkdirs();
            }
            file = new File(dir, fileName);
            if (!file.getParentFile().exists()) {
                logger.warn("2.目录不存在,再次创建" + file.getParentFile().getAbsolutePath());
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fileData);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            closeStream(bos);
            closeStream(fos);
        }
        return file;
    }

    public static void saveUploadFile(YFEnumFileKey fileKey, InputStream fileStream, String fileName, String filePath, String parentPath) {
        filePath = filePath.replace("//", "/").replace("\\\\", "\\");
        writeLocalFile(fileKey, fileStream, fileName, filePath, parentPath);
    }


    public static void saveUploadFile(YFEnumFileKey fileKey, InputStream inputStream, String fileName, String filePath) {
        saveUploadFile(fileKey, inputStream, fileName, filePath, "");
    }

    /**
     * 将文件写到本地存储器
     *
     * @param fileKey:文件类型
     * @param inputStream:文件流
     * @param filePath:文件目录，结尾需要带斜线
     * @param parentPath:文件父目录
     */
    public static void writeLocalFile(YFEnumFileKey fileKey, InputStream inputStream, String fileName, String filePath, String parentPath) {
        try {
            String fullName = filePath + fileName;
            File file = new File(fullName);
            // 判断目标文件所在的目录是否存在
            if (!file.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                logger.warn("目标文件所在目录不存在，准备创建它！" + fullName);
                if (!file.getParentFile().mkdirs()) {
                    logger.error("创建目标文件所在目录失败！" + fullName);
                }
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            OutputStream bufferStream = new BufferedOutputStream(outputStream);

            byte[] buffer = new byte[ApplicationConstants.BUFFER_SIZE];
            for (int byteRead = 0; (byteRead = inputStream.read(buffer)) > 0; ) {
                bufferStream.write(buffer, 0, byteRead);
            }
            closeStream(bufferStream);
            closeStream(inputStream);
            closeStream(outputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 保存byte到文件
     *
     * @param fileKey   文件类型
     * @param fileBytes 文件的bytes数据
     * @param fileName  文件名称
     * @param filePath  文件路径
     * @author infi
     */
    public static void saveUploadFileBytes(YFEnumFileKey fileKey, byte[] fileBytes, String fileName, String filePath) {
        filePath = filePath.replace("//", "/").replace("\\\\", "\\");
        writeLocalFileBytes(fileKey, fileBytes, fileName, filePath);
    }

    /**
     * 将文件写到本地存储器
     *
     * @param fileKey   文件类型
     * @param fileBytes 文件Bytes
     * @param fileName  文件名
     * @param filePath  文件目录，结尾需要带斜线
     * @author infi
     */
    public static void writeLocalFileBytes(YFEnumFileKey fileKey, byte[] fileBytes, String fileName, String filePath) {
        try {
            String fullName = filePath + fileName;
            File file = new File(fullName);
            // 判断目标文件所在的目录是否存在
            if (!file.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                logger.warn("目标文件所在目录不存在，准备创建它！" + fullName);
                if (!file.getParentFile().mkdirs()) {
                    logger.error("创建目标文件所在目录失败！" + fullName);
                }
            }
            FileUtils.writeByteArrayToFile(file, fileBytes);
        } catch (Exception e) {
            logger.error("写文件出错,错误信息:" + e.getMessage());
        }
    }

    public static boolean closeStream(Closeable stream) {
        try {
            if (!YFTools.isEmpty(stream)) {
                stream.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }


    private static int k = 1; // 定义递归次数变量


}
