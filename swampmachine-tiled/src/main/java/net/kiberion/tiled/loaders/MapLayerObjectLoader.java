package net.kiberion.tiled.loaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.tiled.TiledIsometricCoordsUtils;
import net.kiberion.tiled.aspects.holders.CommonMapMetadataHolderAspect;
import net.kiberion.tiled.model.TiledMapInfo;

public class MapLayerObjectLoader <TModel extends AbstractTiledMapModel>{

    private static final Logger log = LogManager.getLogger();
    
    public void load (TModel model, TiledMap map, TiledMapInfo mapInfo) {
        
        if (map == null) {
            throw new IllegalArgumentException ("Map is null");
        }
        
        MapLayer activeObjectLayer = map.getLayers().get("activeobjects");
        if (activeObjectLayer != null) {
            for (MapObject object : activeObjectLayer.getObjects()) {
                CommonMapMetadataHolderAspect activeObject = new CommonMapMetadataHolderAspect();
                activeObject.getMetadata().setId(object.getProperties().get("id", String.class));
                
                float x = object.getProperties().get("x", Float.class).floatValue();
                float y = object.getProperties().get("y", Float.class).floatValue();

                PositionAspect modelPosition = TiledIsometricCoordsUtils.getModelCoordsForTiledMapPosition(new CommonPosition (x, y), mapInfo, true);
                int intX = (int) modelPosition.getX();
                int intY = (int) modelPosition.getY();
                
                activeObject.getPositionAspect().setX(intX);
                activeObject.getPositionAspect().setY(intY);
                
                model.getActiveObjects().add(activeObject);
                model.getActiveObjectsMap().put(activeObject.getPositionAspect().produceCloneWithTrimmedValues(), activeObject);
            }
        }
        
        log.info("Done loading map pbjects");
        
    }
    
}
