package com.wyang.study.encrypt;

import android.util.Base64;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by weiyang on 2017/12/21.
 */
public class AESUtils {
    private static final String aiv = "0000000000000000";

    private AESUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 生产密钥字符串
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generatePrivateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);  //192 256
            SecretKey secretKey = keyGen.generateKey();
            return byteToHexString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * byte数组转化为16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return null;
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String strHex = Integer.toHexString(aByte & 0xff);
            if (strHex.length() < 2) {
                sb.append(0);
            }
            sb.append(strHex);
        }
        return sb.toString();
    }

    /**
     * 字符串加密
     *
     * @param content
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String privateKeyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        int blockSize = cipher.getBlockSize();
        byte[] dataBytes = content.getBytes("utf-8");
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        SecretKeySpec keySpec = new SecretKeySpec(privateKeyStr.getBytes("utf-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(aiv.getBytes("utf-8"));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(plaintext);
        return Base64.encodeToString(encrypted, Base64.DEFAULT);
    }

    /**
     * 字符串解密
     *
     * @param encryptedData
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData, String privateKeyStr) throws Exception {
        byte[] bytes = Base64.decode(encryptedData, Base64.DEFAULT);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(privateKeyStr.getBytes("utf-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(aiv.getBytes("utf-8"));

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] original = cipher.doFinal(bytes);
        return new String(original).trim();
    }
}
