package exe82.remove.duplicates.from.sorted.list.ii;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode listNode = new ListNode(head.val - 1);
        ListNode currentNew = listNode;
        ListNode current = head;
        ListNode pre = new ListNode(head.val - 1);
        while (null != current) {
            if (pre.val == current.val) {
                pre = current;
                current = current.next;
                continue;
            }
            if (current.next != null) {
                if (current.val == current.next.val) {
                    pre = current;
                    current = current.next;
                    continue;
                }
            }
            pre = current;
            current = current.next;
            currentNew.next = pre;
            currentNew = pre;
            currentNew.next = null;
        }
        return listNode.next;
    }
}