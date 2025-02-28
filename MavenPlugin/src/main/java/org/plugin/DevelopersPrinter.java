package org.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "dev-print")
public class DevelopersPrinter extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        List<?> developers = project.getDevelopers();
        if (developers == null || developers.isEmpty()) {
            getLog().info("No developers found in the POM file.");
            return;
        }

        getLog().info("Developers: ");
        for (Object developer : developers) {
            getLog().info(developer.toString());
        }
    }
}
