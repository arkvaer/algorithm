package io.arkvaer.algorithm.primary.day4;


/**
 * 合并两个已排序的链表
 * 测试链接：<a href="https://leetcode.cn/problems/merge-two-sorted-lists">LeetCode</a>
 *
 * @author waver
 * @date 2023/6/12 上午12:06
 */
public class MergeTwoSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public  ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = list1.val < list2.val ? list1 : list2;
        ListNode smaller = head.next;
        ListNode bigger = head == list1 ? list2 : list1;
        ListNode prev = head;
        while (smaller != null && bigger != null) {
            if (smaller.val < bigger.val) {
                prev.next = smaller;
                smaller = smaller.next;
            } else {
                prev.next = bigger;
                bigger = bigger.next;
            }
            prev = prev.next;
        }
        prev.next = smaller == null ? bigger : smaller;
        return head;
    }

}
