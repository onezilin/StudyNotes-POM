package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * Description:
 * <p>
 * 141. 环形链表
 * <p>
 * 给定一个链表，判断链表中是否有环。如果链表中存在环，则返回 true。否则，返回 false。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 进阶： 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class DemoList_141 {

    @Test
    public void test() {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        System.out.println(hasCycle(head));
    }

    /**
     * Description: 解题思路：
     * 1、使用快慢指针
     * 2、定义两个指针 slow 和 fast，slow 每次移动一步，fast 每次移动两步。
     * 3、如果链表中没有环，则 fast 会先到达链表尾部；如果链表中有环，则 slow 和 fast 会相遇。
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }

    /**
     * Description: 解题思路：
     * 1、使用哈希表
     * 2、遍历链表，使用哈希表存储链表中的元素，如果哈希表中已经存在该节点，则说明有环
     */
    public boolean hasCycle0(ListNode head) {
        HashSet<ListNode> hash = new HashSet<>();
        ListNode p = head;

        while (p != null) {
            if (hash.contains(p)) return true; // 如果哈希表中已经存在该节点，则说明有环
            else hash.add(p);
            p = p.next;
        }

        return false;
    }
}
