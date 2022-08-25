package manfred.end;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "goal1")
public class Goal1Mojo extends AbstractMojo {

    @Parameter(name = "name", defaultValue = "test")
    private String name;

    public void execute() {
        getLog().info("This is goal1.");
    }
}
