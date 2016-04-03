package net.kiberion.spring;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.HeadlessTextureLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;

public class HeadlessAssetManager extends AssetManager{

    private TextureLoader textureLoader; 
    
    public HeadlessAssetManager() {
        super();
        textureLoader = new HeadlessTextureLoader(getFileHandleResolver());;
    }
    
    public <T> AssetLoader getLoader (final Class<T> type, final String fileName) {
        if (type == Texture.class) {
            return textureLoader;
        }
        
        return super.getLoader(type, fileName);
    }
    
}
