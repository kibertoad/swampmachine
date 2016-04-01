package net.kiberion.assets;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.google.inject.Singleton;

import lombok.Getter;
import lombok.Setter;

@Singleton
@Component
public class GameConfig {

    public static final GameConfig config = new GameConfig();
    
	@Setter
	@Getter
	private boolean musicEnabled = false;
	
	@Getter
	private String pathToResourcesAsString = "src/main/resources";
	
	@Getter
	private Path pathToResources = Paths.get(pathToResourcesAsString);
	
	public void setPathToResourcesAsString(String pathToResourcesAsString) {
        this.pathToResourcesAsString = pathToResourcesAsString;
        this.pathToResources = Paths.get(pathToResourcesAsString);
    }
}
