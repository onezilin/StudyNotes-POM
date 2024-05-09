package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * 95. 不同的二叉搜索树 II
 * <p>
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 */
public class DemoBST_95 {

    @Test
    public void test() {
        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println(treeNodes);
    }

    /**
     * Description: 解题思路：
     * 1、BST 二叉搜索树的性质：左子树的所有节点值都小于根节点值，右子树的所有节点值都大于根节点值
     * 2、遍历递增数组，以当前位置为根节点，左边数组作为左子节点，右边数组作为右子节点
     * 3、递归，构建树
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int low, int high) {
        List<TreeNode> allNodes = new ArrayList<>();
        if (low > high) {
            allNodes.add(null); // 返回 null
            return allNodes;
        }

        for (int i = low; i <= high; i++) {

            List<TreeNode> leftList = generateTrees(low, i - 1); // 获取当前节点的所有左子节点集合
            List<TreeNode> rightList = generateTrees(i + 1, high); // 获取当前节点的所有右子节点集合

            // 遍历左右子节点集合，构建根节点
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    allNodes.add(root);
                }
            }
        }
        return allNodes;
    }
}
