package net.kiberion.swampmachine.assets.loaders.util;

import java.nio.file.Path;

import com.badlogic.gdx.Gdx;

import lombok.Setter;
import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.assets.readers.GDXFileReader;
import net.kiberion.swampmachine.assets.readers.SimpleFileReader;

/**
 * Class provides a new file reader instance. Depending on whether GDX context
 * is available, either GDXFileReader (based on GDX Files layer) or
 * SimpleFileReader ( based on direct access to FS via java.io) instance is
 * provided.
 * 
 * @author kibertoad
 *
 */
public class FileReaderFactory {

    @Setter
    private static GameConfig config;

    private FileReaderFactory() {
    };

    public static AbstractFileReader buildFileReader() {
        return buildFileReader(config.getPathToResources());
    }

    public static AbstractFileReader buildFileReader(Path path) {
        if (Gdx.app != null) {
            return new GDXFileReader(path);
        } else {
            return new SimpleFileReader(path);
        }
    }

}
