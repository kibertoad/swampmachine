package net.kiberion.swampmachine.factories.params;

import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;

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
    
    public PositionAspect getPosition() {
        return new CommonPosition (x, y);
    }
    
    public CommonSpawnParams() {
    }
    
    public CommonSpawnParams (String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
}
