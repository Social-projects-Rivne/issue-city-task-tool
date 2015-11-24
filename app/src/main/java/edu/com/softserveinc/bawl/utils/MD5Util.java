package edu.com.softserveinc.bawl.utils;

import org.apache.commons.codec.digest.DigestUtils;

/* DigestUtils is waste class allows you to encrypt data in MD5 and other encryption types.*/

public class MD5Util {
    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);

        return md5Hex;
    }
}
