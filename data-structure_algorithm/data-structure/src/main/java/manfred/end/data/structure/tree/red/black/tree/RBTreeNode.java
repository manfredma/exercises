package manfred.end.data.structure.tree.red.black.tree;

public class RBTreeNode<T> {


    public static final int RED = 1;
    public static final int BLACK = 0;

    public static RBTreeNode<?> LEAF_NODE = new RBTreeNode<>();

    static {
        LEAF_NODE.setColor(BLACK);
    }

    /**
     * 节点颜色
     */
    private int color = RED;

    /**
     * 存储的内容
     */
    private T key;


    private RBTreeNode<T> left = (RBTreeNode<T>) LEAF_NODE;

    private RBTreeNode<T> right = (RBTreeNode<T>) LEAF_NODE;

    private RBTreeNode<T> parent = null;

    private RBTreeNode() {

    }

    public RBTreeNode(T key) {
        this.key = key;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public RBTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(RBTreeNode<T> left) {
        this.left = left;
    }

    public RBTreeNode<T> getRight() {
        return right;
    }

    public void setRight(RBTreeNode<T> right) {
        this.right = right;
    }

    public boolean notLeafNode() {
        return this != LEAF_NODE;
    }


    public RBTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }

    public boolean isRed() {
        return color == RED;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }
}
