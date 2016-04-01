package net.kiberion.assets.readers;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.google.inject.Singleton;

import lombok.Getter;

@Singleton
@Component
public class ReaderHelper {

    private static final Logger log = LogManager.getLogger();
    
	private AbstractFileReader reader;

	@Getter
	private Path pathToAssets = Paths.get("src/main/resources/");

	public AbstractFileReader getReader() {
		if (reader == null) {
			if (Gdx.app != null) {
				reader = new GDXFileReader(pathToAssets);
			} else {
				reader = new SimpleFileReader(pathToAssets);
			}
		}
		return reader;
	}
	
	public void setPathToAssets (String path) {
		pathToAssets = Paths.get(path);
		reader = null;
	}

    public boolean fileExists(String directoryName) {
        boolean result = getReader().fileExists((getPathToAssets().resolve(directoryName)));

        if (!result) {
            {
                log.warn("No " + directoryName + " directory exists.");
            }
        }
        return result;
    }}
