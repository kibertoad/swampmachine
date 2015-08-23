package net.kiberion.assets.readers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.input.AutoCloseInputStream;

public class SimpleFileReader extends AbstractFileReader {

    public SimpleFileReader(Path baseDir) {
        super(baseDir);
    }

    @Override
    public List<Path> getListOfFilesByWildcard(Path path, Set<String> wildcard) throws IOException {
        List<Path> result = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = (entry) -> {
            return wildcard.contains(com.google.common.io.Files.getFileExtension(entry.toString()));
        };

        DirectoryStream<Path> stream = Files.newDirectoryStream(path, filter);

        for (Path file : stream) {
            result.add(file);
        }
        return result;
    }

    @Override
    public InputStream getFileAsStream(Path file) throws IOException {
        
        InputStream in = new FileInputStream(file.toFile());
        BufferedInputStream bis = new BufferedInputStream(in);
        AutoCloseInputStream acis = new AutoCloseInputStream (bis);
        
        return acis;
    }
    
    @Override
    public boolean fileExists(Path path) {
        return Files.exists(path);
    }
}
