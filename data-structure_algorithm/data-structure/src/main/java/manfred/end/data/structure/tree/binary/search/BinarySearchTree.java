package manfred.end.data.structure.tree.binary.search;

/**
 * @author Manfred since 2019/8/20
 */
public class BinarySearchTree {
    private Node tree;

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    public void insert(int data) {
        if (null == tree) {
            tree = new Node(data);
            return;
        }

        Node cur = tree;
        while (true) {
            if (data > cur.data && cur.right == null) {
                cur.right = new Node(data);
                return;
            } else if (data <= cur.data && cur.left == null) {
                cur.left = new Node(data);
                return;
            } else if (data > cur.data) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
    }

    public void delete(int data) {
        Node p = tree;
        Node pp = null;
        while (p != null) {
            if (data < p.data) {
                pp = p;
                p = p.left;
            } else if (data > p.data) {
                pp = p;
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) {
            return;
        }

        if (null != p.left && null != p.right) {
            // 当前节点有两个子节点，则使用右子树的最小节点替换当前位置，并将删除节点变成右子树的最小节点
            Node minRight = p.right;
            Node minRightParent = p;
            while (null != minRight.left) {
                minRightParent = minRight;
                minRight = minRight.left;
            }
            p.data = minRight.data;

            // 删除节点变成了右子树的最小
            p = minRight;
            pp = minRightParent;
        }

        Node child = null;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        }

        if (pp == null) {
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }


    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
