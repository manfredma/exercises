package exe19.remove.nth.node.from.end.of.list;

public class Main {

    public static void main(String[] args) {
        ListNode n = new ListNode(5);
        ListNode n2 = new ListNode(4);
        n2.next = n;
        ListNode n3 = new ListNode(3);
        n3.next = n2;
        ListNode n4 = new ListNode(2);
        n4.next = n3;
        ListNode n5 = new ListNode(1);
        n5.next = n4;
        Solution solution = new Solution();
        ListNode r = solution.removeNthFromEnd(n5, 5);
        while (null != r) {
            System.out.print(r.val + "->");
            r = r.next;
        }

        System.out.println();
        ListNode n6 = new ListNode(5);
        ListNode n7 = solution.removeNthFromEnd(n6, 1);
        while (null != n7) {
            System.out.print(n7.val + "->");
            n7 = n7.next;
        }
    }
}
