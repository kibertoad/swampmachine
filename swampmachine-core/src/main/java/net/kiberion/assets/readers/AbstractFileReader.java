package net.kiberion.assets.readers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public abstract class AbstractFileReader {

    protected final Path baseDir;
    
    public AbstractFileReader(Path baseDir) {
        this.baseDir = baseDir;
    }

    protected Path getBaseDir() {
        return baseDir;
    }
    
    public abstract List<Path> getListOfFilesByWildcard (Path path, Set<String> wildcardFileExtensions) throws IOException;

    public List<Path> getListOfRelativeFilesByWildcard(Path path, Set<String> wildcardFileExtensions)
            throws IOException {
        return getListOfFilesByWildcard (baseDir.resolve(path), wildcardFileExtensions);
    }

    public List<Path> getListOfRelativeFilesByWildcard(String path, Set<String> wildcardFileExtensions)
            throws IOException {
        return getListOfFilesByWildcard (baseDir.resolve(Paths.get(path)), wildcardFileExtensions);
    }
    
    
    public abstract InputStream getFileAsStream (Path path) throws IOException;
    
    public abstract boolean fileExists (Path path);
    
    public boolean relativeFileExists (Path path) {
        return fileExists(baseDir.resolve(path)); 
    }
}
