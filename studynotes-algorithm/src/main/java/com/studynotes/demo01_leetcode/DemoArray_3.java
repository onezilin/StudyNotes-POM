package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * <p>
 * 3. 无重复字符的最长子串
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串的长度。
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class DemoArray_3 {

    @Test
    public void test() {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * Description: 解题思路：
     * 1、使用滑动窗口技巧
     * 2、使用哈希表记录窗口内字符
     * 3、使用 left 和 right 两个指针控制窗口大小，right 向右移动扩大窗口，left 向右移动缩小窗口
     * 4、当窗口内字符重复时，left 向右移动，直到窗口内字符不重复
     * 5、使用 maxLength 记录窗口内字符最大长度
     */
    Set<Character> window = new HashSet<>();

    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int maxLength = 0;

        while (right < s.length()) {
            char cr = s.charAt(right);
            right++;

            // 当窗口内字符重复时，left 向右移动，直到窗口内字符不重复
            while (window.contains(cr) && left < right) {
                char cl = s.charAt(left);
                window.remove(cl);
                left++;
            }

            // 将当前字符加入窗口，更新窗口最大长度
            window.add(cr);
            maxLength = Math.max(maxLength, window.size());
        }

        return maxLength;
    }
}
