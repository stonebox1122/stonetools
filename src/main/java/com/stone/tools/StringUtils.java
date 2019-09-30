package com.stone.tools;

import java.math.BigInteger;

/**
 * @author stone
 * @date 2019/5/22 15:20
 * description 字符串工具类
 */
public class StringUtils {

    /**
     * 字符串首字母大写
     * @param str 传入字符串
     * @return 返回字符串
     */
    public static String initUpper(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(0, 1).toUpperCase().concat(str.substring(1));
    }

    /**
     * 字符串首字母小写
     * @param str 传入字符串
     * @return 返回字符串
     */
    public static String initLower(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes 字节数组
     * @param radix 进制
     * @return 字符串
     */
    public static String byteToStr(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);
    }
 }
