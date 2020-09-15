/*
Share
A linked list is given such that each node contains an additional random pointer
which could point to any node in the list or null.

Return a deep copy of the list.



Example 1:



Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.


Note:

You must return the copy of the given head as a reference to the cloned list.
 */
package exe138.copy.list.with.random.pointer;

/**
 * @author manfred on 2019/9/10.
 */
public class Main {
    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        node1.val = 1;
        node2.val = 2;

        node1.next = node2;
        node1.random = node2;
        node2.random = node2;

        Solution solution = new Solution();
        System.out.println(solution.copyRandomList(node1));

        Node node21 = new Node();
        Node node22 = new Node();

        node21.val = 1;
        node22.val = 2;

        node21.next = node22;
        node21.random = node22;
        node22.random = node22;

    }
}
