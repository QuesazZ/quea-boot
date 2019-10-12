package com.quesa.mybootproject.common.util;

import java.io.IOException;

/**
 *
 */
public class Base64Encrypt {
    /**
     * 编码
     *
     * @param bstr
     * @return String
     */
    public static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * 编码
     *
     * @param str
     * @return String
     */
    public static String encode(String str) {
        try {
            return new sun.misc.BASE64Encoder().encode(str.getBytes("utf-8"));
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bt;
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static String decodeToString(String str) {
        String value = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            value = new String(decoder.decodeBuffer(str), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * base64 自定义key,编码
     *
     * @param str
     * @param firstKey
     * @param secondKey
     * @return
     */
    public static String encodeSalt(String str, String firstKey, String secondKey) {
        StringBuffer sb = new StringBuffer(encode(str));
        sb.insert(0, createKey(firstKey));
        sb.append(createKey(secondKey));
        return encode(sb.toString());
    }

    /**
     * base64 自定义key,解码
     *
     * @param str
     * @return string
     */
    public static String decodeSalt(String str, String firstKey, String secondKey) {
        String value = decodeToString(str);
        value = value.replace(createKey(firstKey), "").replace(createKey(secondKey), "");
        return decodeToString(value);
    }

    /**
     * 创建秘钥
     *
     * @param key
     * @return
     */
    private static String createKey(String key) {
        return encode(encode(encode(key))).replaceAll("\r|\n", "").replaceAll("=", "");
    }
}
