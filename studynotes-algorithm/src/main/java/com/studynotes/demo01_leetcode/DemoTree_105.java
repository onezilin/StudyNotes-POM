package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 105. 从前序与中序遍历序列构造二叉树
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意: 你可以假设树中没有重复的元素。
 * <p>
 * 示例：
 * 输入：前序遍历 preorder = [3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7]
 * 构造树节点
 */
public class DemoTree_105 {

    @Test
    public void test() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode treeNode = buildTree(preorder, inorder);
        System.out.println(treeNode);
    }

    /**
     * Description: 解题思路：
     * 1、根据前序遍历的特点，根节点在数组的第一个位置，根据中序遍历的特点，根节点在数组的中间位置；
     * 2、分别获取前序遍历和中序遍历中的左子树和右子树；
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        int mid = preorder[0];
        int midIndex = -1;
        TreeNode root = new TreeNode(mid);
        for (int i = 0; i < inorder.length; i++) {
            if (mid == inorder[i]) {
                midIndex = i;
                break;
            }
        }

        if (midIndex > 0) {
            int[] leftIn = Arrays.copyOfRange(inorder, 0, midIndex); // 根据根节点位置，获取中序遍历中的左子树
            int[] leftPre = Arrays.copyOfRange(preorder, 1, 1 + midIndex); // 根据中序遍历中的左子树元素个数，获取前序遍历中的左子树
            root.left = buildTree(leftPre, leftIn);
        }
        if (midIndex + 1 < inorder.length) {
            int[] rightIn = Arrays.copyOfRange(inorder, midIndex + 1, inorder.length); // 根据根节点位置，获取中序遍历中的右子树
            int[] rightPre = Arrays.copyOfRange(preorder, midIndex + 1, inorder.length); // 根据中序遍历中的右子树元素个数，获取前序遍历中的右子树
            root.right = buildTree(rightPre, rightIn);
        }
        return root;
    }
}
