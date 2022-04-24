package com.example.lib;

public class MyClass {

    public static void main(String[] args) {
        String str = "abc";

        print(str.intern());
    }

    private static void print(String s) {
        System.out.println(s);
    }

}