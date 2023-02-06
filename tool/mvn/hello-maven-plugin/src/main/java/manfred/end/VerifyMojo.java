package manfred.end;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;


/**
 * TODO
 */
@Mojo(name = "verify")
public class VerifyMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        throw new MojoFailureException("Not implemented!");
    }
}
