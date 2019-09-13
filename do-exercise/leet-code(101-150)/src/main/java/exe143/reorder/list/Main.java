/*

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

 */
package exe143.reorder.list;

/**
 * @author manfred on 2019/9/13.
 */
public class Main {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode node5 = new ListNode(5);
        node4.next = node5;

        Solution solution = new Solution();
        solution.reorderList(node1);

        ListNode cur = node1;
        while (cur != null) {
            System.out.print(" -> " + cur.val);
            cur = cur.next;
        }
    }
}
