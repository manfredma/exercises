package manfred.end.data.structure.tree.red.black.tree;

public class Boot {
    public static void main(String[] args) {
        RBTree<String> bst = new RBTree<String>();
        bst.addNode("d");
        bst.addNode("d");

        bst.addNode("c");
        bst.addNode("c");

        bst.addNode("b");

        bst.addNode("f");

        bst.addNode("a");

        bst.addNode("e");

        bst.addNode("g");
        bst.addNode("h");
        RBTreeShow.show(bst.getRoot());
        System.out.println("删除节点 g ==================================");
        bst.remove("g");

        RBTreeShow.show(bst.getRoot());

        System.out.println("删除节点 h ==================================");
        bst.remove("h");

        RBTreeShow.show(bst.getRoot());

        System.out.println("删除节点 f ==================================");
        bst.remove("f");

        RBTreeShow.show(bst.getRoot());
    }
}
