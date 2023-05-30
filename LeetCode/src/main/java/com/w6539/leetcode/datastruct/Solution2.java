package com.w6539.leetcode.datastruct;

/**
 * @author Yang
 * @desc 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * @since 2023/2/21 10:03
 */
public class Solution2 {
    public int maxSubArray(int[] nums) {
        int pre = 0, result = nums[0];
        for (int i : nums) {
            pre = Math.max(pre + i, i);
            result = Math.max(pre, result);

            System.out.println(pre + " - " + result);
        }
        return result;
    }

    public static void main(String[] args) {
        int result = new Solution2().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(result);
    }
}
