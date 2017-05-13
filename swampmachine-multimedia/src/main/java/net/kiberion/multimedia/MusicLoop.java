package net.kiberion.multimedia;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.audio.Music.OnCompletionListener;

import lombok.Getter;
import lombok.Setter;

public class MusicLoop {

    @Getter
    @Setter
    private boolean shuffleOnFirstPlay;

    @Getter
    @Setter
    private boolean shuffleOnReplay = true;

    private List<String> playlist = new ArrayList<>();

    private List<String> randomizedPlaylist = new ArrayList<>();

    private String lastPlayedTrack;

    //ToDo add proper loader
    /*
    private AbstractFileReader fileReader;

    public MusicLoop() {
        fileReader = new GDXFileReader();
    }

    public void addTrack(String trackName) {
        playlist.add(trackName);

        if (!fileReader.fileExists(Paths.get(Music.instance().getPathToMusic() + trackName + ".ogg"))) {
            throw new IllegalArgumentException("Track not found: " + trackName + ".ogg");
        }

        if (!shuffleOnFirstPlay) {
            randomizedPlaylist = new ArrayList<>(playlist);
        }
    }
    */

    public void reshuffle() {
        randomizedPlaylist = new ArrayList<>(playlist);

        if (shuffleOnReplay) {
            Collections.shuffle(randomizedPlaylist);
        }
    }

    public void play() {
        if (randomizedPlaylist.isEmpty()) {
            do {
                reshuffle();
            } while (randomizedPlaylist.size() > 1 && randomizedPlaylist.get(0).equals(lastPlayedTrack));
        }

        lastPlayedTrack = randomizedPlaylist.get(0);
        randomizedPlaylist.remove(lastPlayedTrack);

        OnCompletionListener onCompletion = (music) -> {
            music.stop();
            play();
        };

        Music.instance().playOnce(lastPlayedTrack, onCompletion);
    }

    public boolean isEmpty() {
        return playlist.isEmpty();
    }

}
