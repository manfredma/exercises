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
                String pos = !n.hasParent() ? "" : (n == n.getParent().getLeft()
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
        // y 待删除节点
        RBTreeNode<T> y = z;
        if (z.hasBothChildren()) {
            // 情景3：若删除结点有两个子结点，用后继结点（大于删除结点的最小结点）替换删除结点
            RBTreeNode<T> candidate = z.getRight();
            while (candidate.hasLeftChild()) {
                candidate = candidate.getLeft();
            }
            // 使用后继节点的内容替换当前节点的存储内容
            z.setKey(candidate.getKey());

            // 后继节点替换真实的删除节点
            y = candidate;
        }

        RBTreeNode<T> parent = y.getParent();
        // 替代节点：替换原节点的位置
        RBTreeNode<T> x;
        if (y.hasNoChildren()) {
            // 情景1：若删除结点无子结点，直接删除
            if (parent != RBTreeNode.NIL_NODE) {
                if (parent.getLeft() == y) {
                    parent.setLeft((RBTreeNode<T>) RBTreeNode.NIL_NODE);
                } else {
                    parent.setRight((RBTreeNode<T>) RBTreeNode.NIL_NODE);
                }
            }
            // 判断待删除节点是否是根节点，如果是根节点则将根节点直接删除即可：既没有子节点且自身是根节点，则说明树中只
            // 有这一个元素，则方法可以直接结束
            if (y == root) {
                root = null;
                return;
            }
            // FIXME：因为存在这种情况，所以后面处理的时候，不能直接使用 x.getParent
            x = (RBTreeNode<T>) RBTreeNode.NIL_NODE;
        } else {
            // 情景2：若删除结点只有一个子结点，用子结点替换删除结点
            if (y.hasLeftChild()) {
                if (parent != RBTreeNode.NIL_NODE) {
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
                if (parent != RBTreeNode.NIL_NODE) {
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
            // 重新设置替换节点的父节点信息（之前父节点为删除节点）
            x.setParent(parent);
        }

        if (y.isBlack()) {
            while (x != root && x.isBlack()) {
                // 情况说明：x是“黑+黑”节点，且x不是根。
                // 如果 x 是根节点，则不需要处理，整棵树的 黑高度 -1
                if (parent.getLeft() == x) {
                    // 如果 x 是黑节点 且 不是根节点，则说明 x 的兄弟节点必然存在！否则性质5就不满足
                    RBTreeNode<T> w = parent.getRight();
                    if (w.isRed()) {
                        // 因为删除了一个黑色节点，所以需要借用兄弟分支的红色节点来当做自己分支上的黑色节点
                        // 调整完成后，对红黑树的破坏没有加重！但是会把兄弟节点变为黑色，进行后面的处理！
                        w.setColor(RBTreeNode.BLACK);
                        // 因为 w 是红色，所以 parent 一定是黑色节点
                        parent.setColor(RBTreeNode.RED);
                        if (parent == root) {
                            // 如果父节点是根节点，则左旋以后
                            root = w;
                        }
                        leftRotate(parent);
                    } else {
                        //  兄弟节点的两个孩子都是黑节点（没有可以借用的红色节点）
                        //  左右子树的"黑"高度都减1（左子树因为删除黑色节点导致高度 -1，右子树则将兄弟节点变红）
                        //  对于父节点这颗子树来说相当于高度 -1，则需要父节点变为 黑+黑 继续处理
                        if (w.getLeft().isBlack() && w.getRight().isBlack()) {
                            w.setColor(RBTreeNode.RED);
                            x = parent;
                            parent = parent.getParent();
                            continue;
                        }

                        // x的兄弟节点是黑色；x的兄弟节点的左孩子是红色，右孩子是黑色的
                        if (w.getLeft().isRed() && w.getRight().isBlack()) {
                            w.getLeft().setColor(RBTreeNode.BLACK);
                            w.setColor(RBTreeNode.RED);
                            rightRotate(w);
                            // 上面右旋以后可能导致兄弟节点已经变化
                            w = parent.getRight();
                        }

                        // x的兄弟节点是黑色；x的兄弟节点的右孩子是红色的，x的兄弟节点的左孩子任意颜色
                        // 从右子树借一个黑色节点（兄弟节点），可以保证左子树的黑高度一样；
                        // 兄弟节点的左子树直接到原父节点的左子树；
                        w.setColor(parent.getColor());
                        parent.setColor(RBTreeNode.BLACK);
                        w.getRight().setColor(RBTreeNode.BLACK);
                        if (parent == root) {
                            // 如果父节点是根节点，则左旋以后
                            root = w;
                        }
                        leftRotate(parent);

                        // 至此已经处理完成，不需要继续处理
                        break;
                    }
                } else {
                    // 如果 x 是黑节点 且 不是根节点，则说明 x 的兄弟节点必然存在！否则性质5就不满足
                    RBTreeNode<T> w = parent.getLeft();
                    if (w.isRed()) {
                        parent.setColor(RBTreeNode.RED);
                        w.setColor(RBTreeNode.BLACK);
                        // 因为 w 是红色，所以 parent 一定是黑色节点
                        if (parent == root) {
                            // 如果父节点是根节点，则左旋以后
                            root = w;
                        }
                        rightRotate(parent);
                    } else {
                        if (w.getRight().isBlack() && w.getLeft().isBlack()) {
                            x = parent;
                            w.setColor(RBTreeNode.RED);
                            parent = parent.getParent();
                            continue;
                        }

                        if (w.getRight().isRed() && w.getLeft().isBlack()) {
                            w.getRight().setColor(RBTreeNode.BLACK);
                            w.setColor(RBTreeNode.RED);
                            leftRotate(w);
                            w = parent.getLeft();
                        }

                        w.setColor(parent.getColor());
                        parent.setColor(RBTreeNode.BLACK);
                        w.getLeft().setColor(RBTreeNode.BLACK);
                        if (parent == root) {
                            // 如果父节点是根节点，则左旋以后
                            root = w;
                        }
                        rightRotate(parent);

                        // 至此已经处理完成，不需要继续处理
                        break;
                    }
                }
            }
        }
        x.setColor(RBTreeNode.BLACK);
    }
}
