package exe61.rotate.list;

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (null == head) {
            return null;
        }
        ListNode tail = head;
        int size = 1;
        while (tail.next != null) {
            tail = tail.next;
            size++;
        }

        int mod = k % size;
        if (mod == 0) {
            return head;
        }
        tail.next = head;
        int newTail = size - mod;
        ListNode pre = head;
        for (int i = 0; i < newTail - 1; i++) {
            pre = pre.next;
        }
        ListNode result = pre.next;
        pre.next = null;
        return result;
    }
}