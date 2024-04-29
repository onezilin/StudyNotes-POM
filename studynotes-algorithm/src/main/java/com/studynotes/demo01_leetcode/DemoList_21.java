package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 21. 合并两个有序链表
 * <p>
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * 输入：l1 = [1, 2, 4], l2 = [1, 3, 4]
 * 输出：[1, 1, 2, 3, 4, 4]
 */
public class DemoList_21 {

    @Test
    public void test() {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode = mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }

    /**
     * Description: 解题思路：
     * 1、使用双指针
     * 2、新建一个表头和表尾，然后遍历两个链表，比较两个链表的值，将较小的值放入新链表中
     * 3、最后将剩余的链表放入新链表中
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1), newTail = newHead;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {
            if (p1.val >= p2.val) {
                newTail.next = p2;
                p2 = p2.next;
            } else {
                newTail.next = p1;
                p1 = p1.next;
            }
            newTail = newTail.next;
        }

        // 将剩余的链表放入新链表中
        while (p1 != null) {
            newTail.next = p1;
            p1 = p1.next;
        }
        while (p2 != null) {
            newTail.next = p2;
            p2 = p2.next;
        }

        return newHead.next;
    }
}
