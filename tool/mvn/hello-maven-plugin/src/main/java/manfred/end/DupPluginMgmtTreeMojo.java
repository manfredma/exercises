package manfred.end;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import manfred.end.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Build;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuilder;

/**
 * 打印父子项目间重复的 PluginManagement/Plugins 定义
 */
@Mojo(name = "pmdup")
public class DupPluginMgmtTreeMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession session;

    @Component
    private ProjectBuilder projectBuilder;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        try {

            Preconditions.checkNotNull(project);

            for (MavenProject current = project; current != null; current = current.getParent()) {

                final MavenProject parent = current.getParent();
                if (null == parent) {
                    continue;
                }

                final String pid = current.getId();
                final String ppid = parent.getId();
                getLog().info(String.format("<< parent: %s <- current:%s >>", ppid, pid));
                final List<Plugin> original = getOriginalPluginsInManagement(current);
                if (original == null) {
                    continue;
                }

                final List<Plugin> effective = getEffectivePluginsInManagement(parent);
                Preconditions.checkNotNull(effective);
                Preconditions.checkArgument(!effective.isEmpty());

                /*
                MEMO: 子项目升级或者降低版本，都应该是允许的，只判断是否是重复定义。
                 */

                // key = group id + artifact id
                // Plugin 的 equals 使用的是 Key
                // filterByKey(original, parent);

                // id = group id + artifact id + version
                filterById(original, effective, project);
            }
        } catch (Exception ignore) {
            getLog().error(ignore);
        }

    }

    private void filterById(List<Plugin> original, List<Plugin> parent, MavenProject project) {
        final Set<String> left = parent.stream()
                                       .map(Plugin::getId)
                                       .collect(Collectors.toSet());

        // final Set<String> right = original.stream()
        //                                   .map(Plugin::getId)
        //                                   .collect(Collectors.toSet());
        final Set<String> right = original.stream()
                                          .map(plugin -> tryResolvePropertyVersion(project, plugin))
                                          .collect(Collectors.toSet());

        if (getLog().isDebugEnabled()) {
            getLog().debug("left:\n" + Joiner.on("\n- ")
                                             .join(left));
            getLog().debug("right:\n" + Joiner.on("\n- ")
                                              .join(right));
        }

        final SetView<String> intersection = Sets.intersection(left, right);

        if (!intersection.isEmpty()) {
            getLog().warn("Contains Duplicated PluginManagements as bellow:");
            for (String plugin : intersection) {
                getLog().warn("! " + plugin);
            }
        }
    }

    private String tryResolvePropertyVersion(MavenProject project, Plugin plugin) {
        final String key = plugin.getKey();
        final String version = plugin.getVersion();
        // Version可能为空，就是继承了
        if (StringUtils.isNotEmpty(version) && Utils.isVersionSetAsProperty(version)) {
            final String rVersion =
                    Utils.resolveVersionProperty(
                            project,
                            version,
                            getLog());
            return key + ":" + rVersion;
        } else {
            return plugin.getId();
        }
    }

    private void filterByKey(List<Plugin> original, List<Plugin> parent) {

        // 如果当前层级的项目中没有申明版本，其实就是继承了parent的版本
        final List<Plugin> filtered
                = original.stream()
                          .filter(plugin -> plugin.getVersion() != null)
                          .collect(Collectors.toList());

        final SetView<Plugin> intersection
                = Sets.intersection(Sets.newHashSet(parent),
                                    Sets.newHashSet(filtered));
        if (!intersection.isEmpty()) {
            getLog().warn("Contains Duplicated PluginManagements as bellow:");
            for (Plugin plugin : intersection) {
                getLog().warn("! " + plugin);
            }
        }

    }

    /**
     * @param current 当前项目
     * @return 当前项目的有效的PluginManagement/Plugins
     */
    private List<Plugin> getEffectivePluginsInManagement(@Nonnull MavenProject current) {
        final PluginManagement parentPluginManagement = current.getPluginManagement();
        // 获取到的是生效后的PluginManagement，应该不会空，还有Super POM
        Preconditions.checkNotNull(parentPluginManagement);

        final List<Plugin> parentEffectivePlugins = parentPluginManagement.getPlugins();
        if (getLog().isDebugEnabled()) {
            getLog().info("Effective Plugins in Parent:");
            getLog().debug(Joiner.on("\n\t")
                                 .join(parentEffectivePlugins.stream()
                                                             .map(Plugin::getId)
                                                             .collect(Collectors.toList())));
        }
        return parentEffectivePlugins;
    }

    /**
     * @param current 当前项目
     * @return 获取当前项目定义的PluginManagement，而非继承自父项目的有效PluginManagement
     */
    private List<Plugin> getOriginalPluginsInManagement(@Nonnull MavenProject current) {

        final Build originalBuild = current.getOriginalModel()
                                           .getBuild();
        // 当前项目没有定义 <Build> 或者 <PluginManagement>
        if (originalBuild == null) {
            return null;
        }

        final PluginManagement originalPluginMgmt = originalBuild.getPluginManagement();
        if (originalPluginMgmt == null) {
            return null;
        }
        final List<Plugin> originalPlugins = originalPluginMgmt.getPlugins();
        if (originalPlugins == null || originalPlugins.isEmpty()) {
            return null;
        }

        if (getLog().isDebugEnabled()) {
            getLog().info("Plugins in Original Model:");
            getLog().info(Joiner.on("\n\t")
                                .join(originalPlugins.stream()
                                                     .map(Plugin::getId)
                                                     .collect(Collectors.toList())));
        }
        return originalPlugins;
    }
}
