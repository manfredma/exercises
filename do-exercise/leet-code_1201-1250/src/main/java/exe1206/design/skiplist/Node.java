package exe1206.design.skiplist;

class Node {

    Node next;

    Node down;

    int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{value=" + value + '}';
    }
}