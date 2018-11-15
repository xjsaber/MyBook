package com.mmall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xjsaber
 */
public class MD5Util {

    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    /**
     * 返回大写MD5
     * @param origin
     * @param charsetName
     * @return
     */
    public static String MD5Encode(String origin, String charsetName){
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetName == null || "".endsWith(charsetName)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resultString.toUpperCase();
    }

    public static String MD5EncodeUTF8(String origin){
        origin = origin + PropertiesUtil.getProperty("password.salt");
        return MD5Encode(origin, "UTF-8");
    }

    private static final String HEX_DIGITS[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
