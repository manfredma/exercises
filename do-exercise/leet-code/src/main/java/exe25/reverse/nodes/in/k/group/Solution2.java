package exe25.reverse.nodes.in.k.group;

class Solution2 {
    ListNode pre = null;
    ListNode first = null;
    ListNode check = null;
    ListNode current = null;
    ListNode currentNext = null;
    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || null == head.next) {
            return head;
        }
        if (1 == k) {
            return head;
        }

        ListNode v = new ListNode(0);
        v.next = head;
        pre = v;
        first = head;
        check = head;
        current = head;
        currentNext = current.next;
        for (int i = 1; null != check; i++) {
            check = check.next;
            if (i % k == 0) {
                // has k element
                for (int j = 1; j < k; j++) {
                    // del
                    current = currentNext;
                    if (null != currentNext) {
                        currentNext = currentNext.next;
                    }
                    first.next = current.next;

                    // add
                    current.next = pre.next;
                    pre.next = current;
                }
                if (null != currentNext)
                    currentNext = currentNext.next;
                pre = first;
                first = first.next;
            }

        }
        return v.next;
    }
}