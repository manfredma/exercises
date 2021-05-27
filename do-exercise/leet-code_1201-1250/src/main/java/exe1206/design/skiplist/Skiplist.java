package exe1206.design.skiplist;

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

    private Node head;

    private double promoteRate = 0.2;

    public Skiplist() {

    }

    public boolean search(int target) {
        return find(target) != null;
    }

    private Node find(int target) {
        Node current = head;
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

    private Node[] findWithUpLeft(int target) {
        Node upLeft = null;
        Node current = head;
        while (current != null) {
            if (current.value == target) {
                return new Node[]{upLeft, current};
            } else if (current.value < target) {
                if (current.next == null || current.next.value > target) {
                    upLeft = current;
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
        if (head != null) {
            if (head.value > num) {
                Node insertNode = new Node(num);
                Node next = head;
                head = insertNode;
                while (next != null) {
                    insertNode.next = next;
                    if (next.down != null) {
                        Node down = new Node(num);
                        insertNode.down = down;
                        insertNode = down;
                        next = next.down;
                    } else {
                        next = null;
                    }
                }
            } else {
                Node current = head;
                while (current != null) {
                    if (current.next != null) {
                        if (current.next.value < num) {
                            current = current.next;
                        } else {
                            Node up = new Node(num);
                            while (current != null) {
                                Node insertNode = new Node(num);
                                insertNode.next = current.next;
                                current.next = insertNode;

                                up.down = insertNode;
                                current = current.down;
                            }
                        }
                    } else {
                        if (current.down == null) {
                            current.next = new Node(num);
                            current = null;
                        } else {
                            current = current.down;
                        }
                    }
                }
            }
        } else {
            head = new Node(num);
        }
        if (Math.random() < promoteRate) {
            promote(num);
        }
    }

    private void promote(int num) {
        Node[] findWithUpLeft = findWithUpLeft(num);
        if (findWithUpLeft != null) {
            if (findWithUpLeft[0] != null) {
                // 不需要新增加一层
                Node current = new Node(num);
                current.next = findWithUpLeft[0].next;
                findWithUpLeft[0].next = current;
                current.down = findWithUpLeft[1];
            } else {
                if (num != head.value) {
                    // 新增加一层
                    Node newHead = new Node(head.value);
                    Node current = new Node(findWithUpLeft[1].value);
                    newHead.next = current;

                    newHead.down = head;
                    current.down = findWithUpLeft[1];

                    head = newHead;
                }
            }
        }
    }

    public boolean erase(int num) {
        Node current = head;
        Node before = null;
        while (current != null) {
            if (current.value == num) {
                break;
            } else if (current.value < num) {
                if (current.next == null || current.next.value > num) {
                    current = current.down;
                } else {
                    before = current;
                    current = current.next;
                }
            } else {
                break;
            }
        }
        if (current != null && current.value == num) {
            if (before != null) {
                // 删除非根节点
                while (current != null) {
                    before.next = current.next;
                    current = current.down;
                    before = before.down;
                }
            } else {
                // 删除根节点，只需要将head节点右移一位即可
                // 如果删除head节点后，这一层没有其他元素，则删除这一层
                while (head != null && head.next == null) {
                    head = head.down;
                }

                // 这个删除head节点，需要寻找次小节点来代替
                if (head != null) {
                    head = recursiveEraseHead(head);
                }
            }
            return true;
        }
        return false;
    }

    private Node recursiveEraseHead(Node head) {
        if (head.down != null) {
            Node downHead = recursiveEraseHead(head.down);
            if (head.next.value != downHead.value) {
                Node newHead = new Node(downHead.value);
                newHead.next = head.next;
                newHead.down = downHead;
                return newHead;
            } else {
                return head.next;
            }

        } else {
            // 最底层直接使用 next 做的新的头部节点
            return head.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node currentHead = head;
        while (currentHead != null) {
            Node current = currentHead;
            while (current != null) {
                sb.append(current.value);
                if (currentHead.down == null && current.down != null) {
                    sb.append("(错误：down=").append(current.down.value).append(")");
                }
                sb.append(" -> ");
                current = current.next;
            }
            sb.append("NIL\n");
            currentHead = currentHead.down;
        }
        return sb.toString();
    }
}

class Node {

    Node next;

    Node down;

    int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", down=" + down +
                ", value=" + value +
                '}';
    }
}