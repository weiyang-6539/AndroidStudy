package com.wyang.study.encrypt;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RC4Util {
    public static String getKey() {
        char[] k = new char[16];
        Random r = new Random();
        for (int i = 0; i < 16; i++) {
            k[i] = (char) ('a' + r.nextInt() % 26);
        }
        return new String(k);
    }

    public static String arc4(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("ARC4");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "ECB"));
            return new String(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String HloveyRC4(String aInput, String aKey) {
        int[] iS = new int[256];
        byte[] iK = new byte[256];

        // 1.1 KSA--密钥调度算法--利用key来对S盒做一个置换，也就是对S盒重新排列
        for (int i = 0; i < 256; i++)
            iS[i] = i;

        int j;

        for (short i = 0; i < 256; i++) {
            iK[i] = (byte) aKey.charAt((i % aKey.length()));
        }

        j = 0;

        for (int i = 0; i < 256; i++) {
            j = (j + iS[i] + iK[i]) % 256;
            int temp = iS[i];
            iS[i] = iS[j];
            iS[j] = temp;
        }

        // 1.2 RPGA--伪随机生成算法--利用上面重新排列的S盒来产生任意长度的密钥流
        int i = 0;
        j = 0;
        char[] iInputChar = aInput.toCharArray();
        char[] iOutputChar = new char[iInputChar.length];
        for (short x = 0; x < iInputChar.length; x++) {
            i = (i + 1) % 256;
            j = (j + iS[i]) % 256;
            int temp = iS[i];
            iS[i] = iS[j];
            iS[j] = temp;
            int t = (iS[i] + (iS[j] % 256)) % 256;
            int iY = iS[t];
            char iCY = (char) iY;
            iOutputChar[x] = (char) (iInputChar[x] ^ iCY);
        }
        return new String(iOutputChar);
    }

    public static String rc4_trans(String keys, String encrypt) {
        char[] keyBytes = new char[256];
        char[] cypherBytes = new char[256];

        for (int i = 0; i < 256; ++i) {
            keyBytes[i] = keys.charAt(i % keys.length());
            cypherBytes[i] = (char) i;
        }

        int jump = 0;
        for (int i = 0; i < 256; ++i) {
            jump = (jump + cypherBytes[i] + keyBytes[i]) & 0xFF;
            char tmp = cypherBytes[i]; // Swap:
            cypherBytes[i] = cypherBytes[jump];
            cypherBytes[jump] = tmp;
        }

        int i = 0;
        jump = 0;
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < encrypt.length(); ++x) {
            i = (i + 1) & 0xFF;
            char tmp = cypherBytes[i];
            jump = (jump + tmp) & 0xFF;
            char t = (char) ((tmp + cypherBytes[jump]) & 0xFF);
            cypherBytes[i] = cypherBytes[jump]; // Swap:
            cypherBytes[jump] = tmp;

            try {
                result.append((char) (encrypt.charAt(x) ^ cypherBytes[t]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
