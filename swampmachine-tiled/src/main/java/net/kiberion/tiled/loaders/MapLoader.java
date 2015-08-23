package net.kiberion.tiled.loaders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import lombok.Getter;
import net.kiberion.assets.UiManager;
import net.kiberion.assets.loaders.AssetLoader;
import net.kiberion.tiled.MapRegistry;

public class MapLoader implements AssetLoader{

    private static final Logger log = LogManager.getLogger();

    @Getter
    private List<Path> queuedMaps = new ArrayList<>();
    
    private final MapRegistry mapRegistry;
    
    
	public MapLoader(MapRegistry mapRegistry) {
		super();
		Validate.notNull(mapRegistry);
		this.mapRegistry = mapRegistry;
	}
    
    public void queueMapLoading(Path path) {
        log.info("Load map. Path: " + path);
        AssetManager assets = UiManager.instance().assets();
        Objects.requireNonNull(assets, "Asset manager should not be null");

        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Unknown file: " + path.toString());
        }

        assets.load(path.toString().replace("\\", "/"), TiledMap.class);
        log.info("Finished queueing.");
    }

    
    public TiledMap finishLoadingMap(Path path) {
        log.info("Getting map. Path: " + path);
        AssetManager assets = UiManager.instance().assets();
        assets.finishLoading();

        TiledMap map = assets.get(path.toString().replace("\\", "/"), TiledMap.class);
        return map;
    }
    
    @Override
    public void load() {
    	for (Path path : queuedMaps) {
    		queueMapLoading(path);
    	}

    	for (Path path : queuedMaps) {
    		TiledMap map = finishLoadingMap(path);
    		mapRegistry.getRegisteredMaps().put("map", map);
    	}
    }

}
