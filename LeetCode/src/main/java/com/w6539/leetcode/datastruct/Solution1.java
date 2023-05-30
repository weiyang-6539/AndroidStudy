package com.w6539.leetcode.datastruct;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yang
 * @desc 存在重复元素
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 * @since 2023/2/21 9:20
 */
public class Solution1 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() != nums.length;
    }
}
