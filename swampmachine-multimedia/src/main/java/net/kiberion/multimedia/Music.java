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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music.OnCompletionListener;

import net.kiberion.assets.AssetProvider;

/**
 * Class for playing Music files.
 *
 * @author Cameron Seebach
 */
public class Music {
	
	private static final Logger log = LogManager.getLogger();

    private static Music _instance;

    private boolean isEnabled = true;

    public float volume = 0.75f;
    public Map<String, MusicObject> musicTracks = new HashMap<String, MusicObject>();
    public MusicObject currentTrack;
    private MusicObject nextTrack;
    private MusicObject lastPlayedTrack;

    public List<MusicObject> allPlayingTracks = new ArrayList<MusicObject>();

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

            for (int x = 0; x < fileList.length; x++) {

                if (fileList[x].endsWith(".ogg")) {
                    musicTracks.put(fileList[x].replace(".ogg", ""), new MusicObject(dirPath + fileList[x]));

                }
            }
        }
    }

    private void play(String trackName, float setVolume, OnCompletionListener onCompletion) {
        if (isEnabled) {
            Gdx.app.log("debug", "play track");
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
            Gdx.app.log("debug", "play track: " + trackName);
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
                currentTrack.track.stop();
            }

            currentTrack = track;
            currentTrack.track.setVolume(localVolume);
            lastPlayedTrack = currentTrack;
            currentTrack.track.play();
            
            if (onCompletion != null) {
            currentTrack.track.setOnCompletionListener(onCompletion);
            }
        }

    }

    /**
     * Stop current track from being played
     */
    public void stop() {
        if (currentTrack != null) {
            currentTrack.track.stop();
        }

        while (!(allPlayingTracks.isEmpty())) {
            MusicObject track = allPlayingTracks.get(0);
            track.track.stop();
            allPlayingTracks.remove(track);
        }
    }

    /**
     * Set the volume of music.
     *
     * @param setVolume
     */
    public void setVolume(float setVolume) {
        volume = setVolume;
    }

    /**
     * Get the volume of music.
     *
     * @return
     */
    public float getVolume() {
        return volume;
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
            Gdx.app.log("debug", m.toString());

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
        // currentTrack.track.play();
        musicTracks.get(trackName).setLooping(setLooping);
        musicTracks.get(trackName).track.play();
        allPlayingTracks.add(musicTracks.get(trackName));
    }

    public static Music instance() {
        if (_instance == null) {
            _instance = new Music();
        }
        return _instance;
    }

    public String getPathToMusic() {
        return AssetProvider.getPathToAssets().toString()+"/music/";
    }

    
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        if (!isEnabled) {
            stop();
        } else {
            if (currentTrack != null) {
            currentTrack.track.play();
            }
        }
    }
    
}
