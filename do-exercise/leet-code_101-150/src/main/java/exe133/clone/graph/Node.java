package exe133.clone.graph;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    @Override
    public String toString() {
        return "Node:" + val + "{" + neighbors.stream().map(a -> a.val).collect(Collectors.toList()) + "}";
    }
}