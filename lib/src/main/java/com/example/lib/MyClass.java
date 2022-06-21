package com.example.lib;


import java.util.ArrayList;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {
        //new SortDemo().execute();
        System.out.println(fun("ylqpejqbalahwr", "yrkzavgdmdgtqpg"));
    }

    private static int fun(String text1, String text2) {
        StringBuilder builder1 = new StringBuilder();
        for (int i = 0; i < text1.length(); i++) {
            char c = text1.charAt(i);
            if (text2.contains(c + ""))
                builder1.append(c);
        }

        StringBuilder builder2 = new StringBuilder();
        for (int i = 0; i < text2.length(); i++) {
            char c = text2.charAt(i);
            if (text1.contains(c + ""))
                builder2.append(c);
        }

        text1 = builder1.toString();
        text2 = builder2.toString();
        System.out.println(text1);
        System.out.println(text2);
        if (text1.isEmpty() || text2.isEmpty())
            return 0;

        if (text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        if (text1.contains(text2))
            return text2.length();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < text2.length(); i++) {
            for (int j = 0; j < text1.length(); j++) {
                char cJ = text1.charAt(j);
                char cI = text2.charAt(i);
                if (cJ == cI) {
                    list.add(j);
                    break;
                }
            }
        }

        int rst = 0;
        for (int i = 0; i < list.size(); ) {
            int j = i + 1;
            while (j < list.size() && list.get(j) > list.get(j - 1)) {
                j++;
            }
            rst = Math.max((j - i), rst);
            i = j;
        }

        return rst;
    }
}