package net.kiberion.tiled.entityblocks.impl;

import com.badlogic.gdx.math.Rectangle;

import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.tiled.entityblocks.api.FormBlock;

public class GenericFormBlock implements FormBlock{

    private float widthInTiles = 0.98f;
    private float heightInTiles = 0.98f;
    private Rectangle rectangle;
    
    public GenericFormBlock(float width, float height) {
        this.widthInTiles = width;
        this.heightInTiles = height;
    }
    
    public GenericFormBlock() {
    }
    
    @Override
    public float getWidthInTiles() {
        return widthInTiles;
    }
    @Override
    public void setWidthInTiles(float widthInTiles) {
        this.widthInTiles = widthInTiles;
    }
    @Override
    public float getHeightInTiles() {
        return heightInTiles;
    }
    @Override
    public void setHeightInTiles(float heightInTiles) {
        this.heightInTiles = heightInTiles;
    }
    
    @Override
    public void produceRectangle (Position position) {
        rectangle = new Rectangle(position.getX(), position.getY(), widthInTiles, heightInTiles);
    }
    
    @Override
    public void moveRectangle (Position position) {
        rectangle.setPosition(position.getX(), position.getY());
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void produceRectangle(Position position, float widthInTiles, float heightInTiles) {
        this.setWidthInTiles(widthInTiles);
        this.setHeightInTiles(heightInTiles);
        produceRectangle(position);
    }
}
