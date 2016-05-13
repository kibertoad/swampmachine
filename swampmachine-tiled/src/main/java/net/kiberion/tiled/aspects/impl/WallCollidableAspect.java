package net.kiberion.tiled.aspects.impl;

import net.kiberion.swampmachine.aspects.api.MetadataAspect;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.tiled.aspects.api.CollidableAspect;
import net.kiberion.tiled.aspects.api.FormAspect;


public class WallCollidableAspect implements CollidableAspect{

    @Override
    public boolean removeOnCollision() {
        return false;
    }

    @Override
    public void processCollision(CollidableAspect collisionObject) {
    }

    @Override
    public FormAspect getFormAspect() {
        return null;
    }

    @Override
    public CommonPosition getPositionAspect() {
        return null;
    }

    @Override
    public MetadataAspect getMetadata() {
        return null;
    }

    @Override
    public void setMetadata(MetadataAspect metadata) {
    }

    @Override
    public boolean canCollide(CollidableAspect collisionObject) {
        return true;
    }


}
