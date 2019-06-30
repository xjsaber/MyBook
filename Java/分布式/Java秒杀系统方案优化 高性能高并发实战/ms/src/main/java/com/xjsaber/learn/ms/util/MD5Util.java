package com.xjsaber.learn.ms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5 工具类
 * @author xjsaber
 */
public class MD5Util {

    private final String salt = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public String inputPassword(String pass){
        return md5(salt.charAt(1) + salt.charAt(2) + pass + salt.charAt(5) + salt.charAt(4));
    }
}
