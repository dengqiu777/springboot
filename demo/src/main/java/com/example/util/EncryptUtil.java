package com.example.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author xiong.qiu
 * @create 2017-11-03 18:06
 **/
public class EncryptUtil {
    public static String md5(String text) {
        return md5(text, "utf-8");
    }

    public static String md5(String text, String encoding) {
        char[] hexDigits =
                {
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
                };

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] input = text.getBytes(encoding);

            md.update(input);
            byte[] tmp = md.digest();
            char[] str = new char[32];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte b = tmp[i];
                str[k++] = hexDigits[b >>> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            String encrypt = new String(str);
            return encrypt;
        } catch (NoSuchAlgorithmException e) {

        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    public static void main(String[] arg) {
        System.out.println(md5("alice"+"123456" ));
        String a = "a";
        String b = "b";
        String c = a+"|"+b;
        System.out.println(c);
    }
}
