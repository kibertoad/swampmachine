package net.kiberion.tiled.aspects.api;


import com.badlogic.gdx.math.Rectangle;

import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;

public interface FormAspect {

    public float getWidthInTiles ();
    public float getHeightInTiles ();
    
    public void setWidthInTiles (float width);
    public void setHeightInTiles (float height);
    
    public void produceRectangle (PositionAspect position);
    public void produceRectangle (PositionAspect position, float widthInTiles, float heightInTiles);
    public void moveRectangle (PositionAspect position);
    public Rectangle getRectangle();
    
}
