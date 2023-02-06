package manfred.end.tree;

public interface TreeNodeVisitor {

    void visit(MavenDependencyTreeNode node);

    void visit(MavenProjectTreeNode node);

}
