/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.mvcips.states.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.UiManager;

/**
 * Pyramide flavor of gdx's Game class
 *
 * @author kibertoad
 */
public class GameApplication extends Game {

    private static final Logger log = LogManager.getLogger();

    private GameConfig config = new GameConfig();

    private static final String PATH_TO_GAME_CONFIG = "data/config.yml";

    public String dataDirectory = "src/main/resources";

    @SuppressWarnings("unchecked")
    protected void loadConfig() {
        config.setMusicEnabled(false);

        Yaml yaml = new Yaml();
        Map<String, Object> configMap;
        if (Gdx.files.internal(PATH_TO_GAME_CONFIG).exists()) {
            log.info("Load game config from file: " + PATH_TO_GAME_CONFIG);
            try {
                configMap = (Map<String, Object>) yaml
                        .load(new String(Files.readAllBytes(Paths.get("data", "config.yml"))));
                if (configMap.containsKey("music")) {
                    config.setMusicEnabled(((Boolean) configMap.get("music")));
                }
            } catch (IOException ex) {
                log.error("IO exception: ", ex);
            }
        } else {
            log.info("Game config not found at " + PATH_TO_GAME_CONFIG + ", skip loading.");
        }

    }

    @Override
    public void render() {
        Gdx.graphics.getGL30().glClear(GL30.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    // ToDo find a proper non-coupling way
    /*
     * public void loadAllData() { Music.instance().load();
     * Sound.instance().load(); }
     */

    @Override
    public void create() {
        TextureAtlas atlas = new TextureAtlas(dataDirectory + "/imgpacked/packed.atlas");

        UiManager.instance().loadSkin(Gdx.files.internal(dataDirectory + "/uiskin/uiskin.json"));
        UiManager.instance().loadTransparentSkin(Gdx.files.internal(dataDirectory + "/uiskin/uiskin_transp.json"));
        UiManager.instance().setAtlas(atlas);
    }

}
