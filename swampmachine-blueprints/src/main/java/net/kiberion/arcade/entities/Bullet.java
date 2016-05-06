package net.kiberion.arcade.entities;

import net.kiberion.common.aspects.api.KillableAspect;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.tiled.aspects.api.CollidableAspect;
import net.kiberion.tiled.aspects.api.FormAspect;
import net.kiberion.tiled.aspects.holders.CommonMapMetadataHolderAspect;
import net.kiberion.tiled.aspects.impl.FormHolderAspect;
import net.kiberion.tiled.aspects.impl.GenericFormAspect;
import net.kiberion.tiled.aspects.impl.WallCollidableAspect;

public class Bullet extends CommonMapMetadataHolderAspect implements FormHolderAspect, CollidableAspect, KillableAspect{

    private PositionAspect movementDelta = new PositionAspect (0, 0);
    private GenericFormAspect formAspect = new GenericFormAspect(0.1f, 0.1f);

    private boolean isDestroyed;
    private CollidableAspect shooter;
    
    public CollidableAspect getShooter() {
        return shooter;
    }

    public void setShooter(CollidableAspect shooter) {
        this.shooter = shooter;
    }

    public boolean ignoresShooter () {
        return true;
    }
    
    public PositionAspect getMovementDelta() {
        return movementDelta;
    }

    @Override
    public FormAspect getFormAspect() {
        return formAspect;
    }
    
    @Override
    public void processCollision(CollidableAspect collisionObject) {
        if (collisionObject instanceof WallCollidableAspect) {
            this.kill();
        }

        //if (collisionObject instanceof Creature2) {
        //        this.kill();
        //}

    }
    
    @Override
    public void kill() {
        isDestroyed = true;
    }
    
    @Override
    public boolean isAlive() {
        return !isDestroyed;
    }

    @Override
    public boolean removeOnCollision() {
        return true;
    }

    @Override
    public boolean canCollide(CollidableAspect collisionObject) {
        return (collisionObject != this && (collisionObject != shooter || !ignoresShooter()));
    }

    @Override
    public String toString() {
        return getPositionAspect().toString();
    }
    
}
