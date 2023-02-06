package manfred.end.tree;

public class GavTextVisitor implements TreeNodeVisitor {

    // private static final GavTextVisitor INSTANCE = new GavTextVisitor();

    public GavTextVisitor() {
    }

    // public static TreeNodeVisitor getInstance() {
    //     return INSTANCE;
    // }

    @Override
    public void visit(MavenDependencyTreeNode node) {
        node.setText(node.getPlainText());
    }

    @Override
    public void visit(MavenProjectTreeNode node) {
        node.setText(node.getPlainText());
    }

}
