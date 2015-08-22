/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.multimedia;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * @author kibertoad
 */
public class SoundObject {

	private static final Logger log = LogManager.getLogger();
	
    public String code;
    public Sound track;
    public float volume = (float) 0.08;

    public SoundObject(String path) {
    	log.info("Sound: Looking for " + path);
        code = path;
        boolean validFile = (new File(path)).exists();
        if (validFile) {
            track = Gdx.audio.newSound(Gdx.files.internal(path));
        } else {
            track = null;
            //  lastMusic.music = new Music ("NULL");
            log.info("Sound: Can't find " + path);
        }
    }
}
