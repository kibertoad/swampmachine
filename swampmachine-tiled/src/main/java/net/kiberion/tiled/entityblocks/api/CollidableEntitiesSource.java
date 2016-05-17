package net.kiberion.tiled.entityblocks.api;

import java.util.List;

public interface CollidableEntitiesSource {

    public List<? extends CollidableBlock> getCollidableEntities();
    
}
