package exe138.copy.list.with.random.pointer;

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val, Node _next, Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }

    @Override
    public String toString() {
        return "val = " + val + ", next = " + (null != next ? next.val : null) + ", random = " + (random != null ? random.val : null);
    }
};