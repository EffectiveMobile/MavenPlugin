package org.plugin;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.maven.model.Developer;
import org.apache.maven.model.InputLocation;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Mojo(name = "developer-print", defaultPhase = LifecyclePhase.COMPILE)
public class DeveloperMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        List<Developer> developers = project.getDevelopers();

        for (Developer developer : developers) {
            List<Field> allField = FieldUtils.getAllFieldsList(developer.getClass());

            for (Field field : allField) {
                field.setAccessible(true);
                try {
                    if (!field.getType().equals(InputLocation.class) && !field.getType().equals(Map.class)) {
                        getLog().info(field.getName() + ": " + field.get(developer));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            getLog().info("----------------------");
        }
    }
}