package exe160.intersection.of.two.linked.lists;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthOfA = 0;
        ListNode curA = headA;
        while (curA != null) {
            curA = curA.next;
            lengthOfA++;
        }
        int lengthOfB = 0;
        ListNode curB = headB;
        while (curB != null) {
            curB = curB.next;
            lengthOfB++;
        }

        curA = headA;
        curB = headB;
        if (lengthOfB > lengthOfA) {
            while (lengthOfB > lengthOfA) {
                curB = curB.next;
                lengthOfB--;
            }
        } else if (lengthOfA > lengthOfB) {
            while (lengthOfA > lengthOfB) {
                curA = curA.next;
                lengthOfA--;
            }
        }

        while (lengthOfA > 0) {
            if (curA == curB) {
                return curA;
            }
            lengthOfA--;
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}