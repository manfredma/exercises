package manfred.end.tree;

import java.util.StringJoiner;
import javax.annotation.Nonnull;
import org.apache.maven.project.MavenProject;

public class MavenProjectTreeNode extends MavenDefaultTreeNode {

    private final MavenProject project;

    public MavenProjectTreeNode(@Nonnull  MessageType messageType, @Nonnull MavenProject mavenProject) {
        super(messageType);
        this.project = mavenProject;
        // hook
        this.acceptor(new GavTextVisitor());
    }

    public MavenProject getProject() {
        return project;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MavenProjectTreeNode.class.getSimpleName() + "[", "]")
                   .add("mavenProject=" + project)
                   .add("messageType=" + messageType)
                   .toString();
    }

    @Override
    public String getGav() {
        return project.getGroupId()
                   + ":" + project.getArtifactId()
                   + ":" + project.getVersion();
    }
    @Override
    protected void acceptor(TreeNodeVisitor visitor) {
        visitor.visit(this);
    }
}
