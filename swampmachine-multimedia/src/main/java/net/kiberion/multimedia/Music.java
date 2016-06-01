/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.multimedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.audio.Music.OnCompletionListener;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.GameConfig;

/**
 * Class for playing Music files.
 *
 * @author Cameron Seebach
 */
public class Music {

    @Autowired
    @Setter
    private GameConfig gameConfig;
    
    private static final Logger log = LogManager.getLogger();

    private static Music _instance;

    @Getter
    private boolean isEnabled = true;

    @Getter
    @Setter
    private float volume = 0.75f;

    @Getter
    private Map<String, MusicObject> musicTracks = new HashMap<>();

    @Getter
    private MusicObject currentTrack;
    private MusicObject nextTrack;
    private MusicObject lastPlayedTrack;

    public List<MusicObject> allPlayingTracks = new ArrayList<>();

    /**
     * Load all the music files.
     */
    public void load() {
        String dirPath = getPathToMusic();

        File dir = new File(dirPath);
        String[] fileList = dir.list();

        if (fileList == null) {
            log.warn("Music directory does not exist!");
        } else {
            for (String fileEntry : fileList) {
                if (fileEntry.endsWith(".ogg")) {
                    musicTracks.put(fileEntry.replace(".ogg", ""), new MusicObject(dirPath + fileEntry));
                }
            }
        }
    }

    private void play(String trackName, float setVolume, OnCompletionListener onCompletion) {
        if (isEnabled) {
            log.debug("play track");
            nextTrack = musicTracks.get(trackName);

            play(nextTrack, setVolume, onCompletion);
        }
    }

    /**
     * Play the track with the name track name.
     *
     * @param trackName
     */
    public void play(String trackName, OnCompletionListener onCompletion) {
        if (isEnabled) {
            log.debug("play track: " + trackName);
            nextTrack = musicTracks.get(trackName);
            play(nextTrack, volume, onCompletion);
        }
    }

    /**
     * Play the specified track.
     *
     * @param track
     */
    public void play(MusicObject track, float localVolume, OnCompletionListener onCompletion) {
        if (currentTrack != track) {
            if (currentTrack != null) {
                currentTrack.getTrack().stop();
            }

            currentTrack = track;
            currentTrack.getTrack().setVolume(localVolume);
            lastPlayedTrack = currentTrack;
            currentTrack.getTrack().play();

            if (onCompletion != null) {
                currentTrack.getTrack().setOnCompletionListener(onCompletion);
            }
        }

    }

    /**
     * Stop current track from being played
     */
    public void stop() {
        if (currentTrack != null) {
            currentTrack.getTrack().stop();
        }

        while (!(allPlayingTracks.isEmpty())) {
            MusicObject track = allPlayingTracks.get(0);
            track.getTrack().stop();
            allPlayingTracks.remove(track);
        }
    }

    /**
     * Play the track that was played before the current one Useful for
     * returning to ambient music after some dynamically played one
     */
    public void playLastTrack() {
        if (isEnabled) {
            play(lastPlayedTrack, volume, null);
        }
    }

    public void outputValues() {
        for (MusicObject m : musicTracks.values()) {
            log.debug(m.toString());
        }
    }

    public void playOnce(String trackName, OnCompletionListener onCompletion) {
        playOnce(trackName, volume, onCompletion);
    }

    public void playOnce(String trackName, float setVolume, OnCompletionListener onCompletion) {
        nextTrack = musicTracks.get(trackName);

        nextTrack.setLooping(false);
        play(trackName, setVolume, onCompletion);
    }

    public void playAsync(String trackName, boolean setLooping) {
        musicTracks.get(trackName).setLooping(setLooping);
        musicTracks.get(trackName).getTrack().play();
        allPlayingTracks.add(musicTracks.get(trackName));
    }

    public static Music instance() {
        if (_instance == null) {
            _instance = new Music();
        }
        return _instance;
    }

    public String getPathToMusic() {
        return gameConfig.getPathToResources().resolve("music").toString();
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        if (!isEnabled) {
            stop();
        } else {
            if (currentTrack != null) {
                currentTrack.getTrack().play();
            }
        }
    }

}
