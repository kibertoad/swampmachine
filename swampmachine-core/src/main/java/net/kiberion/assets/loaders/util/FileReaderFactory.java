package net.kiberion.assets.loaders.util;

import java.nio.file.Path;

import com.badlogic.gdx.Gdx;

import lombok.Setter;
import net.kiberion.assets.GameConfig;
import net.kiberion.assets.readers.AbstractFileReader;
import net.kiberion.assets.readers.GDXFileReader;
import net.kiberion.assets.readers.SimpleFileReader;

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
