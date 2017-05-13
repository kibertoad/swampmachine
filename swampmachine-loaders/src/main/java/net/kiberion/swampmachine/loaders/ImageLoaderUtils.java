package net.kiberion.swampmachine.loaders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;

public class ImageLoaderUtils {

    private ImageLoaderUtils (){}
    
    public static Texture loadImageDynamically (File sourceFile) {
        Texture texture = new Texture (new FileHandle (sourceFile));
        return texture;
    }
    
    public static ViewInfo loadImageDynamically (InputStream sourceStream) {
        Gdx2DPixmap gpm;
        try {
            gpm = new Gdx2DPixmap(sourceStream, Gdx2DPixmap.GDX2D_FORMAT_RGB888);
        } catch (IOException e) {
            throw new IllegalStateException (e);
        }
        Pixmap pixmap = new Pixmap(gpm);
        Texture result = new Texture(pixmap);
        
        ViewInfo viewInfo = new ViewInfo();
        viewInfo.setPicture(pixmap);
        viewInfo.setImage(new TextureRegion (result));
        
        return viewInfo;
    }
    
}
