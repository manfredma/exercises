/*
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.



Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.


Example 3:

Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.




Follow up:

Can you solve it using O(1) (i.e. constant) memory?

 */
package exe141.linked.list.cycle;

/**
 * @author Manfred since 2019/8/30
 */
public class Main {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode2;

        Solution solution = new Solution();
        System.out.println(solution.hasCycle(listNode1));

        ListNode listNode21 = new ListNode(2);
        ListNode listNode22 = new ListNode(1);
        listNode21.next = listNode22;
        listNode22.next = listNode21;
        System.out.println(solution.hasCycle(listNode21));

        ListNode listNode31 = new ListNode(3);
        System.out.println(solution.hasCycle(listNode31));
    }
}
