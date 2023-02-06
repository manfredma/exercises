package manfred.end.utils;

import com.google.common.base.Preconditions;
import java.util.Properties;
import javax.annotation.Nonnull;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public interface Utils {

    static final String PROPERTY_PROJECT_VERSION = "project.version";
    static final String PROPERTY_PARENT_PREFIX = "project.parent.";

    static boolean isVersionSetAsProperty(@Nonnull String version) {
        Preconditions.checkNotNull(version);
        return version.contains("${");
    }

    static String findPropertyRecursively(MavenProject mavenProject,
                                          String propertyName,
                                          Log logger) {
        // project.version
        if (PROPERTY_PROJECT_VERSION.equals(propertyName)) {
            return mavenProject.getVersion();
        }

        Properties properties = mavenProject.getProperties();

        // resolve `project.version`
        if (PROPERTY_PROJECT_VERSION.equals(propertyName)) {
            final String artifactId = mavenProject.getArtifactId();
            final String version = mavenProject.getVersion();
            logger.warn(String.format("resolving project.version as project(%s) version: %s",
                                      artifactId,
                                      version));
            return version;
        }
        // resolve from properties
        if (properties != null) {
            String propertyValue = (String) properties.get(propertyName);
            if (propertyValue != null) {
                return propertyValue;
            }
        }

        // resolve from parent
        MavenProject parent = mavenProject.getParent();
        if (parent != null) {
            String fixedPropertyName = propertyName;
            if (propertyName.startsWith(PROPERTY_PARENT_PREFIX)) {
                fixedPropertyName = fixedPropertyName.replaceAll("project\\.parent\\.(?<prop>.*)",
                                                                 "project.${prop}");
            }
            return findPropertyRecursively(parent, fixedPropertyName, logger);
        }

        return propertyName;
    }

    static String resolveVersionProperty(MavenProject currentProject, String version, Log logger) {
        if (isVersionSetAsProperty(version)) {
            String propertyName = version.replace("$", "")
                                         .replace("{", "")
                                         .replace("}", "");
            return findPropertyRecursively(currentProject, propertyName, logger);
        }

        return version;
    }
}
