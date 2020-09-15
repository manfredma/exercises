package exe155.min.stack;

import java.util.Stack;

class MinStack {

    private Stack<ListNode> stack = new Stack<>();
    private ListNode head = new ListNode(Integer.MIN_VALUE);
    private ListNode tail = new ListNode(Integer.MAX_VALUE);

    {
        head.next = tail;
        tail.pre = head;
    }

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        ListNode node = new ListNode(x);
        ListNode next = head.next;
        while (next.val < node.val) {
            next = next.next;
        }
        ListNode pre = next.pre;
        node.next = next;
        node.pre = pre;
        pre.next = node;
        next.pre = node;
        stack.push(node);
    }

    public void pop() {
        ListNode listNode = stack.pop();
        ListNode pre = listNode.pre;
        ListNode next = listNode.next;
        pre.next = next;
        next.pre = pre;
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return head.next.val;
    }

    private static class ListNode {
        ListNode next;
        ListNode pre;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */