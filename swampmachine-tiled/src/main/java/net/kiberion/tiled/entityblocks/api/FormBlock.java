package net.kiberion.tiled.entityblocks.api;


import com.badlogic.gdx.math.Rectangle;

import net.kiberion.swampmachine.entities.spatial.api.Position;

public interface FormBlock {

    public float getWidthInTiles ();
    public float getHeightInTiles ();
    
    public void setWidthInTiles (float width);
    public void setHeightInTiles (float height);
    
    public void produceRectangle (Position position);
    public void produceRectangle (Position position, float widthInTiles, float heightInTiles);
    public void moveRectangle (Position position);
    public Rectangle getRectangle();
    
}
