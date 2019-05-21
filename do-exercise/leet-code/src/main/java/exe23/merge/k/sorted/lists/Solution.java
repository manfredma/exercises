package exe23.merge.k.sorted.lists;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || 0 == lists.length) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }
        ListNode[] merger2 = new ListNode[(int)Math.ceil(lists.length / 2.0)];
        for (int i = 0; i < merger2.length; i++) {
            if (i != lists.length - i -1) {
                merger2[i] =  mergeTwoLists(lists[i], lists[lists.length - i -1]);
            } else {
                merger2[i] = lists[i];
            }
        }
        return mergeKLists(merger2);
    }

    private ListNode mergeTwoLists(ListNode l1,ListNode l2) {
        if (null == l1) {
            return l2;
        } else if (null == l2) {
            return l1;
        }

        ListNode head = new ListNode(0);
        ListNode current = head;

        while (true) {
            if (null != l1 && null != l2) {
                if (l1.val < l2.val) {
                    current.next = l1;
                    current = current.next;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    current = current.next;
                    l2 = l2.next;
                }
            } else if (null != l1) {
                current.next = l1;
                break;
            } else {
                current.next = l2;
                break;
            }
        }

        return head.next;
    }
}