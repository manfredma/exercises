package manfred.end;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import manfred.end.tree.MavenDefaultTreeNode;
import manfred.end.tree.MavenProjectTreeNode;
import manfred.end.tree.MessageType;
import manfred.end.utils.Utils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuilder;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.repository.RepositorySystem;
import org.barfuin.texttree.api.TextTree;
import org.barfuin.texttree.api.TreeOptions;
import org.barfuin.texttree.api.style.TreeStyles;

@Mojo(name = "dependency-tree")
public class DependencyTreeMojo extends AbstractMojo {

    private static final String LEVEL_FULL = "full";

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession session;

    @Parameter(property = "level")
    private String level;

    @Component
    private RepositorySystem repositorySystem;

    @Component
    private ProjectBuilder projectBuilder;

    @Override
    public void execute() {

        // root node
        MavenProjectTreeNode root = new MavenProjectTreeNode(MessageType.__ROOT, project);
        bfs(root);

        TreeOptions options = new TreeOptions();
        options.setStyle(TreeStyles.UNICODE);
        String rendered = TextTree.newInstance(options)
                .render(root);

        getLog().info("\n" + rendered);

    }

    private void bfs(MavenProjectTreeNode root) {
        Queue<MavenDefaultTreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<String> resolved = new ArrayList<>();
        MavenDefaultTreeNode pointer;
        while (!queue.isEmpty()) {
            pointer = queue.remove();
            getLog().debug("current tree node: " + pointer);

            MavenProjectTreeNode current = (MavenProjectTreeNode) pointer;

            MavenProject mavenProject = current.getProject();
            List<Dependency> dependencies = mavenProject.getDependencies();

            for (Dependency dependency : dependencies) {
                if (!dependency.getGroupId().startsWith("com.meituan") && !dependency.getGroupId().startsWith("com.sankuai")) {
                    continue;
                }

                if (resolved.contains(dependency.toString())) {
                    continue;
                }
                resolved.add(dependency.toString());

                getLog().info("正在解析 dependency：" + dependency);
                try {
                    MavenProject dependencyProject = getMavenProject(mavenProject,
                            dependency.getGroupId(),
                            dependency.getArtifactId(),
                            dependency.getVersion());
                    MavenProjectTreeNode dependencyTreeNode = new MavenProjectTreeNode(
                            MessageType.DEPENDENCY,
                            dependencyProject);
                    queue.add(dependencyTreeNode);
                    pointer.addChild(dependencyTreeNode);
                } catch (ProjectBuildingException e) {
                    getLog().error("解析时出现异常 dependency：" + dependency);
                    // throw new RuntimeException(e);
                }
            }
        }
    }

    // ---------------------------------------------------------------------------------------------

    private MavenProject getMavenProject(
            MavenProject currentProject,
            String groupId,
            String artifactId,
            String version) throws ProjectBuildingException {

        String resolvedVersion = Utils.resolveVersionProperty(currentProject, version, getLog());

        Artifact pomArtifact = repositorySystem.createProjectArtifact(groupId,
                artifactId,
                resolvedVersion);
        return projectBuilder.build(pomArtifact, session.getProjectBuildingRequest())
                .getProject();

    }

}