/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
 */

package exe19.remove.nth.node.from.end.of.list;

public class Main {

    public static void main(String[] args) {
        ListNode n = new ListNode(5);
        ListNode n2 = new ListNode(4);
        n2.next = n;
        ListNode n3 = new ListNode(3);
        n3.next = n2;
        ListNode n4 = new ListNode(2);
        n4.next = n3;
        ListNode n5 = new ListNode(1);
        n5.next = n4;
        Solution solution = new Solution();
        ListNode r = solution.removeNthFromEnd(n5, 5);
        while (null != r) {
            System.out.print(r.val + "->");
            r = r.next;
        }

        System.out.println();
        ListNode n6 = new ListNode(5);
        ListNode n7 = solution.removeNthFromEnd(n6, 1);
        while (null != n7) {
            System.out.print(n7.val + "->");
            n7 = n7.next;
        }
    }
}
