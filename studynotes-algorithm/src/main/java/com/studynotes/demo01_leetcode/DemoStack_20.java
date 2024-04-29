package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Stack;

/**
 * Description:
 * <p>
 * 20. 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。有效字符串需满足：
 * * 左括号必须用相同类型的右括号闭合。
 * * 左括号必须以正确的顺序闭合。
 * * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 示例：
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 输入：s = "(]"
 * 输出：false
 */
public class DemoStack_20 {

    @Test
    public void test() {
        isValid("([)]");
    }

    /**
     * Description: 解题思路：
     * 1、使用 Stack 栈结构，通过比较栈顶元素是否和当前字符相匹配，例如：( 和 ) 匹配
     * 2、如果匹配，则将栈顶元素出栈
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (char ch : arr) {
            Character top = null;
            if (!stack.isEmpty()) top = stack.peek();
            if (top != null && (Objects.equals(top, '(') && ')' == ch) || (Objects.equals(top, '{') && '}' == ch) || (Objects.equals(top, '[') && ']' == ch)) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
