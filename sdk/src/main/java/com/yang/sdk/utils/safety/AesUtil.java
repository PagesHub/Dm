package com.yang.sdk.utils.safety;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Describe:  Aes加密工具
 * Created by Yang on 2019/4/15.
 */
public class AesUtil {
    //-- 算法/模式/填充
    private static final String CipherMode = "AES/CBC/PKCS7Padding";

    //--创建密钥
    private static SecretKeySpec createKey(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(password);
        String s = null;
        while (sb.length() < 32) {
            sb.append(" ");//--密码长度不够32补足到32
        }
        s = sb.substring(0, 32);//--截取32位密码
        data = s.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(data, "AES");
    }

    //--创建偏移量
    private static IvParameterSpec createIV(String iv) {
        byte[] data = null;
        if (iv == null) {
            iv = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(iv);
        String s = null;
        while (sb.length() < 16) {
            sb.append(" ");//--偏移量长度不够16补足到16
        }
        s = sb.substring(0, 16);//--截取16位偏移量
        data = s.getBytes(StandardCharsets.UTF_8);
        return new IvParameterSpec(data);
    }

    //--加密字节数组到字节数组
    private static byte[] encryptByte2Byte(byte[] content) {
        try {
            //SecretKeySpec key = createKey(password);
            SecretKeySpec key = new SecretKeySpec("abcdefghijklmnop".getBytes(StandardCharsets.UTF_8),"AES");
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key, createIV("1234567890123456"));
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //--加密字节数组到字符串
    public static String encryptByte2String(byte[] content) {
        byte[] data = encryptByte2Byte(content);
        return new String(data);
    }

    //--加密字节数组到base64
    public static String encryptByte2Base64(byte[] content) {
        byte[] data = encryptByte2Byte(content);
        return new String(Base64.encode(data, Base64.DEFAULT));
    }

    //--加密字符串到字节数组
    public static byte[] encryptString2Byte(String content) {
        byte[] data = null;
        try {
            data = content.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encryptByte2Byte(data);
        return data;
    }

    //--加密字符串到字符串
    public static String encryptString2String(String content) {
        byte[] data = null;
        try {
            data = content.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encryptByte2Byte(data);
        return new String(data);
    }

    //--加密字符串到base64
    public static String encryptString2Base64(String content) {
        byte[] data = null;
        try {
            data = content.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encryptByte2Byte(data);
        return new String(Base64.encode(data, Base64.DEFAULT));
    }

    //-- 解密字节数组到字节数组
    private static byte[] decryptByte2Byte(byte[] content) {
        try {
            // SecretKeySpec key = createKey(password);
            SecretKeySpec key = new SecretKeySpec("abcdefghijklmnop".getBytes(StandardCharsets.UTF_8),"AES");
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key, createIV("1234567890123456"));
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //--解密字符串到字节数组
    public static byte[] decryptString2Byte(String content, String password, String iv) {
        byte[] data = null;
        try {
            data = content.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decryptByte2Byte(data);
        return data;
    }

    //--解密base64到字节数组
    public static byte[] decryptBase642Byte(String content, String password, String iv) {
        byte[] data = null;
        try {
            data = Base64.decode(content, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decryptByte2Byte(data);
        return data;
    }

    //-- 解密字节数组到字符串
    private static String decryptByte2String(byte[] content) {
        byte[] data = decryptByte2Byte(content);
        return new String(data);
    }

    //-- 解密字节数组到字符串
    public static String decryptBase642String(String content) {
        byte[] data = Base64.decode(content, Base64.DEFAULT);
        return decryptByte2String(data);
    }
}
