package manfred.end.tree;

import java.util.StringJoiner;
import javax.annotation.Nonnull;
import org.apache.maven.model.Dependency;

public class MavenDependencyTreeNode extends MavenDefaultTreeNode {

    private final Dependency dependency;

    public MavenDependencyTreeNode(@Nonnull Dependency dependency) {
        super(MessageType.DPMGMT);
        this.dependency = dependency;

        // hook
        this.acceptor(new GavTextVisitor());
    }

    public Dependency getDependency() {
        return dependency;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MavenDependencyTreeNode.class.getSimpleName() + "[", "]")
                   .add("dependency=" + dependency)
                   .add("messageType=" + messageType)
                   .toString();
    }

    @Override
    protected void acceptor(TreeNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getGav() {
        return dependency.getGroupId()
                   + ":" + dependency.getArtifactId()
                   + ":" + dependency.getVersion();
    }
}
