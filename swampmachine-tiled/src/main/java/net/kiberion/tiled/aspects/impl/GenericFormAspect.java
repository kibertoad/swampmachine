package net.kiberion.tiled.aspects.impl;

import com.badlogic.gdx.math.Rectangle;

import net.kiberion.entities.map.api.Position;
import net.kiberion.tiled.aspects.api.FormAspect;

public class GenericFormAspect implements FormAspect{

    private float widthInTiles = 0.98f;
    private float heightInTiles = 0.98f;
    private Rectangle rectangle;
    
    public GenericFormAspect(float width, float height) {
        this.widthInTiles = width;
        this.heightInTiles = height;
    }
    
    public GenericFormAspect() {
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
