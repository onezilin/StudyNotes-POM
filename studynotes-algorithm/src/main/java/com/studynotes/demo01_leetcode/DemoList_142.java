package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * Description:
 * <p>
 * 142. 环形链表 II
 * <p>
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回 null。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * 进阶：你是否可以不用额外空间解决此题？
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class DemoList_142 {

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
        System.out.println(detectCycle(head));
    }

    /**
     * Description: 解题思路：
     * 1、使用快慢指针
     * 2、定义两个指针 slow 和 fast，slow 每次移动一步，fast 每次移动两步。
     * 3、如果链表中没有环，则 fast 会先到达链表尾部；如果链表中有环，则 slow 和 fast 会相遇。
     * 4、当 slow 和 fast 相遇时，将 slow 指针重新指向 head，然后 slow 和 fast 每次移动一步，再次相遇的节点即为环的入口节点。
     * PS：这个证明过程可以参考题解：
     * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/solutions/12616/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/">...</a>
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;

        // 当 slow 和 fast 相遇时，将 slow 指针重新指向 head，然后 slow 和 fast 每次移动一步，再次相遇的节点即为环的入口节点
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * Description: 解题思路：
     * 1、使用哈希表
     * 2、遍历链表，使用哈希表存储链表中的元素，如果当前节点已经在哈希表中，则说明有环，返回当前节点
     */
    public ListNode detectCycle0(ListNode head) {
        HashSet<ListNode> hash = new HashSet<>();
        ListNode p = head;

        while (p != null) {
            if (hash.contains(p)) return p;
            else hash.add(p);
            p = p.next;
        }

        return null;
    }
}
