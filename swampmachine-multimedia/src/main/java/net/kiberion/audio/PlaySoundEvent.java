package net.kiberion.audio;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ConstructableEntity;

@ConstructableEntity(id = "playSound", constructorProperties = {"soundId", "source"})
public class PlaySoundEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = -2617594430142686185L;
    
    @Getter
    private final String soundId;
    
    public PlaySoundEvent(Object source, String soundId) {
        super(source);
        this.soundId = soundId;
    }

}
