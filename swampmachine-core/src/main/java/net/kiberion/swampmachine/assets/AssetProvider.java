package net.kiberion.swampmachine.assets;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.badlogic.gdx.Gdx;

import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.assets.readers.GDXFileReader;
import net.kiberion.swampmachine.assets.readers.SimpleFileReader;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;

public class AssetProvider {

	private static AssetProvider _instance;	
    protected boolean isInitted;
    
	private static String defaultPathToAssets = "src/main/resources/";
    private String pathToAssets;
    private AbstractFileReader fileReader;
	
    public static Path getPathToAssets () {
        if (_instance != null && _instance.isInitted) {
            return Paths.get(instance().pathToAssets);
        } else {
            return Paths.get(defaultPathToAssets);
        }
    }
    
    public static AssetProvider instance() {
    	
        try {
            return instance(defaultPathToAssets);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static AssetProvider instanceNoInit() {
        if (_instance == null) {
            _instance = new AssetProvider(defaultPathToAssets);
        }
        return _instance;
    }

    public static AssetProvider instance(String pathToAssets) throws IOException {
        setDefaultPathToAssets(pathToAssets);
        if (_instance == null) {
            _instance = new AssetProvider(pathToAssets);
        } 

        if (!_instance.isInitted) {
            _instance.init();
        }

        return _instance;
    }
    
    public AssetProvider(String pathToAssets) {
        this.pathToAssets = pathToAssets;
        
        if (Gdx.app != null) {
            fileReader = new GDXFileReader(Paths.get(pathToAssets));
        } else {
            fileReader = new SimpleFileReader(Paths.get(pathToAssets));
        }
    }
    

	public void init() throws IOException {
    	isInitted = true;
    }

	
    
    private static void setDefaultPathToAssets(String pathToAssets) {
		defaultPathToAssets = pathToAssets;
	}
    
    public static String getDefaultPathToAssets (){
    	return defaultPathToAssets;
    }

	public Map<String, CreatureModelInfo> getCreatures() {
		return null;
	}

}
