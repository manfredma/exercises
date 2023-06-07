package exe234.palindrome.linked.list;

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
            slow = slow.next; 
        }

// 重置
        ListNode pre = slow;
        ListNode next = slow.next;
        ListNode tmp;
        while (next != null) {
            tmp = next.next;
            next.next = pre;
            pre = next;
            next = tmp;
            
        }

        
        while (slow != head) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }
        return true;

    }
}