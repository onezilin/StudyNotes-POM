package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 114. 二叉树展开为链表
 * <p>
 * 给定一个二叉树，原地将它展开为一个单链表。
 * * 展开后的单链表应该同样使用 TreeNode，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null。
 * * 展开后的单链表应该与二叉树先序遍历顺序相同。
 * <p>
 * 示例：
 * 给定二叉树
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6
 */
public class DemoTree_114 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(5);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(3);
        left.right = new TreeNode(4);
        right.right = new TreeNode(6);
        flatten(root);
        System.out.println(root);
    }

    /**
     * Description: 解题思路：
     * 1、使用中序遍历，将左子树插入到右子树的地方
     * 2、将原来的右子树接到左子树的最右边节点
     * 3、难点在于记得要寻找左子树的最右边节点
     */
    public void flatten(TreeNode root) {
        flat(root);
    }

    private TreeNode flat(TreeNode node) {
        if (node == null) return null;
        flat(node.left);
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left != null) {
            node.left = null;
            node.right = left;
            // 找到左子树的最右边节点，将右子树接到该节点上
            while (left.right != null) {
                left = left.right;
            }
            left.right = right;
        }
        flat(node.right);
        return node;
    }
}
