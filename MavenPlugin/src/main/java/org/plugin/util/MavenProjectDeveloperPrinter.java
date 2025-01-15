package org.plugin.util;

import org.apache.maven.model.Developer;
import org.apache.maven.model.Model;

public class MavenProjectDeveloperPrinter implements MavenProjectInfoPrinter {
    private static final String DEVELOPERS_INFO_FORMAT = "id: %s, name: %s, email: %s, organization: %s, roles: %s";
    @Override
    public void print(Model model) {
        System.out.println("Developers:");
        for (Developer developer: model.getDevelopers()) {
            String id = developer.getId() != null ? developer.getId() : "N/A";
            String name = developer.getName() != null ? developer.getName() : "N/A";
            String email = developer.getEmail() != null ? developer.getEmail() : "N/A";
            String organization = developer.getOrganization() != null ? developer.getOrganization() : "N/A";
            String roles = (developer.getRoles() != null && !developer.getRoles().isEmpty())
                    ? developer.getRoles().toString()
                    : "N/A";

            System.out.println(String.format(DEVELOPERS_INFO_FORMAT, id, name, email, organization, roles));
        }
    }
}
