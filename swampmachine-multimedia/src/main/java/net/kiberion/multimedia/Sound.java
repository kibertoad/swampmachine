/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.multimedia;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Getter;
import lombok.Setter;

/**
 * Play sounds.
 * 
 * @author Cameron Seebach
 */
public class Sound {

    private static final Logger log = LogManager.getLogger();

    public static Sound _instance;
    public SoundObject currentSound = null;
    public boolean isEnabled = true;

    @Getter
    @Setter
    private float volume = 0.75f;

    @Getter
    private Map<String, SoundObject> soundEffects = new HashMap<>();

    /**
     * Load all the sound files.
     */
    public void load() {
        String dirPath = "data/sounds/";

        File dir = new File(dirPath);
        String[] fileList = dir.list();

        if (fileList == null) {
            log.warn("Sound directory does not exist!");
        } else {
            for (String fileEntry : fileList) {
                if ((fileEntry.endsWith(".wav") || fileEntry.endsWith(".ogg"))) {
                    soundEffects.put(fileEntry.replace(".ogg", ""), new SoundObject(dirPath + fileEntry));
                }
            }
        }

    }

    /**
     * Play the sound with the name name.
     * 
     * @param name
     */
    public void play(String name) {
        currentSound = this.soundEffects.get(name);

        if (currentSound == null) {
            outputValues();
        }

        currentSound.getTrack().play(volume);
    }

    private void outputValues() {
        for (Object o : this.soundEffects.entrySet()) {
            log.debug("sound: " + ((SoundObject) o).getCode());
        }
    }

    public static Sound instance() {
        if (_instance == null) {
            _instance = new Sound();
        }
        return _instance;
    }

}
