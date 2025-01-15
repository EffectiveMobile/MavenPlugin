package org.plugin.util;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.plugin.exception.PomXmlNotParsedException;

import java.io.FileReader;
import java.io.IOException;

public class MavenProjectDeveloperParser implements MavenProjectParser {

    @Override
    public Model parseModel() {
        try(FileReader fileReader = new FileReader("./pom.xml")) {
            return new MavenXpp3Reader().read(fileReader);
        } catch (IOException | XmlPullParserException ex) {
            throw new PomXmlNotParsedException(ex);
        }
    }
}