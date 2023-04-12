package fr.univrouen.stb23v1.model;

import org.springframework.core.io.DefaultResourceLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestSTB {

    public String loadFileXML() throws IOException {
        File file = new DefaultResourceLoader().getResource("classpath:xml/test0.xml").getFile();
        String xml = Files.readString(file.toPath());

        return xml;
    }

}
