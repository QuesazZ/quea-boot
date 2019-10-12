package com.quesa.mybootproject.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * 标准JCE实现常用的摘要算法。
 */
public class JCEDigest {
    private static MessageDigest ALG_MD5;
    private static MessageDigest ALG_SHA1;
    private static MessageDigest ALG_SHA256;

    private static final Logger log = LoggerFactory.getLogger(JCEDigest.class);

    public final static char[] HEX_digits = new char[]{ //
            '0', '1', '2', '3', //
            '4', '5', '6', '7', //
            '8', '9', 'a', 'b', //
            'c', 'd', 'e', 'f'};

    static {
        try {
            ALG_MD5 = MessageDigest.getInstance("MD5");
            ALG_SHA1 = MessageDigest.getInstance("SHA-1");
            ALG_SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private JCEDigest() {
    }

    public static String toMD5(String content) {
        if (content == null) {
            content = "";
        }
        return toMD5(content.getBytes());
    }


    public static String SHA1(String content) {
        if (content == null) {
            content = "";
        }
        return toSHA1(content.getBytes());
    }

    public static String toSHA256(String content) {
        if (content == null) {
            content = "";
        }
        return toSHA256(content.getBytes());
    }

    public static String toMD5(byte[] content) {
        return doMesgDigest(ALG_MD5, content);
    }

    public static String toSHA1(byte[] content) {
        return doMesgDigest(ALG_SHA1, content);
    }

    public static String toSHA256(byte[] content) {
        return doMesgDigest(ALG_SHA256, content);
    }

    public static String doMesgDigest(MessageDigest digestAlg, byte[] content) {
        if (content == null) {
            content = new byte[0];
        }
        return toHexString(digestAlg.digest(content));
    }

    public static String toHexString(byte[] hex) {
        StringBuffer hexValue = new StringBuffer(64);
        for (int i = 0; i < hex.length; i++) {
            hexValue.append(HEX_digits[0xf & (hex[i] >> 4)]);
            hexValue.append(HEX_digits[0xf & hex[i]]);
        }
        return hexValue.toString();
    }
}
