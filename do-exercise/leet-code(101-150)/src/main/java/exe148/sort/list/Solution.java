package exe148.sort.list;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        return doSortList(head);
    }

    private ListNode doSortList(ListNode head) {
        if (head.next == null) {
            return head;
        } else if (head.next.next == null) {
            if (head.val > head.next.val) {
                int tmp = head.val;
                head.val = head.next.val;
                head.next.val = tmp;
            }
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode second = slow.next;
        slow.next = null;

        ListNode first = head;
        first = doSortList(first);
        second = doSortList(second);


        ListNode result = new ListNode(-1);
        ListNode cur = result;
        while (first != null && second != null) {
            if (first.val < second.val) {
                cur.next = first;
                first = first.next;
                cur = cur.next;
            } else {
                cur.next = second;
                second = second.next;
                cur = cur.next;
            }
        }
        if (first != null) {
            cur.next = first;
        }
        if (second != null) {
            cur.next = second;
        }
        return result.next;
    }
}