package net.kiberion.tiled.loaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.assets.loaders.api.AbstractAsyncAssetLoader;
import net.kiberion.swampmachine.assets.loaders.util.FileReaderFactory;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;
import net.kiberion.swampmachine.utils.SetUtils;
import net.kiberion.tiled.MapRegistry;

@LoadOnStartup
public class MapLoader extends AbstractAsyncAssetLoader {

    public static final String MAP_ID_PROPERTY = "id";

    private static final Logger log = LogManager.getLogger();
    public static final String MAP_EXTENSION = "tmx";
    public static final String MAP_RESOURCES_DIRECTORY = "map-tiled";

    @Getter
    private List<Path> queuedMaps = new ArrayList<>();

    @Autowired
    private MapRegistry mapRegistry;

    @Getter
    @Setter
    private boolean autoScan = true;

    public void queueMapLoading(Path path) {
        log.info("Load map. Path: " + path);
        AssetManager assets = UiManager.instance().getAssetManager();
        Objects.requireNonNull(assets, "Asset manager should not be null");

        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Unknown file: " + path.toString());
        }

        assets.load(UiManager.instance().getPathForAssetManager(path), TiledMap.class);
        log.info("Finished queueing.");
    }

    public TiledMap finishLoadingMap(Path path) {
        log.info("Finishing loading map. Path: " + path);
        AssetManager assets = UiManager.instance().getAssetManager();
        assets.finishLoading();

        TiledMap map = assets.get(UiManager.instance().getPathForAssetManager(path), TiledMap.class);
        log.info("Finished loading map. ID: " + map.getProperties().get("id"));
        return map;
    }

    @Override
    public void startAsyncLoading() {
        if (autoScan) {
            log.info("Autoscan for maps is enabled, checking for maps at " + getConfig().getPathToResources());
            Validate.notNull(getConfig(), "Config is null");
            AbstractFileReader reader = FileReaderFactory.buildFileReader(getConfig().getPathToResources());
            try {
                queuedMaps = reader.getListOfRelativeFilesByWildcard(MAP_RESOURCES_DIRECTORY,
                        SetUtils.buildSet(MAP_EXTENSION));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        for (Path path : queuedMaps) {
            queueMapLoading(path);
        }
    }

    @Override
    public void finishAsyncLoading() {
        for (Path path : queuedMaps) {
            TiledMap map = finishLoadingMap(path);
            String id = (String) map.getProperties().get(MAP_ID_PROPERTY);
            Validate.notNull(id,
                    "Map id was null for map " + path.toString() + ". Please set it via Tiled custom map properties.");
            mapRegistry.getRegisteredMaps().put(id, map);
        }
    }

    @Override
    public void load() {
        startAsyncLoading();
        finishAsyncLoading();
    }

}
