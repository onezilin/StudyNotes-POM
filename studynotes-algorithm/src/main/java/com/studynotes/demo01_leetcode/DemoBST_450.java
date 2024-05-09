package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 450. 删除二叉搜索树中的节点
 * <p>
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 */
public class DemoBST_450 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(6);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(2);
        left.right = new TreeNode(4);
        right.right = new TreeNode(7);

        TreeNode result = deleteNode(root, 3);
        System.out.println(result);
    }

    /**
     * Description: 解题思路：
     * 1、查找到要删除的节点
     * 2、找到删除节点时，有三种情况：
     * 1）左右子节点都不为 null，则有两种策略
     * 1.1、获取左子节点中的最大值替代当前节点，同时将该最大值节点从原位置移除
     * 1.2、获取右子节点中的最小值替代当前节点，同时将该最小值节点从原位置移除
     * 2）左右子节点任一为 null，则使用另一节点替代当前节点
     * 3）左右子节点都为 null，则直接删除当前节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val == key) {
            if (root.left != null && root.right != null) {
                // 如果左右子节点都不为 null，则有两种策略
                // 1、获取左子节点中的最大值替代当前节点，同时将该最大值节点从原位置移除
                // 2、获取右子节点中的最小值替代当前节点，同时将该最小值节点从原位置移除
                // 这里我选择第二种方式
                TreeNode minNode = getMinNode(root.right);
                root.right = deleteNode(root.right, minNode.val); // 将最小值节点从原位置移除
                minNode.left = root.left; // 将当前节点的左子节点赋值给最小值节点的左子节点
                minNode.right = root.right; // 将当前节点的右子节点赋值给最小值节点的右子节点
                return minNode;
            } else if (root.left != null) {
                // 如果左子节点不为 null；右子节点为 null，则使用左子节点替代当前节点
                return root.left;
            } else if (root.right != null) {
                // 如果左子节点为 null；右子节点不为 null，则使用右子节点替代当前节点
                return root.right;
            } else {
                // 如果左右子节点都为 null，则可以直接删除当前节点
                return null;
            }
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    // 获取根节点中的最小值节点，并将该节点从原位置移除
    public TreeNode getMinNode(TreeNode root) {
        if (root.left != null) {
            return getMinNode(root.left);
        } else {
            return root;
        }
    }
}
