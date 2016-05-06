package net.kiberion.assets.loaders.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import net.kiberion.assets.GameConfig;
import net.kiberion.assets.loaders.api.SyncAssetLoader;
import net.kiberion.assets.loaders.util.FileReaderFactory;
import net.kiberion.assets.readers.AbstractFileReader;

public class GameConfigLoader implements SyncAssetLoader {

    private static final Logger log = LogManager.getLogger();

    private GameConfig config;

    public GameConfigLoader(GameConfig config) {
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void load() {
        AbstractFileReader reader = FileReaderFactory.buildFileReader(Paths.get("data"));

        Yaml yaml = new Yaml();
        Map<String, Object> configMap;
        if (reader.relativeFileExists(Paths.get("config.yml"))) {
            try {
                configMap = (Map<String, Object>) yaml
                        .load(new String(Files.readAllBytes(Paths.get("data", "config.yml"))));
                if (configMap.containsKey("music")) {
                    config.setMusicEnabled(((Boolean) configMap.get("music")));
                }
            } catch (IOException ex) {
                log.warn("Failed to load config: ", ex);
            }
        }
    }

}
