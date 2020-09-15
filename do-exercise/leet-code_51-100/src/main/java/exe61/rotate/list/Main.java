/*
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

 */

package exe61.rotate.list;

/**
 * @author Manfred since 2019/7/16
 */
public class Main {
    public static void main(String[] args) {
        testBasic(5, 2);
        testBasic(5, 1);
        testBasic(5, 6);
    }

    private static void testBasic(int size, int x) {
        System.out.println("size=" + size + ", rotate=" + x);
        ListNode n1 = new ListNode(1);
        ListNode pre = n1;
        for (int i = 2; i < size + 1; i++) {
            ListNode l = new ListNode(i);
            pre.next = l;
            pre = l;
        }
        ListNode l = n1;
        while (null != l) {
            System.out.print(l.val + "->");
            l = l.next;
        }
        System.out.println("NULL");
        l = new Solution().rotateRight(n1, x);
        while (null != l) {
            System.out.print(l.val + "->");
            l = l.next;
        }
        System.out.println("NULL");
    }
}
