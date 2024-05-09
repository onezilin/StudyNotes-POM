package com.studynotes.demo01_leetcode;

import com.studynotes.entity.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 * <p>
 * 297. 二叉树的序列化与反序列化
 * <p>
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 */
public class DemoTree_297 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.right = new TreeNode(4);

        String string = serialize(root); // 1,2,3,#,4,#,#,#,#
        TreeNode treeNode = deserialize(string);
        System.out.println(treeNode);
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(4);

        TreeNode node11 = new TreeNode(-7);
        TreeNode node12 = new TreeNode(-3);
        root.left = node11;
        root.right = node12;

        TreeNode node21 = new TreeNode(-9);
        TreeNode node22 = new TreeNode(-3);
        node12.left = node21;
        node12.right = node22;

        TreeNode node31 = new TreeNode(9);
        TreeNode node32 = new TreeNode(-7);
        node21.left = node31;
        node21.right = node32;
        TreeNode node33 = new TreeNode(-4);
        node22.left = node33;

        TreeNode node41 = new TreeNode(6);
        node31.left = node41;
        TreeNode node42 = new TreeNode(-6);
        TreeNode node43 = new TreeNode(-6);
        node32.left = node42;
        node32.right = node43;

        TreeNode node51 = new TreeNode(0);
        TreeNode node52 = new TreeNode(6);
        node41.left = node51;
        node41.right = node52;
        TreeNode node53 = new TreeNode(5);
        node42.left = node53;
        TreeNode node54 = new TreeNode(9);
        node43.left = node54;

        TreeNode node61 = new TreeNode(-1);
        node51.right = node61;
        TreeNode node62 = new TreeNode(-4);
        node52.left = node62;
        TreeNode node63 = new TreeNode(-2);
        node54.left = node63;

        String string = serialize(root);
        TreeNode treeNode = deserialize(string);
        System.out.println(treeNode);
    }

    /**
     * Description: 解题思路：
     * 1、该题并没有限制序列化后的字符串格式，因此可以使用任意方式序列化二叉树。
     * 2、使用层序遍历，将二叉树序列化为字符串
     * 3、难点在于反序列化时，找出左右子节点的索引
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    result.add(String.valueOf(cur.val));
                } else {
                    result.add("#");
                    continue;
                }
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return String.join(",", result);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) return null;
        List<String> list = fullBfsList(data);
        return buildTree(list, 0);
    }

    public TreeNode buildTree(List<String> list, int rootIndex) {
        if (rootIndex > list.size() - 1) return null;
        String val = list.get(rootIndex);
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        // 对于完全二叉树，左子节点的索引为 2 * 父节点索引 + 1；右子节点索引为 2 * 父节点索引 + 2
        root.left = buildTree(list, 2 * rootIndex + 1);
        root.right = buildTree(list, 2 * rootIndex + 2);
        return root;
    }

    // 将原始的【二叉树】层序遍历生成的结果，扩展成【完美二叉树】层序遍历生成的结果，方便后序递归时构建树
    private List<String> fullBfsList(String data) {
        String[] arr = data.split(",");
        List<String> list = Stream.of(arr).collect(Collectors.toList());
        int level = 0; // 当前树的层级，从 0 层开始
        int start = 0; // 当前层级第一个节点的索引下标
        int count = (int) Math.pow(2, level); // 当前层级节点个数
        while (true) {
            int end = Math.min(start + count, list.size());
            // 判断当前层级节点是否都是 #，如果是，则表示到了最后一层，跳出循环
            LinkedHashSet<String> set = new LinkedHashSet<>(list.subList(start, end));
            if (set.size() == 1 && "#".equals(set.iterator().next())) return list;
            // 对空节点的子节点进行补全，空节点的子节点也是空节点
            for (int i = start; i < end; i++) {
                if ("#".equals(list.get(i))) {
                    int leftIndex = 2 * i + 1;
                    int rightIndex = 2 * i + 2;
                    list.add(leftIndex, "#");
                    list.add(rightIndex, "#");
                }
            }
            start = end;
            level++;
            count = (int) Math.pow(2, level);
        }
    }
}
