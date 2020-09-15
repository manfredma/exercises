package exe206.reverse.linked.list;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode pre = head;
        head.next = null;
        while (next != null) {
            ListNode cur = next;
            next = next.next;
            cur.next = pre;
            pre = cur;
        }
        return pre;
    }
}