package com.example.lib;

import java.util.Arrays;

public class Printer {

    protected void print(Object o) {
        System.out.println(o);
    }

    protected void print(int[] arr) {
        print(Arrays.toString(arr));
    }
}
