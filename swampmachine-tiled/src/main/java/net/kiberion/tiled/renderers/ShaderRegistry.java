package net.kiberion.tiled.renderers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import lombok.Getter;

@Component
public class ShaderRegistry {
	
	static {
		ShaderProgram.pedantic = false;
	}
	
	@Getter
	private Map<String, ShaderProgram> registeredPrograms = new HashMap<>();
	
	@Getter
	private Map<String, String> registeredShaders = new HashMap<>();
	
}
