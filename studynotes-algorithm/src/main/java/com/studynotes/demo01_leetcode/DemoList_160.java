package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 160. 相交链表
 * <p>
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 */
public class DemoList_160 {

    @Test
    public void test() {
        ListNode headA = new ListNode(4);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        headA.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode headB = new ListNode(5);
        ListNode node5 = new ListNode(0);
        ListNode node6 = new ListNode(1);
        headB.next = node5;
        node5.next = node6;
        node6.next = node2;

        System.out.println(getIntersectionNode(headA, headB).val);
    }

    /**
     * Description: 解题思路：
     * 1、定义两个指针 pA 和 pB，分别指向两个链表的头结点 headA 和 headB。
     * 2、同时遍历两个链表，当 pA 到达链表尾部时，将其重定向到 headB；当 pB 到达链表尾部时，将其重定向到 headA。
     * 3、由于 PA 和 PB 走过相同的路程，如果 pA 和 pB 相遇，即为相交结点。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;

        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }
}
