/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
 */

package exe25.reverse.nodes.in.k.group;


public class Main {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        ListNode l = n1;
        while (null != l) {
            System.out.print(l.val + "->");
            l = l.next;
        }
        System.out.println();
        l = new Solution().reverseKGroup(n1, 2);
        while (null != l) {
            System.out.print(l.val + "->");
            l = l.next;
        }

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = null;
        System.out.println();
        l = new Solution2().reverseKGroup(n1, 2);
        while (null != l) {
            System.out.print(l.val + "->");
            l = l.next;
        }
    }
}
