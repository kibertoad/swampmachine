package net.kiberion.tiled.renderers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class IsometricTiledMapRendererWithObjects extends IsometricTiledMapRenderer{

    public IsometricTiledMapRendererWithObjects(TiledMap map) {
        super(map);
    }

    @Override
    public void renderObject(MapObject object) {
        if(object instanceof TextureMapObject) {
            TextureMapObject textureObj = (TextureMapObject) object;
                batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());
        }
    }    
}
