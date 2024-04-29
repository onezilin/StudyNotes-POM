package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 92. 反转链表 II
 * <p>
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回反转后的链表 。
 * <p>
 * 示例：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 */
public class DemoList_92 {

    @Test
    public void test() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(reverseBetween(head, 2, 4));
    }

    /**
     * Description: 解题思路：先看一下怎么翻转前 n 个节点 {@link this#reverseBetween(ListNode, int)}
     * 1、递归获取第 left - 1 个节点（作为翻转开始的节点），开始翻转前 right - left 个节点
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) return reverseBetween(head, right);
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    /**
     * Description: 解题思路：递归翻转链表查看 {@link DemoList_206#reverseList0(ListNode)}
     * 1、递归获取第 n 个节点（翻转后就是头结点）
     * 2、记录第 n 个节点的 next 节点，就是翻转后的后续节点（即原 head 的 next 节点）
     * 3、对当前节点进行翻转操作
     */
    ListNode processor = null;

    public ListNode reverseBetween(ListNode cur, int n) {
        if (n == 1) {
            processor = cur.next;
            return cur;
        }
        ListNode last = reverseBetween(cur.next, n - 1);
        cur.next.next = cur;
        cur.next = processor;
        return last;
    }
}
