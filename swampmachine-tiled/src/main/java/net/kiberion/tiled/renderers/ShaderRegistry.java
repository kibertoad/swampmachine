package net.kiberion.tiled.renderers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.google.inject.Singleton;

import lombok.Getter;

@Singleton
public class ShaderRegistry {
	
	static {
		ShaderProgram.pedantic = false;
	}
	
	@Getter
	private Map<String, ShaderProgram> registeredPrograms = new HashMap<>();
	
	@Getter
	private Map<String, String> registeredShaders = new HashMap<>();
	
}
