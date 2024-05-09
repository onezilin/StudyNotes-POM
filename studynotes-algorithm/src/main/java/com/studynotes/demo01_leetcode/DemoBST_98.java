package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 98. 验证二叉搜索树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 一个二叉搜索树具有如下特征：
 * 1、节点的左子树只包含小于当前节点的数。
 * 2、节点的右子树只包含大于当前节点的数。
 * 3、所有左子树和右子树自身必须也是二叉搜索树。
 */
public class DemoBST_98 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        root.left = left;
        root.right = right;
        TreeNode rightLeft = new TreeNode(3);
        TreeNode rightRight = new TreeNode(6);
        right.left = rightLeft;
        right.right = rightRight;
        System.out.println(isValidBST(root));
    }

    /**
     * Description: 解题思路：
     * 1、判断当前节点是否大于左子树的最大值，小于右子树的最小值
     * 2、递归判断左右子树
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        if (root.left != null && root.val <= getMaxVal(root.left)) {
            return false;
        }
        if (root.right != null && root.val >= getMinVal(root.right)) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public int getMaxVal(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        int leftVal = getMaxVal(root.left);
        int rightVal = getMaxVal(root.right);
        return Math.max(root.val, Math.max(leftVal, rightVal));
    }

    public int getMinVal(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        int leftVal = getMinVal(root.left);
        int rightVal = getMinVal(root.left);
        return Math.min(root.val, Math.min(leftVal, rightVal));
    }
}
