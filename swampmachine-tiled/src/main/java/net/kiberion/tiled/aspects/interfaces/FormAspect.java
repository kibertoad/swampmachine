package net.kiberion.tiled.aspects.interfaces;


import com.badlogic.gdx.math.Rectangle;

import net.kiberion.entities.map.api.Position;

public interface FormAspect {

    public float getWidthInTiles ();
    public float getHeightInTiles ();
    
    public void setWidthInTiles (float width);
    public void setHeightInTiles (float height);
    
    public void produceRectangle (Position position);
    public void produceRectangle (Position position, float widthInTiles, float heightInTiles);
    public void moveRectangle (Position position);
    public Rectangle getRectangle();
    
}
