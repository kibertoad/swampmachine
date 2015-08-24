package net.kiberion.tiled.entities.impl;

import net.kiberion.entities.modelinfo.CreatureModelInfo;
import net.kiberion.tiled.aspects.api.CollidableAspect;
import net.kiberion.tiled.aspects.api.FormAspect;
import net.kiberion.tiled.aspects.holders.CommonMapMetadataHolderAspect;
import net.kiberion.tiled.aspects.impl.GenericFormAspect;
import net.kiberion.tiled.aspects.impl.PathfindingAspect;
import net.kiberion.tiled.entities.api.CreatureModel;

public class CommonCreatureModel extends CommonMapMetadataHolderAspect implements CreatureModel, CollidableAspect{

    private final CreatureModelInfo info;
    private final PathfindingAspect pathfinding;
    private FormAspect form = new GenericFormAspect (0.98f, 0.98f);
    
    public PathfindingAspect getPathfindingAspect() {
        return pathfinding;
    }

    public CommonCreatureModel(CreatureModelInfo info) {
        this.info = info;
        this.getMetadata().setId(info.getId());
        this.pathfinding = new PathfindingAspect(this.getPositionAspect());
    }

    //Used mostly for pseudo-creatures like cameras
    public CommonCreatureModel() {
        this.info = null;
        this.pathfinding = null;
    }

    @Override
    public FormAspect getFormAspect() {
        return form;
    }

    @Override
    public void processCollision(CollidableAspect collisionObject) {
    }

    @Override
    public boolean canCollide(CollidableAspect collisionObject) {
        return collisionObject != this;
    }

    
    
}
