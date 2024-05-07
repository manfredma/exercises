/*

Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5

 */
package exe148.sort.list;

/**
 * @author manfred on 2019/9/13.
 */
public class Main {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;

        Solution solution = new Solution();
        ListNode head = solution.sortList(node1);

        ListNode cur = head;
        while (cur != null) {
            System.out.print(" -> " + cur.val);
            cur = cur.next;
        }
        System.out.println();
    }

    private static void test3() {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Solution solution = new Solution();
        ListNode head = solution.sortList(node1);

        ListNode cur = head;
        while (cur != null) {
            System.out.print(" -> " + cur.val);
            cur = cur.next;
        }
        System.out.println();
    }

    private static void test2() {
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Solution solution = new Solution();
        ListNode head = solution.sortList(node1);

        ListNode cur = head;
        while (cur != null) {
            System.out.print(" -> " + cur.val);
            cur = cur.next;
        }
        System.out.println();
    }
}
