package manfred.end.data.structure.tree.red.black.tree;

import java.util.Optional;

public class RBTree<T extends Comparable> {

    private RBTreeNode<T> root;

    public RBTree(T key) {
        initial(key);
    }

    public RBTree() {
    }

    public RBTreeNode<T> getRoot() {
        return root;
    }

    public Optional<RBTreeNode<T>> find(T key) {
        if (root == null) {
            return Optional.empty();
        }

        RBTreeNode<T> current = root;
        while (current.notLeafNode()) {
            if (current.getKey().compareTo(key) == 0) {
                return Optional.of(current);
            } else if (current.getKey().compareTo(key) < 0) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return Optional.empty();
    }

    public void addNode(T key) {
        if (root == null) {
            initial(key);
            return;
        }

        // 插入元素
        RBTreeNode<T> current = root;
        RBTreeNode<T> parent = null;
        while (current.notLeafNode()) {
            if (current.getKey().compareTo(key) == 0) {
                // 树中已存在相同元素，不处理（实际应用中可能需要覆盖节点中承载的内容）
                return;
            } else if (current.getKey().compareTo(key) < 0) {
                parent = current;
                current = current.getRight();
            } else {
                parent = current;
                current = current.getLeft();
            }
        }
        RBTreeNode<T> insertNode = new RBTreeNode<>(key);
        insertNode.setParent(parent);
        if (parent.getKey().compareTo(key) < 0) {
            parent.setRight(insertNode);
        } else {
            parent.setLeft(insertNode);
        }

        // 自平衡
        selfBalance(insertNode);

        // 重新寻找根节点
        while (root.getParent() != null) {
            root = root.getParent();
        }
    }

    private void selfBalance(RBTreeNode<T> insertNode) {

        // 如果插入节点是根节点，则变更颜色后直接返回
        if (insertNode.getParent() == null) {
            insertNode.setColor(RBTreeNode.BLACK);
            return;
        }

        RBTreeNode<T> parent = insertNode.getParent();
        // 插入情景4：插入结点的父结点为红结点
        if (parent.isRed()) {
            RBTreeNode<T> pp = parent.getParent();
            RBTreeNode<T> s = pp.getLeft() == parent ? pp.getRight() : pp.getLeft();
            if (s.isRed()) {
                parent.setColor(RBTreeNode.BLACK);
                s.setColor(RBTreeNode.BLACK);
                pp.setColor(RBTreeNode.RED);
                selfBalance(pp);
            } else {
                if (pp.getLeft() == parent) {
                    if (parent.getRight() == insertNode) {
                        // 插入情景4.2：叔叔结点不存在或为黑结点，并且插入结点的父亲结点是祖父结点的左子结点
                        // 插入情景4.2.2：插入结点是其父结点的右子结点
                        //    对P进行左旋
                        //    把P设置为插入结点，得到情景4.2.1
                        //    进行情景4.2.1的处理
                        leftRotate(parent);
                    }
                    // 插入情景4.2：叔叔结点不存在或为黑结点，并且插入结点的父亲结点是祖父结点的左子结点
                    // 插入情景4.2.1：插入结点是其父结点的左子结点
                    //    将P设为黑色
                    //    将PP设为红色
                    //    对PP进行右旋
                    pp.getLeft().setColor(RBTreeNode.BLACK);
                    pp.setColor(RBTreeNode.RED);
                    rightRotate(pp);
                } else {
                    if (pp.getLeft() == parent) {
                        // 插入情景4.3：叔叔结点不存在或为黑结点，并且插入结点的父亲结点是祖父结点的右子结点
                        // 插入情景4.3.2：插入结点是其父结点的左子结点
                        //    对P进行右旋
                        //    把P设置为插入结点，得到情景4.3.1
                        //    进行情景4.3.1的处理
                        rightRotate(parent);
                    }
                    // 插入情景4.3：叔叔结点不存在或为黑结点，并且插入结点的父亲结点是祖父结点的右子结点
                    // 插入情景4.3.1：插入结点是其父结点的右子结点
                    //    将P设为黑色
                    //    将PP设为红色
                    //    对PP进行左旋
                    pp.getRight().setColor(RBTreeNode.BLACK);
                    pp.setColor(RBTreeNode.RED);
                    leftRotate(pp);
                }
            }
        }
    }

    private void rightRotate(RBTreeNode<T> current) {
        RBTreeNode<T> parent = current.getParent();

        RBTreeNode<T> oldLeft = current.getLeft();
        RBTreeNode<T> oldLeftRight = oldLeft.getRight();

        current.setParent(oldLeft);
        current.setLeft(oldLeftRight);

        oldLeft.setParent(parent);
        oldLeft.setRight(current);

        if (oldLeftRight.notLeafNode()) {
            oldLeftRight.setParent(current);
        }
        if (parent != null) {
            if (parent.getLeft() == current) {
                parent.setLeft(oldLeft);
            } else {
                parent.setRight(oldLeft);
            }
        }
    }

    private void leftRotate(RBTreeNode<T> current) {
        RBTreeNode<T> parent = current.getParent();

        RBTreeNode<T> oldRight = current.getRight();
        RBTreeNode<T> oldRightLeft = oldRight.getLeft();

        current.setParent(oldRight);
        current.setRight(oldRightLeft);

        oldRight.setParent(parent);
        oldRight.setLeft(current);

        if (oldRightLeft.notLeafNode()) {
            oldRightLeft.setParent(current);
        }

        if (parent != null) {
            if (parent.getLeft() == current) {
                parent.setLeft(oldRight);
            } else {
                parent.setRight(oldRight);
            }
        }
    }

    private void initial(T key) {
        this.root = new RBTreeNode<>(key);
        this.root.setColor(RBTreeNode.BLACK);
    }

    /**
     * debug method,it used print the given node and its children nodes,
     * every layer output in one line
     *
     * @param root
     */
    public void printTree(RBTreeNode<T> root) {
        System.out.println("\nRBTree is ----------------------------------------");
        java.util.LinkedList<RBTreeNode<T>> queue = new java.util.LinkedList<>();
        java.util.LinkedList<RBTreeNode<T>> queue2 = new java.util.LinkedList<>();
        if (root == null) {
            return;
        }
        queue.add(root);
        boolean firstQueue = true;

        while (!queue.isEmpty() || !queue2.isEmpty()) {
            java.util.LinkedList<RBTreeNode<T>> q = firstQueue ? queue : queue2;
            RBTreeNode<T> n = q.poll();

            if (n != null) {
                String pos = n.getParent() == null ? "" : (n == n.getParent().getLeft()
                        ? " LE" : " RI");
                String pstr = n.getParent() == null ? "" : n.getParent().toString();
                String cstr = n.isRed() ? "R" : "B";
                cstr = n.getParent() == null ? cstr : cstr + " ";
                System.out.print(n + "(" + (cstr) + pstr + (pos) + ")" + "\t");
                if (n.getLeft() != null && n.getLeft().notLeafNode()) {
                    (firstQueue ? queue2 : queue).add(n.getLeft());
                }
                if (n.getRight() != null && n.getRight().notLeafNode()) {
                    (firstQueue ? queue2 : queue).add(n.getRight());
                }
            } else {
                System.out.println();
                firstQueue = !firstQueue;
            }
        }

        System.out.println("\nEnd RBTree ----------------------------------------");
    }



    public void remove(T key) {
        Optional<RBTreeNode<T>> find = find(key);
        find.ifPresent(this::remove);
    }

    private void remove(RBTreeNode<T> removeRBTreeNode) {
        RBTreeNode<T> parent = removeRBTreeNode.getParent();
        if (!removeRBTreeNode.getLeft().notLeafNode() &&
                !removeRBTreeNode.getRight().notLeafNode()) {
            // 情景1：若删除结点无子结点，直接删除
            if (parent != null) {
                if (parent.getLeft() == removeRBTreeNode) {
                    parent.setLeft((RBTreeNode<T>) RBTreeNode.LEAF_NODE);
                } else {
                    parent.setRight((RBTreeNode<T>) RBTreeNode.LEAF_NODE);
                }
            } else {
                this.root = null;
            }
        } else if (removeRBTreeNode.getLeft().notLeafNode() &&
                removeRBTreeNode.getRight().notLeafNode()) {
            // 情景3：若删除结点有两个子结点，用后继结点（大于删除结点的最小结点）替换删除结点
            RBTreeNode<T> candidate = removeRBTreeNode.getRight();
            while (candidate.getLeft().notLeafNode()) {
                candidate = candidate.getLeft();
            }


            // 把节点关系断开
            candidate.setParent(null);
            if (candidate.getParent().getRight() == candidate) {
                // candidate.getParent().setRight();
            }

        } else {
            // 情景2：若删除结点只有一个子结点，用子结点替换删除结点
            if (removeRBTreeNode.getLeft().notLeafNode()) {
                if (parent != null) {
                    if (parent.getLeft() == removeRBTreeNode) {
                        parent.setLeft(removeRBTreeNode.getLeft());
                    } else {
                        parent.setRight(removeRBTreeNode.getLeft());
                    }
                } else {
                    this.root = removeRBTreeNode.getLeft();
                }
            } else {
                if (parent != null) {
                    if (parent.getLeft() == removeRBTreeNode) {
                        parent.setLeft(removeRBTreeNode.getRight());
                    } else {
                        parent.setRight(removeRBTreeNode.getRight());
                    }
                } else {
                    this.root = removeRBTreeNode.getRight();
                }
            }
        }
    }
}
