package net.kiberion.audio;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class PlayMusicEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = 3137922491373001840L;
    @Getter
    private final String musicId;
    
    public PlayMusicEvent(String musicId, Object source) {
        super(source);
        this.musicId = musicId;
    }

}
