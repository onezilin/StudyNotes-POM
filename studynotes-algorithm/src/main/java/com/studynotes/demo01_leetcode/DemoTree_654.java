package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 654. 最大二叉树
 * <p>
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * * 二叉树的根是数组中的最大元素。
 * * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 */
public class DemoTree_654 {

    @Test
    public void test() {
        int[] arr = new int[]{3, 2, 1, 6, 0, 5};
        constructMaximumBinaryTree(arr);
    }

    /**
     * Description: 解题思路：
     * 1、先判断使用哪种树的遍历方法，先找到数组中的最大值，然后构造根节点；再找到最大值左边的部分数组构造左子树，最大值右边的部分数组构造右子树。
     * 2、因此使用前序遍历，递归实现。
     * 3、找到数组中的最大值，构造根节点；
     * 4、最大值左边的部分数组构造左子树，最大值右边的部分数组构造右子树；
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int index = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        // 获取数组中的最大值，最大值左边的部分构造左子树，最大值右边的部分构造右子树
        if (index > 0) {
            int[] left = Arrays.copyOfRange(nums, 0, index);
            root.left = constructMaximumBinaryTree(left);
        }
        if (index + 1 < nums.length) {
            int[] right = Arrays.copyOfRange(nums, index + 1, nums.length);
            root.right = constructMaximumBinaryTree(right);
        }
        return root;
    }
}
