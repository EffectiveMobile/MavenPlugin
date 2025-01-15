package org.plugin;


import org.apache.maven.model.Model;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.plugin.util.MavenProjectDeveloperParser;
import org.plugin.util.MavenProjectDeveloperPrinter;
import org.plugin.util.MavenProjectInfoPrinter;
import org.plugin.util.MavenProjectParser;

@Mojo(name = "print", defaultPhase = LifecyclePhase.VERIFY)
public class MavenProjectDevelopersMojo extends AbstractMojo {
    private final MavenProjectParser projectParser;
    private final MavenProjectInfoPrinter projectInfoPrinter;

    public MavenProjectDevelopersMojo() {
        projectParser = new MavenProjectDeveloperParser();
        projectInfoPrinter = new MavenProjectDeveloperPrinter();
    }

    @Override
    public void execute() {
        Model model = projectParser.parseModel();
        if (model.getDevelopers() != null || !model.getDevelopers().isEmpty()) {
            projectInfoPrinter.print(model);
        }
    }
}
