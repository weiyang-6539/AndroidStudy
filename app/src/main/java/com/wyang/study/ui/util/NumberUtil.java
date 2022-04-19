package com.wyang.study.ui.util;

public class NumberUtil {
    public static int hex2dec(String hex) {
        if (hex.startsWith("0X") || hex.startsWith("0x"))
            hex = hex.substring(2);
        return (short) Integer.parseInt(hex, 16);
    }

    public static void main(String[] args){
        System.out.println(hex2dec("FFFE"));
        System.out.println(hex2dec("ffff"));
    }
}
