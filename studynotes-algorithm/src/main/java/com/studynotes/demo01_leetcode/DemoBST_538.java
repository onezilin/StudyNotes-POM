package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 538. 把二叉搜索树转换为累加树
 * <p>
 * 给出二叉搜索树的根节点，该二叉树的节点值各不相同，修改二叉搜索树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 */
public class DemoBST_538 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(4, new TreeNode(1, new TreeNode(0), new TreeNode(2, null, new TreeNode(3))),
                new TreeNode(6, new TreeNode(5), new TreeNode(7, null, new TreeNode(8))));
        TreeNode treeNode = convertBST(root);
        System.out.println(treeNode);
    }

    /**
     * Description: 解题思路：
     * 1、题目意思是更新当前节点值为大于等于当前节点值的和，所以需要从大到小遍历，即当前节点右侧的节点
     * 2、树的形状为：
     * <p>
     * --------------------- 4（30）
     * ----------------- /           \
     * -------------- 1（36）        6（21）
     * ------------ /      \      /      \
     * --------- 0（36）  2（35）  5（26）   7（15）
     * ----------------------- \             \
     * ------------------------ 3（33）       8（8）
     * 例如：节点 5 的值为 5，大于等于 5 的值有 6、7、8，所以更新后的值为 26
     * 3、中序遍历（左中右）BST 是一个递增序列，但是这里需要的是一个递减序列，就需要变成右中左遍历
     */
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
