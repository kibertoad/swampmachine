package net.kiberion.tiled.entityblocks.impl;

import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.entityblocks.api.EntityInstanceMetadataBlock;
import net.kiberion.tiled.entityblocks.api.CollidableBlock;
import net.kiberion.tiled.entityblocks.api.FormBlock;


public class WallCollidableBlock implements CollidableBlock{

    @Override
    public boolean removeOnCollision() {
        return false;
    }

    @Override
    public void processCollision(CollidableBlock collisionObject) {
    }

    @Override
    public FormBlock getFormAspect() {
        return null;
    }

    @Override
    public CommonPosition getPositionAspect() {
        return null;
    }

    @Override
    public EntityInstanceMetadataBlock getMetadata() {
        return null;
    }

    @Override
    public void setMetadata(EntityInstanceMetadataBlock metadata) {
    }

    @Override
    public boolean canCollide(CollidableBlock collisionObject) {
        return true;
    }


}
