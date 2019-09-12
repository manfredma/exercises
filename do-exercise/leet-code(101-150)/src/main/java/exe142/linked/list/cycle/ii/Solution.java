package exe142.linked.list.cycle.ii;

public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;
        while (null != fast) {
            slow = slow.next;
            fast = fast.next;
            if (null != fast) {
                fast = fast.next;
            }
            if (fast != null && slow == fast) {
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        return null;
    }
}