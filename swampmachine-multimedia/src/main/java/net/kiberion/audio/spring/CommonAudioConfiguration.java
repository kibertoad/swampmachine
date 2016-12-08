package net.kiberion.audio.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.audio.AudioService;
import net.kiberion.audio.Mixer;
import net.kiberion.audio.SoundRegistry;

@Configuration
public class CommonAudioConfiguration {

    @Bean
    public Mixer mixer() {
        return new Mixer();
    }
    
    @Bean
    public SoundRegistry soundRegistry() {
        return new SoundRegistry();
    }
    
    @Bean
    public AudioService audioService() {
        return new AudioService();
    }
    
}
