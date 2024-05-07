package exe138.copy.list.with.random.pointer;

import java.util.HashMap;
import java.util.Map;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        Node cloneHead = clone(head.val, null);
        Node current = head;
        Node currentClone = cloneHead;
        Map<Node, Node> node2Clone = new HashMap<>();
        node2Clone.put(head, cloneHead);
        while (current != null) {
            if (current.next != null) {
                Node next = current.next;
                Node cloneNext = clone(next.val, currentClone);
                node2Clone.put(next, cloneNext);
            }
            current = current.next;
            currentClone = currentClone.next;
        }

        current = head;
        while (null != current) {
            if (current.random != null) {
                Node clone = node2Clone.get(current);
                clone.random = node2Clone.get(current.random);
            }
            current = current.next;
        }
        return cloneHead;
    }

    private Node clone(int val, Node pre) {
        Node clone = new Node();
        clone.val = val;
        if (null != pre) {
            pre.next = clone;
        }
        return clone;
    }
}
