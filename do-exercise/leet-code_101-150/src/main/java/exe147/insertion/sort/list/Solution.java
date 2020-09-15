package exe147.insertion.sort.list;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode cur = head.next;
        ListNode preCur = head;
        while (null != cur) {
            ListNode next = cur.next;
            ListNode pre = null;
            ListNode after = head;
            while (after.val < cur.val) {
                pre = after;
                after = after.next;
            }
            if (after == cur) {
                preCur = cur;
                cur = next;
                continue;
            }
            if (pre == null) {
                cur.next = head;
                head = cur;
            } else {
                pre.next = cur;
                cur.next = after;
            }
            preCur.next = next;
            cur = next;
        }
        return head;
    }
}