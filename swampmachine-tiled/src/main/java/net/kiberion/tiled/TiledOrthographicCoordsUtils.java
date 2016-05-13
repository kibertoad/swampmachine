package net.kiberion.tiled;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;

import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.tiled.model.TiledMapInfo;

public class TiledOrthographicCoordsUtils {

	private static final Logger log = LogManager.getLogger();
	
	public static PositionAspect getOrthographicScreenCoords(PositionAspect position, TiledMapInfo mapInfo) {
		CommonPosition result = new CommonPosition ();

        float screenPositionX = (position.getX() * mapInfo.getTileWidth());
        float screenPositionY = ((position.getY()-1) * mapInfo.getTileHeight()); //-1 is a hack that shouldn't be needed, but the hell do I know 
        //float mapScreenSizeY = (mapInfo.getMapHeight()-1) * mapInfo.getTileHeight();		
		
	    result.setX ( screenPositionX);
        //result.setY ( mapScreenSizeY - screenPositionY);
	    result.setY ( screenPositionY);
        
        return result;
	}

	public static PositionAspect getModelCoordsForScreenPosition(PositionAspect screenPosition, PositionAspect cameraPosition,
			TiledMapInfo mapInfo) {
		CommonPosition result = new CommonPosition ();
		
        float screenSizeX;
        float screenSizeY;		
        if (Gdx.graphics != null) {
            screenSizeX = Gdx.graphics.getWidth();
            screenSizeY = Gdx.graphics.getHeight();
        } else {
            screenSizeX = 1366;
            screenSizeY = 768;
        }
        
        float halfScreenSizeX = screenSizeX / 2;
        float halfScreenSizeY = screenSizeY / 2;
        
        float x = cameraPosition.getX() + screenPosition.getX() - halfScreenSizeX;
        x = (x / mapInfo.getTileWidth());
        float y = cameraPosition.getY() + screenPosition.getY() - halfScreenSizeY;
        y = mapInfo.getMapHeight() - (y / mapInfo.getTileHeight());
        
	    result.setX ( (int)x);
        result.setY ( (int)y);
        
        log.info("Calc result: "+ result);
        
        return result;
	}

}
