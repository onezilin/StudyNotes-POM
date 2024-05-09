package com.studynotes.demo01_leetcode;

import com.studynotes.entity.Node;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 * <p>
 * 116. 填充每个节点的下一个右侧节点指针
 * <p>
 * 给定一个完美二叉树（每一层的叶子节点都是满的），其所有叶子节点都在同一层，每个父节点都有两个子节点。填充它的每个 next 指针，指向同级别的下一个节点。
 */
public class DemoTree_116 {

    @Test
    public void test() {
        Node root = new Node(1, new Node(2, new Node(4), new Node(5)), new Node(3, new Node(6), new Node(7)));
        connect(root);
    }

    /**
     * Description: 解题思路：
     * 1、使用 BFS 遍历每一层的节点，将每一层的节点的 next 指针指向下一个节点
     */
    public Node connect0(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i < size - 1) {
                    cur.next = queue.peek();
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return root;
    }

    /**
     * Description: 解题思路：
     * 1、递归相邻的两个节点，将 node1 节点的 next 指针指向 node2
     * 2、递归 node1、node2 左右子树，此外还需要将 node1 的右子树和 node2 的左子树连接起来
     */
    public Node connect(Node root) {
        if (root == null) return null;
        connectTwo(root.left, root.right);
        return root;
    }

    private void connectTwo(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;
        node1.next = node2;
        connectTwo(node1.left, node1.right);
        connectTwo(node1.right, node2.left);
        connectTwo(node2.left, node2.right);
    }
}
