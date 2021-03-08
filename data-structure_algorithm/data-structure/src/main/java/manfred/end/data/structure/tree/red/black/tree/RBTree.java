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
        // RB-DELETE-FIXUP(T, x)
        // 01 while x ≠ root[T] and color[x] = BLACK
        // 02     do if x = left[p[x]]
        // 03           then w ← right[p[x]]                                             // 若 “x”是“它父节点的左孩子”，则设置 “w”为“x的兄弟节点”(即x为它父节点的右孩子)
        // 04                if color[w] = RED                                           // Case 1: x是“黑+黑”节点，x的兄弟节点是红色。(此时x的父节点和x的兄弟节点的子节点都是黑节点)。
        // 05                   then color[w] ← BLACK                        ▹  Case 1   //   (01) 将x的兄弟节点设为“黑色”。
        // 06                        color[p[x]] ← RED                       ▹  Case 1   //   (02) 将x的父节点设为“红色”。
        // 07                        LEFT-ROTATE(T, p[x])                    ▹  Case 1   //   (03) 对x的父节点进行左旋。
        // 08                        w ← right[p[x]]                         ▹  Case 1   //   (04) 左旋后，重新设置x的兄弟节点。
        // 09                if color[left[w]] = BLACK and color[right[w]] = BLACK       // Case 2: x是“黑+黑”节点，x的兄弟节点是黑色，x的兄弟节点的两个孩子都是黑色。
        // 10                   then color[w] ← RED                          ▹  Case 2   //   (01) 将x的兄弟节点设为“红色”。
        // 11                        x ←  p[x]                               ▹  Case 2   //   (02) 设置“x的父节点”为“新的x节点”。
        // 12                   else if color[right[w]] = BLACK                          // Case 3: x是“黑+黑”节点，x的兄弟节点是黑色；x的兄弟节点的左孩子是红色，右孩子是黑色的。
        // 13                           then color[left[w]] ← BLACK          ▹  Case 3   //   (01) 将x兄弟节点的左孩子设为“黑色”。
        // 14                                color[w] ← RED                  ▹  Case 3   //   (02) 将x兄弟节点设为“红色”。
        // 15                                RIGHT-ROTATE(T, w)              ▹  Case 3   //   (03) 对x的兄弟节点进行右旋。
        // 16                                w ← right[p[x]]                 ▹  Case 3   //   (04) 右旋后，重新设置x的兄弟节点。
        // 17                         color[w] ← color[p[x]]                 ▹  Case 4   // Case 4: x是“黑+黑”节点，x的兄弟节点是黑色；x的兄弟节点的右孩子是红色的。(01) 将x父节点颜色 赋值给 x的兄弟节点。
        // 18                         color[p[x]] ← BLACK                    ▹  Case 4   //   (02) 将x父节点设为“黑色”。
        // 19                         color[right[w]] ← BLACK                ▹  Case 4   //   (03) 将x兄弟节点的右子节设为“黑色”。
        // 20                         LEFT-ROTATE(T, p[x])                   ▹  Case 4   //   (04) 对x的父节点进行左旋。
        // 21                         x ← root[T]                            ▹  Case 4   //   (05) 设置“x”为“根节点”。
        // 22        else (same as then clause with "right" and "left" exchanged)        // 若 “x”是“它父节点的右孩子”，将上面的操作中“right”和“left”交换位置，然后依次执行。
        // 23 color[x] ← BLACK
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
            while (x.hasParent() && !x.isRed()) {
                // ③ 情况说明：x是“黑+黑”节点，且x不是根。
                //    处理方法：这种情况又可以划分为4种子情况。这4种子情况如下表所示：
                if (x.getParent().getLeft() == x) {
                    RBTreeNode<T> w = x.getParent().getRight();
                    if (w.isRed()) {
                        // Case 1	x是"黑+黑"节点，x的兄弟节点是红色。(此时x的父节点和x的兄弟节点的子节点都是黑节点)。
                        //          (01) 将x的兄弟节点设为“黑色”。
                        //          (02) 将x的父节点设为“红色”。
                        //          (03) 对x的父节点进行左旋。
                        //          (04) 左旋后，重新设置x的兄弟节点。
                        w.setColor(RBTreeNode.BLACK);
                        x.getParent().setColor(RBTreeNode.RED);
                        // FIXME manfred 2021/03/08 如果父节点是根，左旋逻辑存在问题
                        leftRotate(x.getParent());
                    } else {
                        // 2. (Case 2) x是"黑+黑"节点，x的兄弟节点是黑色，x的兄弟节点的两个孩子都是黑色
                        //
                        // 2.1 现象说明
                        // x是“黑+黑”节点，x的兄弟节点是黑色，x的兄弟节点的两个孩子都是黑色。
                        //
                        // 2.2 处理策略
                        // (01) 将x的兄弟节点设为“红色”。
                        // (02) 设置“x的父节点”为“新的x节点”。
                        //      下面谈谈为什么要这样处理。
                        //      这个情况的处理思想：是将“x中多余的一个黑色属性上移(往根方向移动)”。
                        //      x是“黑+黑”节点，我们将x由“黑+黑”节点 变成 “黑”节点，多余的一个“黑”属性移到x的父
                        //      节点中，即x的父节点多出了一个黑属性(若x的父节点原先是“黑”，则此时变成了“黑+黑”；
                        //      若x的父节点原先时“红”，则此时变成了“红+黑”)。 此时，需要注意的是：所有经过x的分支
                        //      中黑节点个数没变化；但是，所有经过x的兄弟节点的分支中黑色节点的个数增加了1(因为x的
                        //      父节点多了一个黑色属性)！为了解决这个问题，我们需要将“所有经过x的兄弟节点的分支中黑
                        //      色节点的个数减1”即可，那么就可以通过“将x的兄弟节点由黑色变成红色”来实现。
                        //      经过上面的步骤(将x的兄弟节点设为红色)，多余的一个颜色属性(黑色)已经跑到x的父节点
                        //      中。我们需要将x的父节点设为“新的x节点”进行处理。若“新的x节点”是“黑+红”，直接将“新
                        //      的x节点”设为黑色，即可完全解决该问题；若“新的x节点”是“黑+黑”，则需要对“新的x节点”
                        //      进行进一步处理。
                        if (!w.getLeft().isRed() && !w.getRight().isRed()) {
                            w.setColor(RBTreeNode.RED);
                            x = x.getParent();
                        }

                        // 3. (Case 3)x是“黑+黑”节点，x的兄弟节点是黑色；x的兄弟节点的左孩子是红色，右孩子是黑色的
                        //
                        // 3.1 现象说明
                        // x是“黑+黑”节点，x的兄弟节点是黑色；x的兄弟节点的左孩子是红色，右孩子是黑色的。
                        //
                        // 3.2 处理策略
                        // (01) 将x兄弟节点的左孩子设为“黑色”。
                        // (02) 将x兄弟节点设为“红色”。
                        // (03) 对x的兄弟节点进行右旋。
                        // (04) 右旋后，重新设置x的兄弟节点。
                        //
                        //       下面谈谈为什么要这样处理。
                        //       我们处理“Case 3”的目的是为了将“Case 3”进行转换，转换成“Case 4”,从而进行进一步
                        //       的处理。转换的方式是对x的兄弟节点进行右旋；为了保证右旋后，它仍然是红黑树，就需要在
                        //       右旋前“将x的兄弟节点的左孩子设为黑色”，同时“将x的兄弟节点设为红色”；右旋后，由于x
                        //       的兄弟节点发生了变化，需要更新x的兄弟节点，从而进行后续处理。
                        if (w.getLeft().isRed() && !w.getRight().isRed()) {
                            w.getLeft().setColor(RBTreeNode.BLACK);
                            w.setColor(RBTreeNode.RED);

                        }


                        // 4. (Case 4)x是“黑+黑”节点，x的兄弟节点是黑色；x的兄弟节点的右孩子是红色的，x的兄弟节点的左孩子任意颜色
                        //
                        // 4.1 现象说明
                        // x是“黑+黑”节点，x的兄弟节点是黑色；x的兄弟节点的右孩子是红色的，x的兄弟节点的左孩子任意颜色。
                        //
                        // 4.2 处理策略
                        // (01) 将x父节点颜色 赋值给 x的兄弟节点。
                        // (02) 将x父节点设为“黑色”。
                        // (03) 将x兄弟节点的右子节设为“黑色”。
                        // (04) 对x的父节点进行左旋。
                        // (05) 设置“x”为“根节点”。
                        //
                        //      下面谈谈为什么要这样处理。(建议理解的时候，通过下面的图进行对比)
                        //      我们处理“Case 4”的目的是：去掉x中额外的黑色，将x变成单独的黑色。处理的方式是“：进
                        //      行颜色修改，然后对x的父节点进行左旋。下面，我们来分析是如何实现的。
                        //      为了便于说明，我们设置“当前节点”为S(Original Son)，“兄弟节点”为B(Brother)，“
                        //      兄弟节点的左孩子”为BLS(Brother's Left Son)，“兄弟节点的右孩子”为
                        //      BRS(Brother's Right Son)，“父节点”为F(Father)。
                        //      我们要对F进行左旋。但在左旋前，我们需要调换F和B的颜色，并设置BRS为黑色。为什么需要
                        //      这里处理呢？因为左旋后，F和BLS是父子关系，而我们已知BL是红色，如果F是红色，则违背
                        //      了“特性(4)”；为了解决这一问题，我们将“F设置为黑色”。 但是，F设置为黑色之后，为了
                        //      保证满足“特性(5)”，即为了保证左旋之后：
                        //      第一，“同时经过根节点和S的分支的黑色节点个数不变”。
                        //             若满足“第一”，只需要S丢弃它多余的颜色即可。因为S的颜色是“黑+黑”，而左旋后
                        //             “同时经过根节点和S的分支的黑色节点个数”增加了1；现在，只需将S由“黑+黑”变
                        //             成单独的“黑”节点，即可满足“第一”。
                        //      第二，“同时经过根节点和BLS的分支的黑色节点数不变”。
                        //             若满足“第二”，只需要将“F的原始颜色”赋值给B即可。之前，我们已经将“F设置为
                        //             黑色”(即，将B的颜色"黑色"，赋值给了F)。至此，我们算是调换了F和B的颜色。
                        //      第三，“同时经过根节点和BRS的分支的黑色节点数不变”。
                        //             在“第二”已经满足的情况下，若要满足“第三”，只需要将BRS设置为“黑色”即可。
                        // 经过，上面的处理之后。红黑树的特性全部得到的满足！接着，我们将x设为根节点，就可以跳出while
                        // 循环(参考伪代码)；即完成了全部处理。
                        //
                        // 至此，我们就完成了Case 4的处理。理解Case 4的核心，是了解如何“去掉当前节点额外的黑色”。

                    }
                } else {

                }
            }
        }
        x.setColor(RBTreeNode.BLACK);
    }

}
