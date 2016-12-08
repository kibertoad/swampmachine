package net.kiberion.audio;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Interpolation;

/**
 * Audio channel, capable of playing music and sounds
 * 
 * @author caryoscelus
 */
public class Channel {
    /**
     * Play music on this channel.
     * 
     * Note that you have to manually set the loop flag on music.
     * Panning and volume, on the other hand, will be manipulated by the channel.
     */
    public void playMusic(Music music) {
        musics.add(music);
        updateMusic(music);
        music.play();
    }
    
    /**
     * Play sound on this channel.
     * 
     * Not implemented yet.
     */
    public void playSound(Sound sound) {
    }
    
    /**
     * Fade volume
     * 
     * TODO: support different interpolation types
     */
    public void fade(float duration, float targetVolume) {
        interpolators.add(new Interpolator(
            Interpolation.fade,
            duration,
            targetVolume-volume,
            (Float value) -> setVolume(value)
        ));
    }
    
    public void update(float delta) {
        for (Interpolator interpolator : interpolators) {
            interpolator.update(delta);
        }
        interpolators.removeIf(i -> i.isDone());
    }
    
    @Setter
    @Getter
    private float pan;
    
    @Setter
    @Getter
    private float volume;
    
    private List<Music> musics = new ArrayList<>();
    
    private List<Interpolator> interpolators = new ArrayList<>();
    
    protected void updateMusic(Music music) {
        music.setPan(pan, volume);
    }
}
