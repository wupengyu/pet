package com.yf.pet.common.utils.aes;

import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.exception.YFException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.CryptoException;
import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.JNCryptor;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

/**
 * 通过AES算法对文本进行加密解密
 *
 * @author xuming 2015-03-03
 */
public class AESUtil {

    public static JNCryptor cryptor = new AES256JNCryptor(100);

    private static final Logger log = Logger.getLogger(AESUtil.class.getClass());
    private static Cipher cipher; // 加密算法
    private static String ARITHMETIC = "AES";// 加密算法
    private static String CIPHER_ARITHMETIC = "AES/CBC/PKCS5Padding";// "算法/模式/补码方式"
    private static String IV_PARAMETER = "weloop3_2015_03#";// 使用CBC模式，需要一个向量iv，可增加加密算法的强度

    private static String CONTENT_TYPE = "UTF-8";

    /**
     * aes 加密
     *
     * @param content 加密对象
     * @param strKey  密钥
     * @return 解密后的对象
     */
    public static String encrypt(String content, String strKey) {

        if (strKey == null) {
            log.info("strKey为空");
            return null;
        }
        // 判断Key是否为16位
        if (strKey.length() != 16) {
            log.info("strKey长度不是16位");
            return null;
        }

        try {
            byte[] byteKey = strKey.getBytes(CONTENT_TYPE);
            SecretKeySpec skeySpec = new SecretKeySpec(byteKey, ARITHMETIC);// 算法
            cipher = Cipher.getInstance(CIPHER_ARITHMETIC);// "算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes(CONTENT_TYPE));
            return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
    }

    /**
     * aes 解密
     *
     * @param content 解密对象
     * @param strKey  密钥
     * @return 解密后的对象
     */
    public static String decrypt(String content, String strKey) {

        if (strKey == null) {
            log.info("strKey为空");
            return null;
        }
        // 判断Key是否为16位
        if (strKey.length() != 16) {
            log.info("strKey长度不是16位");
            return null;
        }
        try {
            byte[] byteKey = strKey.getBytes(CONTENT_TYPE);
            SecretKeySpec skeySpec = new SecretKeySpec(byteKey, ARITHMETIC);
            cipher = Cipher.getInstance(CIPHER_ARITHMETIC);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted = new Base64().decode(content);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original, CONTENT_TYPE);
            return originalString;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new YFException(ReturnMessageEnum.PARAMETER_ERROR);
        }
    }

    /**
     * 加密
     *
     * @param plaintext
     * @param password
     * @return
     * @throws CryptoException
     */
    public static String encryptData(String plaintext, String password) {
        byte[] ciphertext = null;
        try {
            ciphertext = cryptor.encryptData(plaintext.getBytes(), password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Hex.encodeHexString(ciphertext);
    }

    public static String decryptData(String plaintext, String password) {
        byte[] ciphertext = null;
        try {
            ciphertext = cryptor.decryptData(Hex.decodeHex(plaintext.toCharArray()), password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(ciphertext);
    }

    /**
     * 对原始字符串先进行异或运算
     *
     * @param initStr 被运算的字符
     * @param strKey  密钥
     * @return 运算之后的字符
     */
    public static String encrypt4InitStr(String initStr, String strKey) {
        byte[] data = initStr.getBytes();
        byte[] keyData = strKey.getBytes();
        int keyIndex = 0;
        for (int x = 0; x < initStr.length(); x++) {
            data[x] = (byte) (data[x] ^ keyData[keyIndex]);
            if (++keyIndex == keyData.length) {
                keyIndex = 0;
            }
        }
        return new String(data);
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static String createClientLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        return StringUtils.join(keys, ".");
    }

    public static String createClientLinkString2(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        return StringUtils.join(keys, ".");
    }

    public static String clientSign(String text, String key) throws Exception {
        text = text + key + text;
        return DigestUtils.md5Hex(text.getBytes("utf-8"));
    }

    public static byte[] toBytes(String hexString) {
        int size = hexString.length() / 2;
        byte[] buf = new byte[size];
        int index = 0;

        for (; index < size; ++index) {
            int value = Integer.valueOf(hexString.substring(index * 2, index * 2 + 2), 16);
            buf[index] = (byte) value;
        }
        return buf;
    }

    static public Date decodeBongTime(byte[] bytes) {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.BIG_ENDIAN);

        int time = 0xffffffff & bb.getInt();

        calendar.set(Calendar.MONTH, ((time >> 28) & 0xf) - 1);
        calendar.set(Calendar.DATE, (time >> 23) & 0x1f);
        calendar.set(Calendar.HOUR_OF_DAY, (time >> 18) & 0x1f);
        calendar.set(Calendar.MINUTE, (time >> 12) & 0x3f);
        calendar.set(Calendar.YEAR, 2000 + ((time >> 4) & 0x3f));
        return calendar.getTime();
    }

//    static public int getstep0(byte[] bytes, byte[] v1) {
//        ByteBuffer bb = ByteBuffer.wrap(bytes);
//        bb.order(ByteOrder.BIG_ENDIAN);
//
//        ByteBuffer b1 = ByteBuffer.wrap(v1);
//        b1.order(ByteOrder.BIG_ENDIAN);
//        int step = 0xffffffff & bb.getInt();
//        int value0 = 0xffffffff & b1.getInt();
//
//        int a = step >> 27 & 0x1f;
//        int b = step >> 22 & 0x1f;
//        int c = step >> 17 & 0x1f;
//        int d = step >> 12 & 0x1f;
//        int e = step >> 7 & 0x1f;
//        int f = (step & 0x7f) | ((value0 >> 4) & 0x80); // | ((step << 3) &
//        // 0x80);
//        // System.out.println(f);
//        return f;
//    }

//    static public int getstep1(byte[] bytes, byte[] v1) {
//        ByteBuffer bb = ByteBuffer.wrap(bytes);
//        bb.order(ByteOrder.BIG_ENDIAN);
//        ByteBuffer b1 = ByteBuffer.wrap(v1);
//        b1.order(ByteOrder.BIG_ENDIAN);
//        int step = 0xffffffff & bb.getInt();
//        int value0 = 0xffffffff & b1.getInt();
//        int a = step >> 27 & 0x1f;
//        int b = step >> 22 & 0x1f;
//        int c = step >> 17 & 0x1f;
//        int d = step >> 12 & 0x1f;
//        int e = step >> 7 & 0x1f;
//        int f = step & 0x7f | ((value0 >> 3) & 0x80); // (step & 0x7f) | ((step
//        // << 3) & 0x80);
//        // System.out.println(f);
//        return f;
//    }

    /**
     * 先使用 AESUtil.decrypt 方法解密,再使用AESUtil.encrypt4InitStr 异或运算解密
     *
     * @param content 加密后的字符串
     * @param appKey  解密密钥
     * @return 解密以后的内容
     */
    public static String decryptAndXOR(String content, String appKey) {
        content = decrypt(content, appKey);
        return encrypt4InitStr(content, appKey);
    }
}
