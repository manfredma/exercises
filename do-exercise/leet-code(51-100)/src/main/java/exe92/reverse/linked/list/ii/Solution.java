package exe92.reverse.linked.list.ii;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = head.next;
        ListNode preFirst = null;
        ListNode first = null;

        int i = 1;
        while (true) {
            if (i < m) {
                pre = cur;
                cur = next;
                next = cur.next;
                i++;
            } else if (i <= n) {
                if (i == m) {
                    preFirst = pre;
                    first = cur;
                }
                cur.next = pre;
                pre = cur;
                cur = next;
                if (cur != null) {
                    next = cur.next;
                }
                i++;
            } else {
                if (null != preFirst) {
                    preFirst.next = pre;
                }
                first.next = cur;
                if (m != 1) {
                    return head;
                } else {
                    return pre;
                }
            }
        }
    }
}