package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Implementation of an AssetManager that uses dummy TextureLoader which works without OpenGL context.
 * This implementation is intended for use in unit tests.
 * 
 * @author kibertoad
 *
 */
public class HeadlessAssetManager extends AssetManager{

    private TextureLoader textureLoader; 
    
    public HeadlessAssetManager() {
        super();
        textureLoader = new HeadlessTextureLoader(getFileHandleResolver());;
    }
    
    @Override
    public <T> AssetLoader<?, ?> getLoader (final Class<T> type, final String fileName) {
        if (type == Texture.class) {
            return textureLoader;
        }
        
        return super.getLoader(type, fileName);
    }
    
}
