package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * 230. 二叉搜索树中第K小的元素
 * <p>
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * 示例 1：
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 */
public class DemoBST_230 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        System.out.println(kthSmallest(root, 1));
    }

    /**
     * Description: 解题思路：
     * 1、二叉搜索树的中序遍历是一个递增的序列
     * 2、中序遍历的第k个元素即为第k小的元素
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        getSortList(root, list);
        return list.get(k - 1);
    }

    public void getSortList(TreeNode root, List<Integer> list) {
        if (root == null) return;
        getSortList(root.left, list);
        list.add(root.val);
        getSortList(root.right, list);
    }
}
