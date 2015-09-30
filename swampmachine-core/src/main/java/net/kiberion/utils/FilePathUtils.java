package net.kiberion.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilePathUtils {

    public static List<Path> getListOfFilesByWildcard(Path path, Set<String> wildcard) throws IOException {
        List<Path> result = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = (entry) -> {
            return wildcard.contains(com.google.common.io.Files.getFileExtension(entry.toString()));
        };

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, filter)) {
            for (Path file : stream) {
                result.add(file);
            }
        }

        return result;
    }
    
    /**
     * 
     * @param resourceClass - class, to which resource belongs, used to calculate package
     * @param resourceName
     * @return
     * @throws URISyntaxException
     */
    public static Path getResourcePath(Class<?> resourceClass, String resourceName) throws URISyntaxException {
        URL url = resourceClass.getResource(resourceName);
        return Paths.get(url.toURI());
    }      

    public static Path getResourceRootPath(Class<?> resourceClass, String resourceName) {
        URL url = resourceClass.getResource(resourceName);
        try {
            return Paths.get(url.toURI()).getParent();
        } catch (URISyntaxException e) {
            throw new IllegalStateException (e);
        }
    }      
    
}
