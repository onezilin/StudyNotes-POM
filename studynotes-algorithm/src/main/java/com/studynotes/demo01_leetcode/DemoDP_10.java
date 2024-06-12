package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 10. 正则表达式匹配
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符，'*' 匹配零个或多个前面的那一个元素，所谓匹配，是要涵盖整个字符串 s 的，而不是部分字符串。
 * <p>
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * <p>
 * 示例 1:
 * 输入: s = "aa" p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * 输入: s = "aa" p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。
 */
public class DemoDP_10 {

    @Test
    public void test() {
        System.out.println(isMatch("aa", "a*")); // true
        System.out.println(isMatch("daaa", "dab*ac*a")); // true
        System.out.println(isMatch("mississippi", "mis*is*ip*.")); // true
        System.out.println(isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(isMatch("aa", "ab*")); // false
        System.out.println(isMatch("aab", "c*a*b")); // true
        System.out.println(isMatch("aaa", "c*ab*ac*a")); // true
        System.out.println(isMatch("a", "ab*")); // true
        System.out.println(isMatch("bbbba", ".*a*a")); // true
        System.out.println(isMatch("aaa", ".*")); // true
        System.out.println(isMatch("aaa", "ab*a*c*a")); // true
        System.out.println(isMatch("a", "..*")); // true
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、两个字符串相匹配，使用二维数组 dp[][]，以 s 为列、p 为列
     * 3、先确定 base case 值，s 和 p 都是空字符串时，dp[0][0] = true
     * 4、再找出当前值和上下左右的关系：
     * 当前列字符不为 * 的情况下，dp[i][j] = dp[i-1][j-1] && equals(i, j);
     * 当前列字符为 * 的情况下：
     * 1) * 表示 0 时
     * （例如：a 和 ab*，b* 不匹配，* 应该等于 a 的值），dp[i][j] = !euqals(i, j-1) && dp[i][j-2]
     * （例如：a 和 aa*，没有值和 a* 匹配，即便第一个 a 匹配 a*，a* 也应该忽略），dp[i][j] = euqals(i, j-1) && dp[i][j-2]
     * 2) * 表示 1 时（例如：ab 和 ab*，b* 被匹配，* 应该等于 ab 和 ab 的值），dp[i][j] = euqals(i, j-1) && dp[i][j-1]
     * 3) * 表示 2 时（例如：abb 和 ab*，b* 被匹配，* 应该等于 ab 和 ab 的值）, dp[i][j] = euqals(i, j-1) && dp[i-1][j-1]
     * 4) * 表示 3 时（例如：abbb 和 ab*，b* 被匹配，* 应该等于 abb 和 ab* 的值），dp[i][j] = euqals(i, j-1) && dp[i-1][j]
     * 但是还有一种特殊情况【.*】：
     */
    char[] columns;
    char[] rows;

    public boolean isMatch(String s, String p) {
        columns = p.toCharArray();
        rows = s.toCharArray();
        boolean[][] dp = new boolean[rows.length + 1][columns.length + 1];
        // 确定 base case，即 s 和 p 都等于 ""
        dp[0][0] = true;

        // 当 s 为 "" 时，第一行还仍然需要匹配，例如："" 与 a*b*c*，因此 i 从 0 开始遍历
        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (columns[j - 1] == '*') {
                    if (equal(i, j - 1)) {
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    dp[i][j] = equal(i, j) && dp[i - 1][j - 1];
                }
            }
        }

        return dp[rows.length][columns.length];
    }

    public boolean equal(int row, int column) {
        if (row - 1 < 0 || column - 1 < 0) return false;
        return '.' == columns[column - 1] || rows[row - 1] == columns[column - 1];
    }
}
