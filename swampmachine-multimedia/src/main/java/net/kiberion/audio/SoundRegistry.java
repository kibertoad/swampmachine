package net.kiberion.audio;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;

@ImmutableRegistry
public class SoundRegistry {

    @Getter
    private final Map <String, Music> musicEntries = new HashMap<>();
    
    @Getter
    private final Map <String, Sound> soundEntries = new HashMap<>();
    
    
}
