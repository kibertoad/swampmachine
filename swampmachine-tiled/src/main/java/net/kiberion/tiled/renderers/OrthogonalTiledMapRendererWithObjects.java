package net.kiberion.tiled.renderers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class OrthogonalTiledMapRendererWithObjects extends OrthogonalTiledMapRenderer{

    private static final Logger log = LogManager.getLogger(); 
    
    public OrthogonalTiledMapRendererWithObjects(TiledMap map) {
        super(map);
    }

    @Override
    public void renderObject(MapObject object) {
        if(object instanceof TextureMapObject) {
            TextureMapObject textureObj = (TextureMapObject) object;
                batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());
                
                log.info("TextureMapObject coords: "+ textureObj.getX()+"/"+ textureObj.getY());
        }
    }  
}
