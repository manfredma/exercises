package exe19.remove.nth.node.from.end.of.list;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode r = null;
        if (n == 1) {
            r = head;
        }
        ListNode p = null;
        int dis = 1;
        ListNode node = head;
        while (null != node.next) {
            dis += 1;
            if (dis == n) {
                r = head;
            } else if (r != null) {
                p = r;
                r = r.next;
            }
            node = node.next;
        }

        if (p != null && r != null) {
            p.next = r.next;
        }

        if (r == head) {
            return head.next;
        } else {
            return head;
        }
    }
}