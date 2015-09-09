package net.kiberion.tiled.aspects.api;

import java.util.List;

public interface CollidableEntitiesSource {

    public List<? extends CollidableAspect> getCollidableEntities();
    
}
