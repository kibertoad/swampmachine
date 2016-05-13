package net.kiberion.swampmachine.assets.readers;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class GDXFileReader extends AbstractFileReader{

    public GDXFileReader(Path baseDir) {
        super(baseDir);
    }

    public GDXFileReader() {
        this (Paths.get(""));
    }

    @Override
    public List<Path> getListOfFilesByWildcard(Path path, Set<String> wildcardFileExtensions) {
        FileHandle dirHandle = Gdx.files.internal(path.toString());
        List<Path> list = new ArrayList<>();

        for (FileHandle entry : dirHandle.list()) {
            if (wildcardFileExtensions.contains(entry.extension())) {
                list.add(entry.file().toPath());
            }
        }
        return list;
    }
    
    @Override
    public InputStream getFileAsStream(Path path) {
        return Gdx.files.internal(path.toString()).read();
    }
    
    public InputStream getRelativeFileAsStream(Path path) {
        return Gdx.files.internal(getBaseDir().resolve(path).toString()).read();
    }
    
    @Override
    public boolean fileExists(Path path) {
        return (Gdx.files.internal(path.toString()).exists());
    }

}
