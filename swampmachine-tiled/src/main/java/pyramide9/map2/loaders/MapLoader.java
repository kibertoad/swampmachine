package pyramide9.map2.loaders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.assets.UiManager;

public class MapLoader {

    private static final Logger log = LogManager.getLogger();

    public void queueMapLoading(Path path) {
        log.info("Load map. Path: " + path);
        AssetManager assets = UiManager.instance().assets();
        Objects.requireNonNull(assets);

        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Unknown file: " + path.toString());
        }

        assets.load(path.toString(), TiledMap.class);
        log.info("Finished queueing.");
    }

    
    public TiledMap finishLoadingMap(Path path) {
        log.info("Getting map. Path: " + path);
        AssetManager assets = UiManager.instance().assets();

        TiledMap map = assets.get(path.toString().replace("\\", "/"), TiledMap.class);
        return map;
    }

}
