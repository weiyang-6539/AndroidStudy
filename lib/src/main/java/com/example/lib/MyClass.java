package com.example.lib;

import java.math.BigDecimal;
public class MyClass {
    static int num = 20;
    static String s1 = "1";
    static String s2 = "1";

    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            String rst = fun0(s1, s2);
            print((i + 1) + " - " + rst);

            s1 = s2;
            s2 = rst;
        }
    }

    private static String fun0(String s1, String s2) {
        return new BigDecimal(s1).add(new BigDecimal(s2)).toPlainString();
    }

    private static long fun(int m, int n) {
        if (m == 0 || n == 0)
            return 1;
        return fun(m - 1, n - 1) * (m + n) * (m + n - 1) / m / n;
    }

    private static void print(Object s) {
        System.out.println(s);
    }
}