package exe141.linked.list.cycle;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (null != fast) {
            slow = slow.next;
            fast = fast.next;
            if (null != fast) {
                fast = fast.next;
            }
            if (fast != null && slow == fast) {
                return true;
            }
        }
        return false;
    }
}