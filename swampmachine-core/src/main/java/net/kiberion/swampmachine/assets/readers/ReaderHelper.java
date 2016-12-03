package net.kiberion.swampmachine.assets.readers;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;

import lombok.Setter;
import net.kiberion.swampmachine.assets.GameConfig;

/**
 * Abstraction layer for reading files. Uses GDX files mechanism when GDX
 * context is available and reverts to direct work with FS if not.
 * 
 * @author kibertoad
 *
 */

public class ReaderHelper {

    private static final Logger log = LogManager.getLogger();

    private AbstractFileReader reader;

    @Autowired
    @Setter
    private GameConfig config;

    public AbstractFileReader getReader() {
        if (reader == null) {
            getReader(config.getPathToResources());
        }
        return reader;
    }

    public AbstractFileReader getReader(Path sourceDirectory) {
        if (reader == null) {
            if (Gdx.app != null) {
                reader = new GDXFileReader(sourceDirectory);
            } else {
                reader = new SimpleFileReader(sourceDirectory);
            }
        }
        return reader;
    }

    public boolean fileExists(String directoryName) {
        boolean result = getReader().fileExists((config.getPathToResources().resolve(directoryName)));

        if (!result) {
            log.warn("No " + directoryName + " directory exists.");
        }
        return result;
    }
}
