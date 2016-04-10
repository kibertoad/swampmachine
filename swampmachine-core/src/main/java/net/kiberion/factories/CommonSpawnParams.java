package net.kiberion.factories;

import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;

public class CommonSpawnParams implements SpawnParams{
    
    private String id;
    private int x;
    private int y;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public Position getPosition() {
        return new PositionAspect (x, y);
    }
    
}
