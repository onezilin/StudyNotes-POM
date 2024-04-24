package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 5. 最长回文子串
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 */
public class DemoArray_5 {

    @Test
    public void test() {
        String s = "aacabdkacaa";
        System.out.println(longestPalindrome(s));
    }

    /**
     * Description: 解题思路：
     * 1、使用左右双指针
     * 2、遍历字符串，以当前字符为中心，向两边扩展，找到最长的回文子串
     * 3、当前字符为中心的回文子串有两种情况：奇数长度和偶数长度，分别处理。奇数长度时，左右指针相等，偶数长度时，左右指针相邻
     * 4、比较奇数长度和偶数长度的回文子串，取最长的
     */
    public String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i); // 奇数长度，以当前字符为中心
            result = s1.length() > result.length() ? s1 : result;
            String s2 = palindrome(s, i, i + 1); // 偶数长度，以当前字符和下一个字符为中心
            result = s2.length() > result.length() ? s2 : result;
        }
        return result;
    }

    public String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}
