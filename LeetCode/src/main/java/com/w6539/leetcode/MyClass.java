package com.w6539.leetcode;

public class MyClass {
    public static String printByteBinary(byte b) {
        StringBuilder rst = new StringBuilder();
        // 使用位运算将byte的每个位转换为0或1的字符串
        for (int i = 7; i >= 0; i--) {
            // 将第i位（从最高位到最低位）与1进行与运算，然后转换为字符
            char bit = ((b >> i) & 1) == 0 ? '0' : '1';
            rst.append(bit);
        }
        return rst.toString();
    }
    public static void main(String[] args) {
        byte i = -127;

        System.out.println(printByteBinary(i));
//        List<Integer> list = new ArrayList<>();
//        list.add(0);
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        list.add(7);
//        list.add(8);
//        list.add(9);
//
//        Collections.swap(list, 9, 0);
//
//        System.out.println(list);

//        ok:
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                if (j == 5 && i == 5)
//                    break ok;
//            }
//        }
    }
}