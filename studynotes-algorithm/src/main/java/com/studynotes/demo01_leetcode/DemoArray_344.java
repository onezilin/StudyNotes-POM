package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 344. 反转字符串
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 额外空间解决这一问题。
 * <p>
 * 示例：
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 */
public class DemoArray_344 {

    @Test
    public void test() {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(s);
    }

    /**
     * Description: 解题思路：
     * 1、使用双指针
     * 2、定义两个指针，分别指向数组的头部和尾部，交换两个指针指向的元素
     */
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;

        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }
}
