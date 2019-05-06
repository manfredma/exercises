/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
package exe2.add.two.numbers;

/**
 * @author Manfred since 2019/5/6
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = solution.addTwoNumbers(l1, l2);
        while (null != result) {
            System.out.print("->" + result.val);
            result = result.next;
        }
    }

}

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