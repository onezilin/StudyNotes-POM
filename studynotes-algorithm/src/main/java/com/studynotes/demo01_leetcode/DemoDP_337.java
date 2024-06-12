package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 337. 打家劫舍 III
 * <p>
 * 在打家劫舍的基础上，房屋排列成二叉树结构，相邻的房屋不能同时偷窃，求能够偷窃到的最高金额。
 * 即偷了父节点、就不能再偷子节点，可以继续偷孙子节点
 * <p>
 * 实例1：
 * 输入：[3,2,3,null,3,null,1]
 * 输出：7
 * 解释：偷窃 3 + 3 + 1 = 7
 */
public class DemoDP_337 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftRight = new TreeNode(3);
        TreeNode rightRight = new TreeNode(1);

        root.left = left;
        root.right = right;
        left.right = leftRight;
        right.right = rightRight;

        System.out.println(rob(root));
    }

    /**
     * Description: 解题思路：
     * 1、不能连续偷相邻的房屋，那就分为两种情况讨论：
     * 1.1) 偷根节点，那就不能再偷子节点，下一步只能偷孙子节点
     * 1.2) 偷子节点，那就不能再偷根节点
     * 2) 递归方式遍历树节点，获取上面两种情况的最大值
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;

        int rob_self = root.val +
                (root.left != null ? (rob(root.left.left) + rob(root.left.right)) : 0) +
                (root.right != null ? (rob(root.right.left) + rob(root.right.right)) : 0);

        int rob_son = rob(root.left) + rob(root.right);

        return Math.max(rob_self, rob_son);
    }
}
