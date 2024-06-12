package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 72. 编辑距离
 * <p>
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 * 1、插入一个字符
 * 2、删除一个字符
 * 3、替换一个字符
 * <p>
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 */
public class DemoDP_72 {

    @Test
    public void test() {
        String word1 = "pneumonoultramicroscopicsilicovolcanoconiosis";
        String word2 = "ultramicroscopically";
        System.out.println(minDistance(word1, word2));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、这个也可以使用二维表格进行处理，寻找规律，先创建 dp[][]，
     * 以 word1 为列，word2 为行，dp[i][j] 表示 word1 的前 i 个字符转换为 word2 的前 j 个字符所使用的最少操作数
     * 3、再找出当前状态和之前状态的关系，如图所示：
     * <p>
     * | 替换/跳过 | 删除     |
     * ----------------------
     * | 插入     | dp[i][j] |
     * <p>
     * 当 word1[i] == word2[j] 时，跳过，继续下一步判断，dp[i][j] = dp[i-1][j-1]
     * 当 word1[i] != word2[j] 时，从上一步增删改中获取最小的操作，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
     * 4、最后返回 dp[word1.length()][word2.length()]
     */
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        char[] rows = word2.toCharArray();
        char[] columns = word1.toCharArray();
        int[][] dp = new int[rows.length][columns.length];

        // 首先设置 base case 边界
        int count = 0;
        for (int j = 0; j < columns.length; j++) {
            // 如果 word1 首字母与 word2 [0, j] 子串不相等，就需要增加一步操作次数
            if (rows[0] != columns[j]) {
                dp[0][j] = ++count;
            } else if (j > 0) {
                dp[0][j] = dp[0][j - 1];
            }
        }
        count = 0;
        for (int i = 0; i < rows.length; i++) {
            // 与上同理
            if (rows[i] != columns[0]) {
                dp[i][0] = ++count;
            } else if (i > 0) {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i < rows.length; i++) {
            for (int j = 1; j < columns.length; j++) {
                if (rows[i] == columns[j]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[rows.length - 1][columns.length - 1];
    }
}
