package exe133.clone.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

// Definition for a Node.

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};

*/
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Integer> nodeIndex = new HashMap<>();
        List<Node> curNodes = new ArrayList<>();
        nodeIndex.put(node, curNodes.size());
        curNodes.add(node);
        Node cloneNode = new Node(node.val, new ArrayList<>());
        List<Node> curCloneNodes = new ArrayList<>();
        curCloneNodes.add(cloneNode);

        int begin = 0, end = 1;
        while (begin < end) {
            for (int i = begin; i < end; i++) {
                Node cur = curNodes.get(i);
                if (null == cur.neighbors) {
                    continue;
                }
                for (Node neighbor : cur.neighbors) {
                    if (!nodeIndex.containsKey(neighbor)) {
                        nodeIndex.put(neighbor, curNodes.size());
                        curNodes.add(neighbor);
                        Node clone = new Node(neighbor.val, new ArrayList<>());
                        curCloneNodes.add(clone);
                    }
                }
            }
            begin = end;
            end = curNodes.size();
        }

        for (int i = 0; i < curNodes.size(); i++) {
            Node cur = curNodes.get(i);
            Node clone = curCloneNodes.get(i);
            for (int i1 = 0; i1 < cur.neighbors.size(); i1++) {
                Node neighbor = cur.neighbors.get(i1);
                clone.neighbors.add(curCloneNodes.get(nodeIndex.get(neighbor)));
            }
        }
        return curCloneNodes.get(0);
    }
}