package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 876. 链表的中间结点
 * <p>
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 */
public class DemoList_876 {

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(middleNode(head).val);
    }

    /**
     * Description: 解题思路：
     * 1、使用快慢指针
     * 2、定义两个指针 slow 和 fast，slow 每次移动一步，fast 每次移动两步。
     * 3、如果链表长度为奇数，则 fast 会先到达链表尾部；如果链表长度为偶数，则 slow 和 fast 会同时到达中间结点。
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
