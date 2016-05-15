package net.kiberion.tiled;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.maps.tiled.TiledMap;

import lombok.Getter;

public class MapRegistry {

	@Getter
	private Map<String, TiledMap> registeredMaps = new HashMap<>();
	
}
