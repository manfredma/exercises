/*
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL

 */

package exe92.reverse.linked.list.ii;

/**
 * @author manfred on 2019/8/25.
 */
public class Main {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        Solution solution = new Solution();
        ListNode r = solution.reverseBetween(listNode1, 2, 4);
        while (true) {
            System.out.print(r.val + "->");
            if (r.next != null) {
                r = r.next;
            } else {
                break;
            }
        }

        System.out.println();
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        r = solution.reverseBetween(listNode1, 1, 4);
        while (true) {
            System.out.print(r.val + "->");
            if (r.next != null) {
                r = r.next;
            } else {
                break;
            }
        }

        System.out.println();
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        r = solution.reverseBetween(listNode1, 2, 5);
        while (true) {
            System.out.print(r.val + "->");
            if (r.next != null) {
                r = r.next;
            } else {
                break;
            }
        }
    }
}
