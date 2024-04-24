package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * 76. 最小覆盖子串
 * <p>
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 * <p>
 * 示例：
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 */
public class DemoArray_76 {

    @Test
    public void test() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    /**
     * Description: 解题思路：
     * 1、使用滑动窗口技巧
     * 2、使用两个哈希表记录所需字符数和窗口内字符数，用于判断窗口内字符是否满足条件
     * 3、使用 left 和 right 两个指针控制窗口大小，right 向右移动扩大窗口，left 向右移动缩小窗口
     * 4、当窗口内字符满足条件时，记录当前窗口大小，并缩小窗口，直到窗口内字符不满足条件
     */
    Map<Character, Integer> need = new HashMap<>(); // 记录所需字符数
    Map<Character, Integer> window = new HashMap<>(); // 记录窗口内字符数

    public String minWindow(String s, String t) {
        int left = 0, right = 0;
        int minStart = 0, minLength = Integer.MAX_VALUE;

        for (char c : t.toCharArray()) {
            if (need.containsKey(c)) need.put(c, need.get(c) + 1);
            else need.put(c, 1);
        }

        while (right < s.length()) {
            // 将字符放入窗口内，并扩大窗口
            char cr = s.charAt(right);
            if (window.containsKey(cr)) window.put(cr, window.get(cr) + 1);
            else window.put(cr, 1);
            right++;

            // 窗口内元素全部满足，开始缩小窗口
            while (check() && left < right) {
                if (right - left < minLength) {
                    minStart = left;
                    minLength = right - left;
                }
                char cl = s.charAt(left);
                window.put(cl, window.get(cl) - 1);
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }

    public boolean check() {
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            Character c = entry.getKey();
            if (!window.containsKey(c) || window.get(c) < need.get(c)) {
                return false;
            }
        }

        return true;
    }
}
