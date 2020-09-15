package exe117.populating.next.right.pointers.in.each.node.ii;


import java.util.ArrayList;
import java.util.List;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        List<List<Node>> r = levelOrder(root);
        for (List<Node> nodes : r) {
            Node pre = nodes.get(0);
            for (int i = 1; i < nodes.size(); i++) {
                pre.next = nodes.get(i);
                pre = pre.next;
            }
        }
        return root;
    }

    private List<List<Node>> levelOrder(Node root) {
        List<List<Node>> result = new ArrayList<>();
        if (null == root) {
            return new ArrayList<>();
        }
        List<Node> cur = new ArrayList<>();
        cur.add(root);
        result.add(cur);

        List<Node> curNode = new ArrayList<>();
        curNode.add(root);


        do {
            List<Node> nextNode = new ArrayList<>();
            for (Node node : curNode) {
                if (node.left != null) {
                    nextNode.add(node.left);
                }
                if (node.right != null) {
                    nextNode.add(node.right);
                }
            }
            if (!nextNode.isEmpty()) {
                result.add(nextNode);
            }
            curNode = nextNode;
        } while (!curNode.isEmpty());
        return result;
    }
}