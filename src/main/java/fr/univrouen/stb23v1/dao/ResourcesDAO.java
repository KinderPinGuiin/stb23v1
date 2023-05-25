package fr.univrouen.stb23v1.dao;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The resources DAO implements operations that are allowing us to handle the resources files.
 */
@Service
public class ResourcesDAO {

    /**
     * Load the resource at the given path and returns its content as a string.
     *
     * @param  path        The resource path from the "resources" folder.
     * @return             The resource content as a string.
     * @throws IOException Exception thrown when the given path is invalid.
     */
    public String loadResourceAsString(String path) throws IOException {
        return Files.readString(this.loadResourceAsFile(path).toPath());
    }

    /**
     * Load the resource at the given path and return its file instance.
     *
     * @param  path        The resource path from the "resources" folder.
     * @return             The resource content as a file instance.
     * @throws IOException Exception thrown when the given path is invalid.
     */
    public File loadResourceAsFile(String path) throws IOException {
        return new DefaultResourceLoader().getResource("classpath:" + path).getFile();
    }

}
