package com.example.lib;

public class MyClass {

    private static String[] sArr = {"I", "V", "X", "L", "C", "D", "M"};

    public static void main(String[] args) {
        int num = 10;

        String s = f("", num, 0);
        print(s);
    }

    public static String f(String str, int num, int i) {
        int n = num % 10;
        if (n == 4) {
            str = sArr[i] + sArr[i + 1] + str;
        } else if (n == 9) {
            str = sArr[i] + sArr[i + 2] + str;
        } else {
            print("n = " + n);
            for (int j = 0; j < n % 5; j++) {
                str = sArr[i] + str;
            }
            if (n >= 5) {
                str = sArr[i + 1] + str;
            }
        }

        if (num >= 10) {
            num /= 10;
            print("num = " + num);
            return f(str, num, i + 2);
        }
        return str;
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