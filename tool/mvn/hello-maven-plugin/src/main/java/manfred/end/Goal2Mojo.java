package manfred.end;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "goal2")
public class Goal2Mojo extends AbstractMojo {

    @Parameter(name = "name", defaultValue = "test")
    private String name;

    public void execute() {
        getLog().info("This is goal2.");
    }
}