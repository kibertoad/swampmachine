package net.kiberion.swampmachine.utils.common;

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

//ToDo Needs tests
public class FilePathUtils {

    /**
     * 
     * @param directoryPath path to directory that will be searched for eligible files
     * @param extensions files with what extensions should be included
     * @return list of paths to files with extension that is included in provided set of extensions
     * @throws IOException
     */
    public static List<Path> getListOfFilesByExtension(Path directoryPath, Set<String> extensions) throws IOException {
        List<Path> result = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = (fileEntry) -> {
            return extensions.contains(com.google.common.io.Files.getFileExtension(fileEntry.toString()));
        };

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath, filter)) {
            for (Path file : stream) {
                result.add(file);
            }
        }

        return result;
    }

    /**
     * 
     * @param resourceClass
     *            - class, to which resource belongs, used to calculate package
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
            throw new IllegalStateException(e);
        }
    }

    public static String getPathForAssetManager(Path path) {
        return path.toString().replace("\\", "/");
    }    
}
