package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 226. 翻转二叉树
 * <p>
 * 翻转一棵二叉树。依次交换每个节点的左右子树。
 */
public class DemoTree_226 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7,
                new TreeNode(6), new TreeNode(9)));
        invertTree(root);
    }

    /**
     * Description: 解题思路：
     * 1、先判断需要使用哪种树的遍历方法：找到 root 根节点，交换根节点的左右子节点；继续进入左右子节点，重复前面的操作。
     * 2、因此使用前序遍历，递归实现。
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;

        invertTree(left);
        invertTree(right);
        return root;
    }
}
