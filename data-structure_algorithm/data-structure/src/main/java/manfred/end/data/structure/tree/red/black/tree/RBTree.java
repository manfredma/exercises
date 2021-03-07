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
        while (root.hasParent()) {
            root = root.getParent();
        }
    }

    private void selfBalance(RBTreeNode<T> insertNode) {

        // 如果插入节点是根节点，则变更颜色后直接返回
        if (!insertNode.hasParent()) {
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
                String pos = n.hasParent() ? "" : (n == n.getParent().getLeft()
                        ? " LE" : " RI");
                String pstr = n.hasParent() ? "" : n.getParent().toString();
                String cstr = n.isRed() ? "R" : "B";
                cstr = n.hasParent() ? cstr : cstr + " ";
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

    private void remove(RBTreeNode<T> z) {

        // 第一步：将红黑树当作一颗二叉查找树，将节点删除。
        RBTreeNode<T> y = z;
        if (z.hasBothChildren()) {
            // 情景3：若删除结点有两个子结点，用后继结点（大于删除结点的最小结点）替换删除结点
            RBTreeNode<T> candidate = z.getRight();
            while (candidate.hasLeftChild()) {
                candidate = candidate.getLeft();
            }
            // 使用后继节点的内容替换当前节点的存储内容
            z.setKey(candidate.getKey());

            // 后继节点作为删除节点
            y = candidate;
        }


        RBTreeNode<T> parent = y.getParent();
        // 替代节点：替换原节点的位置
        RBTreeNode<T> x = null;
        if (y.hasNoChildren()) {
            // 情景1：若删除结点无子结点，直接删除
            if (parent != null) {
                if (parent.getLeft() == y) {
                    parent.setLeft((RBTreeNode<T>) RBTreeNode.NIL_NODE);
                } else {
                    parent.setRight((RBTreeNode<T>) RBTreeNode.NIL_NODE);
                }
            }
            // 判断待删除节点是否是根节点，如果是根节点则将根节点置空
            // 既没有子节点且自身是根节点，则说明树中只有这一个元素，则函数可以直接结束
            if (y == root) {
                root = null;
                return;
            }
            x = (RBTreeNode<T>) RBTreeNode.NIL_NODE;
        } else {
            // 情景2：若删除结点只有一个子结点，用子结点替换删除结点
            if (y.hasLeftChild()) {
                if (parent != null) {
                    if (parent.getLeft() == y) {
                        parent.setLeft(y.getLeft());
                    } else {
                        parent.setRight(y.getLeft());
                    }
                } else {
                    this.root = y.getLeft();
                }
                x = y.getLeft();
            } else {
                if (parent != null) {
                    if (parent.getLeft() == y) {
                        parent.setLeft(y.getRight());
                    } else {
                        parent.setRight(y.getRight());
                    }
                } else {
                    this.root = y.getRight();
                }
                x = y.getRight();
            }
            // 设置父节点
            x.setParent(parent);
        }

        // 第二步：通过"旋转和重新着色"等一系列来修正该树，使之重新成为一棵红黑树。
        if (!y.isRed()) {
            // 如果删除节点是非红色，可能会导致红黑树的约束条件中的约束2，4，5 失效
            /*
             * 性质1：每个节点要么是黑色，要么是红色。
             * 性质2：根节点是黑色。
             * 性质3：每个叶子节点（NIL）是黑色。
             * 性质4：每个红色结点的两个子结点一定都是黑色。
             * 性质5：任意一结点到每个叶子结点的路径都包含数量相同的黑结点。
             */
            /* 思路：
             * 删除节点y之后，x占据了原来节点y的位置。 既然删除y(y是黑色)，意味着减少一个黑色节点；
             * 那么，再在该位置上增加一个黑色即可。
             * 这样，当我们假设"x包含一个额外的黑色"，就正好弥补了"删除y所丢失的黑色节点"，也就不会违反"特性(5)"。
             * 因此，假设"x包含一个额外的黑色"(x原本的颜色还存在)，这样就不会违反"特性(5)"。
             * 现在，x不仅包含它原本的颜色属性，x还包含一个额外的黑色。即x的颜色属性是"红+黑"或"黑+黑"，它违反了"特性(1)"。
             * 现在，我们面临的问题，由解决"违反了特性(2)、(4)、(5)三个特性"转换成了"解决违反特性(1)、(2)、(4)三个特性"。
             * 需要做的就是通过算法恢复红黑树的特性(1)、(2)、(4)。
             */
            // ① 情况说明：x是“红+黑”节点。
            //    处理方法：直接把x设为黑色，结束。此时红黑树性质全部恢复。
            if (x.isRed()) {
                x.setColor(RBTreeNode.BLACK);
            } else if (x.hasParent()){
                // ③ 情况说明：x是“黑+黑”节点，且x不是根。
                //    处理方法：这种情况又可以划分为4种子情况。这4种子情况如下表所示：

                // Case 1	x是"黑+黑"节点，x的兄弟节点是红色。(此时x的父节点和x的兄弟节点的子节点都是黑节点)。
                //          (01) 将x的兄弟节点设为“黑色”。
                //          (02) 将x的父节点设为“红色”。
                //          (03) 对x的父节点进行左旋。
                //          (04) 左旋后，重新设置x的兄弟节点。
                if (x.getParent().getLeft() == x) {
                    RBTreeNode<T> w = x.getParent().getRight();
                    if (w.isRed()) {

                    }
                } else {

                }



            } else {
                // ② 情况说明：x是“黑+黑”节点，且x是根。
                //    处理方法：什么都不做，结束。此时红黑树性质全部恢复。
            }
        }

    }
}
