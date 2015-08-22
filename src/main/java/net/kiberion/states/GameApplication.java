/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.states;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.kiberion.assets.UiManager;
import net.kiberion.multimedia.Music;
import net.kiberion.multimedia.Sound;

/**
 * Pyramide flavor of gdx's Game class
 *
 * @author kibertoad
 */
@SuppressWarnings("rawtypes")
public class GameApplication extends Game {
	
	public String dataDirectory = "src/main/resources";

    protected void loadConfig() {
        Music.instance().setEnabled (false);

        Yaml yaml = new Yaml();
        Map config;
        if (Gdx.files.internal("data/config.yml").exists()) {
            try {
                config = (Map) yaml.load(new String(Files.readAllBytes(Paths.get("data", "config.yml"))));
                if (config.containsKey("music")) {
                    Music.instance().setEnabled ((Boolean) config.get("music"));
                }
            } catch (IOException ex) {
                Logger.getLogger(GameApplication.class.getName()).log(Level.WARNING, null, ex);
            }
        } else {
            Music.instance().setEnabled (false);
        }
    }

    @Override
    public void render() {
        Gdx.graphics.getGL30().glClear(GL30.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    public void loadAllData() {
        Music.instance().load();
        Sound.instance().load();
    }

    @Override
    public void create() {
        TextureAtlas atlas = new TextureAtlas(dataDirectory+"/imgpacked/packed.atlas");

        UiManager.instance().loadSkin(Gdx.files.internal(dataDirectory+"/uiskin/uiskin.json"));
        UiManager.instance().loadTransparentSkin(Gdx.files.internal(dataDirectory+"/uiskin/uiskin_transp.json"));
        UiManager.instance().setAtlas(atlas);
    }

}
