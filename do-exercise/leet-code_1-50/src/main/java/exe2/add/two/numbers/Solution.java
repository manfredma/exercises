package exe2.add.two.numbers;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        int carryNum = 0;
        while (null != l1 || null != l2) {
            int x = null == l1 ? 0 : l1.val;
            int y = null == l2 ? 0 : l2.val;
            int sum = (x + y + carryNum);
            carryNum = (x + y + carryNum) / 10;
            ListNode current = new ListNode(sum % 10);
            tail.next = current;
            tail = current;
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }

        if (carryNum != 0) {
            tail.next = new ListNode(carryNum);
        }
        return head.next;
    }
}