/*
Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.



Example:



Input:
{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
{
    "$id":"1",
    "neighbors":[
        {
            "$id":"2",
            "neighbors":[
                {
                    "$ref":"1"
                },
                {
                    "$id":"3",
                    "neighbors":[
                        {
                            "$ref":"2"
                        },
                        {
                            "$id":"4",
                            "neighbors":[
                                {
                                    "$ref":"3"
                                },
                                {
                                    "$ref":"1"
                                }
                            ],
                            "val":4
                        }
                    ],
                    "val":3
                }
            ],
            "val":2
        },
        {
            "$ref":"4"
        }
    ],
    "val":1
}
Explanation:
Node 1's value is 1, and it has two neighbors: Node 2 and 4.
Node 2's value is 2, and it has two neighbors: Node 1 and 3.
Node 3's value is 3, and it has two neighbors: Node 2 and 4.
Node 4's value is 4, and it has two neighbors: Node 1 and 3.


Note:

The number of nodes will be between 1 and 100.
The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
You must return the copy of the given node as a reference to the cloned graph.
 */
package exe133.clone.graph;

import java.util.ArrayList;

/**
 * @author Manfred since 2019/9/2
 */
public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());

        node1.neighbors.add(node2);
        node2.neighbors.add(node1);

        node2.neighbors.add(node3);
        node3.neighbors.add(node2);

        node3.neighbors.add(node4);
        node4.neighbors.add(node3);

        node4.neighbors.add(node1);
        node1.neighbors.add(node4);

        node2.neighbors.add(node4);
        node4.neighbors.add(node2);

        Node clone = new Solution().cloneGraph(node1);
        System.out.println(clone.val);
    }
}
