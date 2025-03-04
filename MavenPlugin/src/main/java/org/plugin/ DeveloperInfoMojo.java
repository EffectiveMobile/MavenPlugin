package org.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import org.apache.maven.model.Developer;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "show-developers", defaultPhase = LifecyclePhase.PACKAGE)
public class DeveloperInfoMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter(property = "showEmails", defaultValue = "true")
    private boolean showEmails;

    @Parameter(property = "showUrls", defaultValue = "true")
    private boolean showUrls;

    public void execute() throws MojoExecutionException {
        List<Developer> developers = project.getDevelopers();

        if (developers.isEmpty()) {
            getLog().info("No developers found");
            return;
        }

        getLog().info("Developer(s) info:");
        developers.forEach(this::logDeveloperInfo);
    }

    private void logDeveloperInfo(Developer dev) {
        getLog().info("Name: " + dev.getName());
        if (showEmails && dev.getEmail() != null) {
            getLog().info("Email: " + dev.getEmail());
        }
        if (showUrls && dev.getUrl() != null) {
            getLog().info("URL: " + dev.getUrl());
        }
        getLog().info("------------------------");
    }
}
