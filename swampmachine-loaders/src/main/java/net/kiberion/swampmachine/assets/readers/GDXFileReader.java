package net.kiberion.swampmachine.assets.readers;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class GDXFileReader extends AbstractFileReader {

    public GDXFileReader(Path baseDir) {
        super(baseDir);
    }

    public GDXFileReader() {
        this(Paths.get(""));
    }

    private void processDirectory(FileHandle dirHandle, List<Path> targetList, Set<String> wildcardFileExtensions) {
        for (FileHandle entry : dirHandle.list()) {
            if (entry.isDirectory()) {
                processDirectory(entry, targetList, wildcardFileExtensions);
            } else {
                if (wildcardFileExtensions.contains(entry.extension())) {
                    targetList.add(entry.file().toPath());
                }
            }
        }

    }

    @Override
    public List<Path> getListOfFilesByWildcard(Path path, Set<String> wildcardFileExtensions) {
        FileHandle dirHandle = Gdx.files.internal(path.toString());
        List<Path> list = new ArrayList<>();
        processDirectory(dirHandle, list, wildcardFileExtensions);
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
