package exe83.remove.duplicates.from.sorted.list;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode current = head.next;
        ListNode pre = head;
        while (null != current) {
            if (pre.val == current.val) {
                // 断开链
                pre.next = null;
            } else {
                // 接上链
                pre.next = current;
                pre = current;
            }

            current = current.next;
        }
        return head;
    }
}