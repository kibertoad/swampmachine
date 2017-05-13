package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;

/**
 * This class is used to sidestep GDX requirement for having OpenGL context
 * while loading assets. Since unit tests typically don't have it, trying to
 * load assets from tests fail with default GDX loader implementation.
 * <p>
 * This implementation doesn't load any actual textures (which aren't that
 * useful for model unit tests anyway), but doesn't fail either
 * 
 * @author kibertoad
 *
 */
public class HeadlessTextureLoader extends TextureLoader {

    public HeadlessTextureLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextureParameter parameter) {
        info.filename = fileName;
        if (parameter == null || parameter.textureData == null) {
            info.texture = null;

            if (parameter != null) {
                info.texture = parameter.texture;
            }

            info.data = new PixmapTextureData(null, Format.RGB565, false, false);
        } else {
            info.data = parameter.textureData;
            info.texture = parameter.texture;
        }
    }

    @Override
    public Texture loadSync(AssetManager manager, String fileName, FileHandle file, TextureParameter parameter) {
        return new HeadlessTexture(info.data);
    }

    private static class HeadlessTexture extends Texture {

        private int height = 1;
        private int width = 1;

        public HeadlessTexture(TextureData data) {
            super(data);
        }

        @Override
        public void load(TextureData data) {
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public int getWidth() {
            return width;
        }

    }
}
