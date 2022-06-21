package com.example.lib.sort;

import com.example.lib.Printer;
import com.example.lib.helper.Helper;

public class SortDemo extends Printer {

    public void execute() {
        int[] arr1 = Helper.randomIntArray(100, 1, 100);
        int[] arr2 = new int[100];
        int[] arr3 = new int[100];

        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
        System.arraycopy(arr1, 0, arr3, 0, arr1.length);

        long t1 = System.nanoTime();
        shellSort(arr1);
        print("希尔排序:" + (System.nanoTime() - t1));
        print(arr1);

        long t2 = System.nanoTime();
        bubbleSort(arr2);
        print("冒泡排序:" + (System.nanoTime() - t2));
        print(arr2);

        long t3 = System.nanoTime();
        insertSort(arr3);
        print("插入排序:" + (System.nanoTime() - t3));
        print(arr3);

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 希尔排序
     */
    private void shellSort(int[] arr) {
        int j;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                for (j = i; j >= gap && tmp <= arr[j - gap]; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }

    /**
     * 冒泡排序
     */
    private void bubbleSort(int[] arr) {
        int j;
        for (int i = 0; i < arr.length - 1; i++) {
            for (j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }
}
