package manfred.end.data.structure.tree.red.black.tree;

public class Boot {
    public static void main(String[] args) {
        RBTree<String> bst = new RBTree<String>();
        bst.addNode("d");
        bst.addNode("d");
        bst.printTree(bst.getRoot());

        bst.addNode("c");
        bst.addNode("c");
        bst.printTree(bst.getRoot());

        bst.addNode("b");
        bst.printTree(bst.getRoot());

        bst.addNode("f");
        bst.printTree(bst.getRoot());

        bst.addNode("a");
        bst.printTree(bst.getRoot());

        bst.addNode("e");
        bst.printTree(bst.getRoot());

        bst.addNode("g");
        bst.printTree(bst.getRoot());
        RBTreeShow.show(bst.getRoot());
        bst.addNode("h");
        bst.printTree(bst.getRoot());

        RBTreeShow.show(bst.getRoot());

        bst.remove("c");

        bst.printTree(bst.getRoot());
        RBTreeShow.show(bst.getRoot());
    }
}
