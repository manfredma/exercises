package exe24.swap.nodes.in.pairs;

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode result = head.next;

        ListNode current = head;
        ListNode tmp;
        ListNode pre = null;

        while (true) {
            if (null == current || current.next == null) {
                break;
            }
            if (null != pre) {
                pre.next = current.next;
            }
            pre = current;
            tmp = current.next;
            current.next = current.next.next;
            tmp.next = current;
            current = current.next;
        }
        return result;

    }
}