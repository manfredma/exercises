/*
Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.



Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
 */

package exe24.swap.nodes.in.pairs;

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
        l = new Solution().swapPairs(n1);
        while (null != l) {
            System.out.print(l.val + "->");
            l = l.next;
        }
    }
}
