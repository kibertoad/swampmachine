package net.kiberion.audio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

public class AudioService {

    private static final Logger log = LogManager.getLogger();
    
    private static final String MUSIC_CHANNEL_ID = "music";
    private static final String SOUND_CHANNEL_ID = "sound";
    
    private Channel musicChannel;
    private Channel soundChannel;
    
    private Mixer mixer;
    
    @Autowired
    private SoundRegistry soundRegistry;
    
    @Autowired
    public void setMixer(Mixer mixer) {
        this.mixer = mixer;
        musicChannel = mixer.newChannel(MUSIC_CHANNEL_ID);
        soundChannel = mixer.newChannel(SOUND_CHANNEL_ID);
    }
    
    @EventListener
    public void playSound (PlaySoundEvent event) {
        log.info("Play sound");
        soundChannel.playSound(soundRegistry.getSoundEntries().get(event.getSoundId()));
    }
    
    @EventListener
    public void playMusic (PlayMusicEvent event) {
        log.info("Play music");
        musicChannel.playMusic(soundRegistry.getMusicEntries().get(event.getMusicId()));
    }
    
}
