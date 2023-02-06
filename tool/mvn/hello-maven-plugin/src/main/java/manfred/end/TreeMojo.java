package manfred.end;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import manfred.end.tree.MavenDefaultTreeNode;
import manfred.end.tree.MavenDependencyTreeNode;
import manfred.end.tree.MavenProjectTreeNode;
import manfred.end.tree.MessageType;
import manfred.end.utils.Utils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
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

@Mojo(name = "tree")
public class TreeMojo extends AbstractMojo {

    private static final int DEPTH_INCREMENT = 1;

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
    public void execute() throws MojoExecutionException, MojoFailureException {

        String message = "Displaying hierarchy. ";
        if (!isLevelFull()) {
            message +=
                    "Set level=" + LEVEL_FULL + " to display dependencies in dependencyManagement";
        }
        getLog().info(message);

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

        MavenDefaultTreeNode pointer;
        while (!queue.isEmpty()) {
            pointer = queue.remove();
            getLog().debug("current tree node: " + pointer);

            if (pointer instanceof MavenProjectTreeNode) {
                MavenProjectTreeNode current = (MavenProjectTreeNode) pointer;

                final MavenProject mavenProject = current.getProject();
                final MavenProject parentMavenProject = mavenProject.getParent();
                if (parentMavenProject != null) {
                    final MavenProjectTreeNode pChild = new MavenProjectTreeNode(MessageType.PARENT,
                                                                                 parentMavenProject);
                    queue.add(pChild);
                    pointer.addChild(pChild);

                    addDependencyManagementNode(parentMavenProject, pChild);

                }
                final DependencyManagement dependencyManagement = mavenProject.getOriginalModel()
                                                                              .getDependencyManagement();
                if (dependencyManagement != null) {
                    final List<Dependency> dependencies = dependencyManagement.getDependencies();
                    for (Dependency dependency : dependencies) {
//                        if ("import".equals(dependency.getScope())
//                                    && "pom".equals(dependency.getType())) {
                            try {
                                MavenProject pomProject = getMavenProject(mavenProject,
                                                                          dependency.getGroupId(),
                                                                          dependency.getArtifactId(),
                                                                          dependency.getVersion());
                                final MavenProjectTreeNode pChild = new MavenProjectTreeNode(
                                        MessageType.IMPORT,
                                        pomProject);
                                queue.add(pChild);
                                pointer.addChild(pChild);

                                addDependencyManagementNode(pomProject, pChild);

                            } catch (ProjectBuildingException e) {
                                throw new RuntimeException(e);
                            }
//                        }
                    }
                }
            }
        }
    }

    private void addDependencyManagementNode(MavenProject pomProject, MavenProjectTreeNode pChild) {
        if (isLevelFull()) {
            DependencyManagement mgmt = pomProject.getDependencyManagement();
            if (mgmt != null) {
                for (Dependency d : mgmt.getDependencies()) {
                    MavenDependencyTreeNode dependencyTreeNode =
                            new MavenDependencyTreeNode(d);
                    pChild.addChild(dependencyTreeNode);
                }
            }
        }
    }


    private boolean isLevelFull() {
        return LEVEL_FULL.equals(level);
    }

    // ---------------------------------------------------------------------------------------------

    private MavenProject getMavenProject(MavenProject currentProject,
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