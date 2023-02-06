package manfred.end;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.superpom.DefaultSuperPomProvider;
import org.apache.maven.model.superpom.SuperPomProvider;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuilder;
import org.apache.maven.repository.RepositorySystem;
import org.apache.maven.rtinfo.RuntimeInformation;
import org.apache.maven.rtinfo.internal.DefaultRuntimeInformation;


/*
输出格式的说明:

[INFO] >>> Diffing mdp-basic-parent and hyperion-top
[INFO]
    --- mdp-basic-parent
    +++ hyperion-top
    @@ -9,1 +9,1 @@
    -org.apache.maven.plugins:maven-antrun-plugin:1.3      # 减掉了mdp-basic-parent的antrun
    +org.apache.maven.plugins:maven-antrun-plugin:3.0.0    # 自己新增了3.0.0的版本
 */

/**
 * 按 Maven 项目的继承关系逐层递归检查差异的 PluginManagement/Plugins
 */
@Mojo(name = "pmdiff")
public class PluginMgmtTreeMojo extends AbstractMojo {

    @Parameter(property = "recursive", defaultValue = "false")
    private boolean recursive = false;

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession session;

    @Component
    private RepositorySystem repositorySystem;

    @Component
    private ProjectBuilder projectBuilder;

    @Component(role = SuperPomProvider.class)
    private DefaultSuperPomProvider superPomProvider;

    @Component(role = RuntimeInformation.class)
    private DefaultRuntimeInformation defaultRuntimeInformation;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        Preconditions.checkNotNull(superPomProvider);
        Preconditions.checkNotNull(defaultRuntimeInformation);

        // getLog().info("Display <PluginManagement> Hierarch >>>");

        printBottomUp(project);

        getLog().info("<<< Diff ProjectManagement Project by Project >>>");
        for (MavenProject mp = project; mp != null; mp = mp.getParent()) {
            final MavenProject parent = mp.getParent();
            if (parent == null) {
                // 到达项目的根POM
                printProjectRootPomChange(mp);
                break;
            }

            final PluginManagement newPluginManagement = mp.getPluginManagement();
            final PluginManagement oldPluginManagement = parent.getPluginManagement();
            diffPluginManagements(oldPluginManagement, newPluginManagement, parent.getName(),
                                  mp.getName());

            if (!recursive) {
                break;
            }
        }

    }

    private void diffPluginManagements(PluginManagement oldPluginManagement,
                                       PluginManagement newPluginManagement,
                                       String leftName,
                                       String rightName) {

        final List<String> left = oldPluginManagement.getPlugins()
                                                     .stream()
                                                     .map(Plugin::getId)
                                                     .sorted()
                                                     .collect(Collectors.toList());

        final List<String> right = newPluginManagement.getPlugins()
                                                      .stream()
                                                      .map(Plugin::getId)
                                                      .sorted()
                                                      .collect(Collectors.toList());

        getLog().info(String.format(">>> Diffing %s and %s", leftName, rightName));
        Patch<String> diff = DiffUtils.diff(left, right);

        if (diff.getDeltas()
                .isEmpty()) {
            getLog().info("N/A");
        } else {
            List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff(leftName,
                                                                            rightName,
                                                                            left,
                                                                            diff,
                                                                            0);

            final String diffText = Joiner.on("\n\t")
                                          .join(unifiedDiff);
            getLog().info("\n\t" + diffText);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void printProjectRootPomChange(MavenProject mp) {
        final PluginManagement pluginManagement = mp.getPluginManagement();
        final PluginManagement superPomPluginManagement = superPomProvider.getSuperModel(
                                                                                  defaultRuntimeInformation.getMavenVersion())
                                                                          .getBuild()
                                                                          .getPluginManagement();

        diffPluginManagements(superPomPluginManagement,
                              pluginManagement,
                              "Super POM",
                              mp.getName());

    }

    private void printBottomUp(MavenProject mavenProject) {
        getLog().debug("<<< Top-Down Projects Hierarch >>>");
        List<MavenProject> bottomUpProjects = new ArrayList<>();
        for (MavenProject mp = mavenProject; mp != null; mp = mp.getParent()) {
            bottomUpProjects.add(mp);
        }

        List<MavenProject> topDownProject = Lists.reverse(bottomUpProjects);

        topDownProject.forEach(p -> getLog().debug(p.getId()));
    }
}
