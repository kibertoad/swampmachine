package net.kiberion.tiled.entities.impl;

import net.kiberion.tiled.entities.api.CreatureModel;
import net.kiberion.tiled.entityblocks.api.CollidableBlock;
import net.kiberion.tiled.entityblocks.api.FormBlock;
import net.kiberion.tiled.entityblocks.holders.CommonMapMetadataHolderBlock;
import net.kiberion.tiled.entityblocks.impl.GenericFormBlock;
import net.kiberion.tiled.entityblocks.impl.PathfindingBlock;

public class CommonCreatureModel extends CommonMapMetadataHolderBlock implements CreatureModel{

    private final CreatureModel info;
    private final PathfindingBlock pathfinding;
    private FormBlock form = new GenericFormBlock (0.98f, 0.98f);
    
    public PathfindingBlock getPathfindingAspect() {
        return pathfinding;
    }

    public CommonCreatureModel(CreatureModel info) {
        this.info = info;
        this.getMetadata().setId(info.getId());
        this.pathfinding = new PathfindingBlock(this.getPositionAspect());
    }

    //Used mostly for pseudo-creatures like cameras
    public CommonCreatureModel() {
        this.info = null;
        this.pathfinding = null;
    }

    @Override
    public FormBlock getFormAspect() {
        return form;
    }

    @Override
    public void processCollision(CollidableBlock collisionObject) {
    }

    @Override
    public boolean canCollide(CollidableBlock collisionObject) {
        return collisionObject != this;
    }

    
    
}
