package com.example.lib.helper;

import java.util.Random;

public class Helper {

    /**
     * 生成一个取值区间为[start,end]的随机数, 前闭后闭
     */
    public static int randomInt(int start, int end) {
        if (start >= end)
            throw new IllegalArgumentException();

        return new Random().nextInt(end - start + 1) + start;
    }

    /**
     * 生成一个大小为n,元素取值区间为[start,end]的随机数组
     */
    public static int[] randomIntArray(int n, int start, int end) {
        if (start >= end)
            throw new IllegalArgumentException();

        int[] resultArr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            resultArr[i] = random.nextInt(end - start + 1) + start;
        }

        return resultArr;
    }
}
