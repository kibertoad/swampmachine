package net.kiberion.assets.readers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public abstract class AbstractFileReader {

    
    private final Path baseDir;
    
    public AbstractFileReader(Path baseDir) {
        this.baseDir = baseDir;
    }

    protected Path getBaseDir() {
        return baseDir;
    }
    
    
    public abstract List<Path> getListOfFilesByWildcard (Path path, Set<String> wildcardFileExtensions) throws IOException;
    
    public abstract InputStream getFileAsStream (Path path) throws IOException;
    
    public abstract boolean fileExists (Path path);


    
    
}
