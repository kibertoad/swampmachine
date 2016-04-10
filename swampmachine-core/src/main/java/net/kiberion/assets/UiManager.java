package net.kiberion.assets;

import java.nio.file.Path;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import net.kiberion.styling.StyleFactory;

/**
 * Ui manager.
 * Currently just initializes skins;
 * Note that this implementation will be replaced with
 * using external one..
 * @author caryoscelus
 */
public class UiManager {

    public static final String BLANK_IMAGE = "BLANK"; 
    public StyleFactory fontGenerator = new StyleFactory();

    private static UiManager _instance;
    
    public static UiManager instance () {
        if (_instance == null) {
            _instance = new UiManager();
        }
        return _instance;
    }
    
    protected Skin defaultSkin;
    protected Skin transparentSkin;

    protected TextureAtlas defaultAtlas;
    protected AssetManager assets;
    
    public Skin skin () {
        return defaultSkin;
    }
    
    public void setSkin (Skin skin) {
        defaultSkin = skin;
    }
    
    public Skin transparentSkin () {
        return transparentSkin;
    }
    
    public void setTransparentSkin (Skin skin) {
        transparentSkin = skin;
    }
    
    
    /**
     * Load skin from json file.
     * @param f FileHandle containing json skin
     */
    public void loadSkin (FileHandle f) {
        Skin skin = new Skin(f);
        defaultSkin = skin;
    }
    
    public void loadTransparentSkin (FileHandle f) {
        Skin skin = new Skin(f);
        transparentSkin = skin;
    }
    

    public void setAtlas (TextureAtlas setAtlas){
        defaultAtlas = setAtlas;
        setFiltering();
    }

    public void setFiltering (){
        //for (Texture texture: defaultAtlas.getTextures()) {
            //texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //}
    }

    public TextureAtlas atlas() {
        return defaultAtlas;
    }

    public void setAssets (AssetManager assets) {
        this.assets = assets;
    }

    public AssetManager assets() {
        return assets;
    }
    
    public TextureAtlas.AtlasRegion getImage (String id){
        if (!id.equals(BLANK_IMAGE)) {

            TextureAtlas.AtlasRegion region = atlas().findRegion(id);

            if (region == null) {
                //Log.warn("No image in atlas: " + id);
            }

            return region;
        } else {
            return null;
        }        
    }

    public static void reset() {
        _instance = null;
    }
    
    public String getPathForAssetManager (Path path) {
        return path.toString().replace("\\", "/");        
    }
    
}

