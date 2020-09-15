package exe143.reorder.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> x = new ArrayList<>(10000);
        ListNode cur = head;
        while (cur != null) {
            x.add(cur);
            cur = cur.next;
        }

        int half = x.size() / 2;
        for (int i = 0; i < half; i++) {
            int last = x.size() - 1 - i;
            x.get(i).next = x.get(last);
            x.get(last).next = x.get(i + 1);
        }
        x.get(half).next = null;
    }
}