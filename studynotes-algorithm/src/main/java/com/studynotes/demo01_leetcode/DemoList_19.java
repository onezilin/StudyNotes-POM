package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 19. 删除链表的倒数第 N 个结点
 * <p>
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class DemoList_19 {

    @Test
    public void test() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode listNode = removeNthFromEnd(head, 5);
        System.out.println(listNode);
    }

    /**
     * Description: 解题思路：
     * 1、使用快慢指针
     * 2、fast 快指针先走 n - 1 步，然后快慢指针一起走，当快指针走到链表尾部时，slow 慢指针刚好走到倒数第 n 个节点。
     * 例如：n = 2 时，fast 先走 1 步，然后 slow 和 fast 一起走，当 fast 走到 5 时，slow 走到 4，即为倒数第 n 个节点。
     * 3、使用 pre 节点保存 slow 的前一个节点，然后将 pre.next = slow.next，即可删除 slow 节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head;
        ListNode pre = new ListNode(-1); // 记录 slow 的前一个节点
        pre.next = slow;

        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast.next != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = slow.next;

        return pre.val == -1 ? pre.next : head;
    }
}
