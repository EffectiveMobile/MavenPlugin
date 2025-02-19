package org.plugin;

import org.apache.maven.model.Developer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

import static org.plugin.Constants.SPACE;
import static org.plugin.Constants.SPLITTER;

@Mojo(name = "custom-develop")
public class Plugin extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException {
        List<Developer> list = project.getDevelopers();
        if (list == null || list.isEmpty()) {
            throw new MojoExecutionException("There is no developers");
        }
        getLog().info("Develop plugin stage is started");
        StringBuilder stringBuilder = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        int listSize = list.size();
        stringBuilder.append(lineSeparator)
                .append(SPLITTER).append(lineSeparator);
        for (int i = 0; i < listSize; i++) {
            Developer developer = list.get(i);
            developId(developer, stringBuilder, lineSeparator);
            developName(developer, stringBuilder, lineSeparator);
            developEmail(developer, stringBuilder, lineSeparator);
            developLocation(developer, stringBuilder, lineSeparator);
            developOrganization(developer, stringBuilder, lineSeparator);
            developRoles(developer, stringBuilder, lineSeparator);
            developTimeZone(developer, stringBuilder, lineSeparator);
            developUrl(developer, stringBuilder, lineSeparator);
            if (i != listSize - 1) {
                stringBuilder.append(SPLITTER).append(lineSeparator);
            } else {
                stringBuilder.append(SPLITTER);
            }
        }
        int lastIndexSeparator = stringBuilder.lastIndexOf(lineSeparator);
        stringBuilder.deleteCharAt(lastIndexSeparator);
        getLog().info(stringBuilder.toString());
    }

    private void developId(Developer developer, StringBuilder stringBuilder, String lineSeparator) {
        if (developer.getId() != null) {
            String id = developer.getId();
            stringBuilder.append(id)
                    .append(lineSeparator);
        }
    }

    private void developName(Developer developer, StringBuilder stringBuilder, String lineSeparator) {
        if (developer.getName() != null) {
            String name = developer.getName();
            stringBuilder.append(name)
                    .append(SPACE)
                    .append(lineSeparator);
        }
    }

    private void developEmail(Developer developer, StringBuilder stringBuilder, String lineSeparator) {
        if (developer.getEmail() != null) {
            String email = developer.getEmail();
            stringBuilder.append(email)
                    .append(lineSeparator);
        }
    }

    private void developLocation(Developer developer, StringBuilder stringBuilder, String lineSeparator) {
        if (developer.getLocation(developer) != null) {
            String location = developer.getLocation(developer).toString();
            stringBuilder.append(location)
                    .append(lineSeparator);
        }
    }

    private void developOrganization(Developer developer, StringBuilder stringBuilder, String lineSeparator) {
        if (developer.getOrganization() != null) {
            String organization = developer.getOrganization();
            stringBuilder.append(organization)
                    .append(lineSeparator);
        }
    }

    private void developRoles(Developer developer, StringBuilder stringBuilder, String lineSeparator) {
        if (developer.getRoles() != null) {
            developer.getRoles().forEach(e -> stringBuilder.append(e).append(lineSeparator));
        }
    }

    private void developTimeZone(Developer developer, StringBuilder stringBuilder, String lineSeparator) {
        if (developer.getTimezone() != null) {
            String timezone = developer.getTimezone();
            stringBuilder.append(timezone)
                    .append(lineSeparator);
        }
    }

    private void developUrl(Developer developer, StringBuilder stringBuilder, String lineSeparator) {
        if (developer.getUrl() != null) {
            String url = developer.getUrl();
            stringBuilder.append(url)
                    .append(lineSeparator);
        }
    }
}
