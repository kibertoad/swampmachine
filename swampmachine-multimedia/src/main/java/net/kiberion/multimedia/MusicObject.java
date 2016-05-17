package net.kiberion.multimedia;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kibertoad
 */
public class MusicObject {

	private static final Logger log = LogManager.getLogger();

	@Getter
	private final Music track;
	
	@Setter
	@Getter
    private boolean isLooping = true;

    MusicObject(String path) {
    	log.info("Music: Loading file " + path);
        boolean validFile = (new File(path)).exists();
        if (validFile) {
            track = Gdx.audio.newMusic(Gdx.files.internal(path));
            log.info("Loaded successfully");
        } else {
            track = null;
            //  lastMusic.music = new Music ("NULL");
            log.info("Music: Can't find " + path);
        }

        setLooping(isLooping);
    }

    public final void setLooping(boolean toValue) {
        track.setLooping(toValue);
    }
}
