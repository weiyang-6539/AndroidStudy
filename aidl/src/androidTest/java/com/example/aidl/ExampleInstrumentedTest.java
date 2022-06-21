package com.example.aidl;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        List<PhotoInfo> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new PhotoInfo(i));
        }

        System.out.println("初始集合:[" + list.get(0) + "," + list.get(list.size() - 1) + "]");

        List<PhotoInfo> list1 = get(list, 0, 800);
        System.out.println("点击item 0 取集合[" + list1.get(0) + "," + list1.get(list1.size() - 1) + "]");

        List<PhotoInfo> list2 = get(list, 50, 800);
        System.out.println("点击item 50 取集合[" + list2.get(0) + "," + list2.get(list2.size() - 1) + "]");

        List<PhotoInfo> list3 = get(list, 450, 800);
        System.out.println("点击item 450 取集合[" + list3.get(0) + "," + list3.get(list3.size() - 1) + "]");

        List<PhotoInfo> list4 = get(list, 900, 800);
        System.out.println("点击item 900 取集合[" + list4.get(0) + "," + list4.get(list4.size() - 1) + "]");

    }

    private List<PhotoInfo> get(List<PhotoInfo> photos, int position, int n) {
        List<PhotoInfo> rstList = new ArrayList<>();
        if (photos.size() <= n) {
            rstList.addAll(photos);
        } else {
            int index = 0;
            if (position + n / 2 >= photos.size()) {
                index = photos.size() - n;
            } else if (position - n / 2 >= 0) {
                index = position - n / 2;
            }

            rstList.addAll(photos.subList(index, n + index));
        }

        return rstList;
    }

    private static class PhotoInfo {
        private final int index;

        public PhotoInfo(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return index + "";
        }
    }
}