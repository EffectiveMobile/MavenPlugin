package org.plugin.util;

import org.apache.maven.model.Model;

public interface MavenProjectInfoPrinter {
    void print(Model model);
}
