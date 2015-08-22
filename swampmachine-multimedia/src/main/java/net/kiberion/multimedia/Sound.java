/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.multimedia;

import java.io.File;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;

/**
 * Play sounds.
 * @author Cameron Seebach
 */
public class Sound {
	
	private static final Logger log = LogManager.getLogger();

    public static Sound _instance;
    public SoundObject currentSound = null;
    public boolean isEnabled = true;
    public float volume = 0.75f;
    public HashMap<String, SoundObject> soundEffects = new HashMap<String, SoundObject>();
    
    /**
     * Load all the sound files.
     */
    public void load(){
        
        String dirPath = "data/sounds/";

        File dir = new File(dirPath);
        String[] fileList = dir.list();

        if (fileList == null) {
        	log.warn("Sound directory does not exist!");
        } else {

            for (int x = 0; x < fileList.length; x++) {

                if ((fileList[x].endsWith(".wav") || fileList[x].endsWith(".ogg"))) {
                    soundEffects.put(fileList[x].replace(".ogg",""), new SoundObject(dirPath + fileList[x]));

                }
            }
        }
        
    }
    
    /**
     * Play the sound with the name name.
     * @param name
     */
    public void play(String name){
        currentSound = this.soundEffects.get(name);

        if (currentSound == null) {
            outputValues();
        }

        currentSound.track.play(volume);
    }

    private void outputValues() {
        for (Object o: this.soundEffects.entrySet()) {
            Gdx.app.log("debug", "sound: "+((SoundObject)o).code);
        }
    }

    /**
     * Set the volume of sounds.
     * @param volume 
     */
    public void setVolume(float volume){
        this.volume = volume;
    }
    
    /**
     * Get the volume of sounds.
     * @return 
     */
    public float getVolume(){
        return volume;
    }

    public static Sound instance (){
        if (_instance == null) {
            _instance = new Sound();
        }
        return _instance;
    }

}
