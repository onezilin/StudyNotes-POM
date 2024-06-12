package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 1143. 最长公共子序列
 * <p>
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * <p>
 * 注意：
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删减任意数量的字符（也可以不删减）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的公共子序列是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 */
public class DemoDP_1143 {

    @Test
    public void test() {
        System.out.println(longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、由于需要与两个字符串进行对比，需要建立二维表 dp[][]，以 text1 作为行、text2 作为列
     * 3、首先确定 base case，即第一行 text1 首字母和 text2 的最长公共子串；第一列 text2 的首字母和 text1 的最长公共子串
     * 4、再找出当前值和上下左右的关系，如图所示：
     * <p>
     * | row[i] 和 columns[j] 最长公共子序列   | row[i-1] 和 columns[j] 最长公共子序列 |
     * ---------------------------------------------------------------------------
     * | row[i] 和 columns[j-1] 最长公共子序列 | dp[i][j]                           |
     * <p>
     * rows[i] == columns[j] 时，值为 dp[i][j] = dp[i-1][j-1] + 1，即前面的最长公共子序列值 + 1；
     * rows[i] != columns[j] 时，dp[i][j] = max(dp[i-1][j], dp[i][j-1])，即获取 text1 或 text2 前一个字母的最长公共子序列。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] rows = text1.toCharArray();
        char[] columns = text2.toCharArray();
        int[][] dp = new int[rows.length][columns.length];

        // 先设置 base case 边界
        int maxLength = 0;
        for (int i = 0; i < rows.length; i++) {
            // 如果 text1 首字母在 text2 的 [0, i-1] 子串区间内，则最长公共子串设为 1
            if (rows[i] == columns[0] || (i > 0 && dp[i - 1][0] == 1)) {
                dp[i][0] = 1;
                maxLength = 1;
            }
        }
        for (int j = 0; j < columns.length; j++) {
            // 与上同理
            if (rows[0] == columns[j] || (j > 0 && dp[0][j - 1] == 1)) {
                dp[0][j] = 1;
                maxLength = 1;
            }
        }

        for (int i = 1; i < rows.length; i++) {
            for (int j = 1; j < columns.length; j++) {
                if (rows[i] == columns[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }
}
