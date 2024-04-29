package com.studynotes.entity;

import java.util.HashSet;

/**
 * Description:
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        HashSet<ListNode> hash = new HashSet<>();
        ListNode cur = this;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.val);

            // 防止成环
            if (hash.contains(cur)) break;
            else hash.add(cur);

            if (cur.next != null) {
                sb.append(",");
            }
            cur = cur.next;
        }
        return sb.toString();
    }


}
