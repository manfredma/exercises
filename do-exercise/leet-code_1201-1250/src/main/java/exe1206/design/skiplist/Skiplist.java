package exe1206.design.skiplist;

import java.util.Arrays;
import java.util.Random;

/**
 * Design a Skiplist without using any built-in libraries.
 * <p>
 * A Skiplist is a data structure that takes O(log(n)) time to add, erase and search. Comparing
 * with treap and red-black tree which has the same function and performance, the code length of
 * Skiplist can be comparatively short and the idea behind Skiplists are just simple linked lists.
 * <p>
 * For example: we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45
 * into it. The Skiplist works this way:
 * <p>
 * <img src="doc-files/1506_skiplist.gif" height="90" width="462"/>
 * <p>
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With
 * the help of the top layers, add , erase and search can be faster than O(n). It can be proven
 * that the average time complexity for each operation is O(log(n)) and space complexity is O(n).
 * <p>
 * To be specific, your design should include these functions:
 * <p>
 * bool search(int target) : Return whether the target exists in the Skiplist or not.
 * void add(int num): Insert a value into the SkipList.
 * bool erase(int num): Remove a value in the Skiplist. If num does not exist in the Skiplist, do
 * nothing and return false. If there exists multiple num values, removing any one of them is fine.
 * See more about Skiplist : https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
 * <p>
 * Skiplist skiplist = new Skiplist();
 * <p>
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // return false.
 * skiplist.add(4);
 * skiplist.search(1);   // return true.
 * skiplist.erase(0);    // return false, 0 is not in skiplist.
 * skiplist.erase(1);    // return true.
 * skiplist.search(1);   // return false, 1 has already been erased.
 * <p>
 * onstraints:
 * <p>
 * 0 <= num, target <= 20000
 * At most 50000 calls will be made to search, add, and erase.
 */
class Skiplist {

    private Node[] heads = new Node[MAX_LEVEL];

    private static final int MAX_LEVEL = 16;

    public Skiplist() {
        for (int i = 0; i < MAX_LEVEL; i++) {
            heads[i] = new Node(Integer.MIN_VALUE);
        }

        for (int i = 0; i < MAX_LEVEL - 1; i++) {
            heads[i].down = heads[i + 1];
        }
    }

    public boolean search(int target) {
        return find(target) != null;
    }

    private Node find(int target) {
        Node current = heads[0];
        while (current != null) {
            if (current.value == target) {
                return current;
            } else if (current.value < target) {
                if (current.next == null || current.next.value > target) {
                    current = current.down;
                } else {
                    current = current.next;
                }
            } else {
                break;
            }
        }
        return null;
    }


    public void add(int num) {
        // 1. 计算在第几层
        int level = randomLevel();

        // 2. 获取第level层的根节点
        Node fromSearch = heads[0];
        for (int i = 0; i < level; i++) {
            fromSearch = searchInsertNode(fromSearch, new Node(num));
            fromSearch = fromSearch.down;
        }
        Node preInsertNode = null;
        for (int i = level; i < MAX_LEVEL; i++) {
            Node insertNode = new Node(num);
            fromSearch = addNode(fromSearch, insertNode);
            fromSearch = fromSearch.down;
            if (preInsertNode != null) {
                preInsertNode.down = insertNode;
            }
            preInsertNode = insertNode;
        }
    }

    private Node addNode(Node fromSearch, Node addingNode) {
        Node added = searchInsertNode(fromSearch, addingNode);
        addingNode.next = added.next;
        added.next = addingNode;
        return added;
    }

    private Node searchInsertNode(Node fromSearch, Node addingNode) {
        Node added = fromSearch;
        while (added.next != null && added.next.value < addingNode.value) {
            added = added.next;
        }
        return added;
    }


    public boolean erase(int num) {
        boolean result = false;
        Node current = heads[0];
        while (current != null) {
            if (current.next != null) {
                if (current.next.value == num) {
                    result = true;
                    // 当前节点直接指向下一节点来实现删除；
                    current.next = current.next.next;
                    current = current.down; // 处理下一层（因为是单向链表，故只能通过目标节点的前一节点下降下去）
                } else if (current.next.value < num) {
                    current = current.next;
                } else {
                    current = current.down;
                }
            } else {
                current = current.down;
            }
        }
        return result;
    }

    private int randomLevel() {
        return Math.abs(new Random().nextInt()) % MAX_LEVEL;
    }

    @Override
    public String toString() {
        StringBuffer value = new StringBuffer();
        Arrays.stream(heads).forEach(a -> {
            Node x = a;
            while (x.next != null) {
                value.append(x.next.value).append(" -> ");
                x = x.next;
            }
            value.append('\n');
        });
        return value.toString();
    }
}

