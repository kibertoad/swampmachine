package pyramide9.map2;

import com.badlogic.gdx.Gdx;

import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;
import pyramide9.map2.model.TiledMapInfo;

public class TiledIsometricCoordsUtils {

    public static Position getIsometricScreenCoords (Position modelCoords, TiledMapInfo mapInfo) {
        PositionAspect result = new PositionAspect ();
        
        float projectionX = (modelCoords.getX() - modelCoords.getY()) * mapInfo.getTileHalfWidth();
        float projectionY = (modelCoords.getX() + modelCoords.getY()) * mapInfo.getTileHalfHeight(); 
        float mapScreenSizeX = (mapInfo.getMapWidth()-1) * mapInfo.getTileHalfWidth();
        float mapScreenSizeY = (mapInfo.getMapHeight()-1) * mapInfo.getTileHalfHeight();
        
        result.setX ( mapScreenSizeX + projectionX);
        result.setY ( mapScreenSizeY - projectionY);
        
        return result;
    }
    
    public static Position getModelCoordsForTiledMapPosition (Position mapCoords, TiledMapInfo mapInfo, boolean invertY) {
        Position result = new PositionAspect ();

        result.setX (mapCoords.getX() / mapInfo.getTileWidth());
        
        if (invertY) {
            result.setY(mapInfo.getMapHeight() - (mapCoords.getY() / mapInfo.getTileHeight()));
        } else {
            result.setY((mapCoords.getY() / mapInfo.getTileHeight()));
        }
        
        return result;
    }
    

    // ScreenCoords is actual mouse position in this case
    public static Position getModelCoordsForScreenPosition(Position screenCoords, Position cameraCoords,
            TiledMapInfo mapInfo) {
        Position result = new PositionAspect();

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

        Position adjustedScreenCoords = new PositionAspect(cameraCoords.getX() + screenCoords.getX() - halfScreenSizeX
                - mapInfo.getTileHalfWidth(), cameraCoords.getY() + screenCoords.getY() - halfScreenSizeY
                - mapInfo.getTileHeight());

        Position adjustedResult = getModelCoordsForScreenPosition(adjustedScreenCoords, mapInfo);

        result = adjustedResult;
        return result;
    }

    //ScreenCoords are an abstract concept in this case, whatever MapRenderer uses to calculate position to draw
    public static Position getModelCoordsForScreenPosition (Position screenCoords, TiledMapInfo mapInfo) {
        PositionAspect result = new PositionAspect ();

        float mapScreenSizeX = (mapInfo.getMapWidth()-1) * mapInfo.getTileHalfWidth();
        float mapScreenSizeY = (mapInfo.getMapHeight()-1) * mapInfo.getTileHalfHeight();
        
        float screenCoordsX = screenCoords.getX() - mapScreenSizeX;
        float screenCoordsY = mapScreenSizeY - screenCoords.getY();

        float isoX = screenCoordsY / mapInfo.getTileHeight() + screenCoordsX / (mapInfo.getTileWidth());
        float isoY = (screenCoordsY * 2 / mapInfo.getTileHeight()) - isoX;
        
        result.setX((int)isoX);
        result.setY((int)isoY);
        
        return result;
    }
    
}
