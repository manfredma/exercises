/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */
package exe23.merge.k.sorted.lists;



public class Main {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;

        ListNode m1 = new ListNode(1);
        ListNode m2 = new ListNode(3);
        ListNode m3 = new ListNode(4);
        m1.next = m2;
        m2.next = m3;

        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(6);
        p1.next = p2;

        ListNode l = new Solution().mergeKLists(new ListNode[]{n1, m1, p1});
        while (null != l) {
            System.out.print(l.val + "->");
            l = l.next;
        }
    }
}
