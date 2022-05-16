package com.example.lib;

public class MyClass {

    public static void main(String[] args) {
        int rst = 0;
        int rst2 = 0;
        for (int i = 0; i < 9999; i++) {
            String s = String.valueOf(i);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '7')
                    rst++;
            }
            if (s.contains("7"))
                rst2++;
        }
        System.out.println(rst);
        print(rst2+"");
    }

    private static void print(String s) {
        System.out.println(s);
    }

}