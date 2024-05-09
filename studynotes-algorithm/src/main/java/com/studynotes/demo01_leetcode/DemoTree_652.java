package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * <p>
 * 652. 寻找重复的子树
 * <p>
 * 给定一颗二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 */
public class DemoTree_652 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(4);
        right.left = new TreeNode(2);
        right.right = new TreeNode(4);
        right.left.left = new TreeNode(4);

        List<TreeNode> duplicateSubtrees = findDuplicateSubtrees(root);
        System.out.println(duplicateSubtrees);
    }

    // 记录每种树形状对应的节点，如果节点长度大于 1，表示有相同的子树
    Map<String, List<TreeNode>> nodeList = new HashMap<>();

    /**
     * Description: 解题思路：
     * 1、先思考使用哪种树的遍历方法，要先获取该节点子树的形状，才能判断是否有相同的节点
     * 2、因此使用后序遍历
     * 3、难点在于：想到使用字符串用于比较树节点子树是否相同
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        getPostString(root);
        List<TreeNode> result = new ArrayList<>();
        for (List<TreeNode> treeNode : nodeList.values()) {
            if (treeNode.size() > 1) {
                result.add(treeNode.get(0));
            }
        }
        return result;
    }

    public String getPostString(TreeNode root) {
        if (root == null) return "null";
        String left = getPostString(root.left);
        String right = getPostString(root.right);
        // 使用字符串用于记录树的形状
        String sum = left + "," + right + "," + root.val;
        if (nodeList.containsKey(sum)) {
            nodeList.get(sum).add(root);
        } else {
            List<TreeNode> list = new ArrayList<>();
            list.add(root);
            nodeList.put(sum, list);
        }
        return sum;
    }
}
