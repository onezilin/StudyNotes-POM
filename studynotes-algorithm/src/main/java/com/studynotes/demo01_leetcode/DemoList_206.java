package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 206. 反转链表
 * <p>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
 * <p>
 * 示例：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 */
public class DemoList_206 {


    @Test
    public void test() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(reverseList0(head));
    }

    /**
     * Description: 解题思路：
     * 1、使用双指针，记录前置节点和当前节点，依次进行交换
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;

        }
        return pre;
    }

    /**
     * Description: 解题思路：
     * 1、递归获取最后一个节点（翻转后就是头结点）
     * 2、对当前节点进行翻转操作
     */
    public ListNode reverseList0(ListNode cur) {
        if (cur == null || cur.next == null) return cur;
        ListNode last = reverseList0(cur.next);
        cur.next.next = cur;
        cur.next = null;
        return last;
    }
}
