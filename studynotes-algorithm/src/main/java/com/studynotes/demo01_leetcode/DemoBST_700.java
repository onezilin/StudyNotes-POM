package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 700. 二叉搜索树中的搜索
 * <p>
 * 给定二叉搜索树（BST）的根节点和一个值 你需要在BST中找到节点值等于给定值的节点。返回以该节点为根的子树。如果节点不存在，则返回 NULL。
 */
public class DemoBST_700 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(4, new TreeNode(1, new TreeNode(1), new TreeNode(3)), new TreeNode(7));
        System.out.println(searchBST(root, 3));
    }

    /**
     * Description: 解题思路：
     * 1、BST 特点是：左子树最大值小于根节点，右子树最小值大于根节点
     * 2、对根节点进行判断，从而只进入左子树或右子树，时间复杂度为 O(logN)
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val < val) {
            return searchBST(root.right, val); // 当前值小于目标值，搜索右子树
        } else if (root.val == val) {
            return root;
        } else {
            return searchBST(root.left, val); // 当前值大于目标值，搜索左子树
        }
    }
}
