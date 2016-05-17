package net.kiberion.blueprints.arcade.entities;

import net.kiberion.blueprints.common.entityblocks.api.KillableBlock;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.tiled.entityblocks.api.CollidableBlock;
import net.kiberion.tiled.entityblocks.api.FormBlock;
import net.kiberion.tiled.entityblocks.holders.CommonMapMetadataHolderBlock;
import net.kiberion.tiled.entityblocks.impl.FormHolderBlock;
import net.kiberion.tiled.entityblocks.impl.GenericFormBlock;
import net.kiberion.tiled.entityblocks.impl.WallCollidableBlock;

public class Bullet extends CommonMapMetadataHolderBlock implements FormHolderBlock, CollidableBlock, KillableBlock{

    private CommonPosition movementDelta = new CommonPosition (0, 0);
    private GenericFormBlock formAspect = new GenericFormBlock(0.1f, 0.1f);

    private boolean isDestroyed;
    private CollidableBlock shooter;
    
    public CollidableBlock getShooter() {
        return shooter;
    }

    public void setShooter(CollidableBlock shooter) {
        this.shooter = shooter;
    }

    public boolean ignoresShooter () {
        return true;
    }
    
    public CommonPosition getMovementDelta() {
        return movementDelta;
    }

    @Override
    public FormBlock getFormAspect() {
        return formAspect;
    }
    
    @Override
    public void processCollision(CollidableBlock collisionObject) {
        if (collisionObject instanceof WallCollidableBlock) {
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
    public boolean canCollide(CollidableBlock collisionObject) {
        return (collisionObject != this && (collisionObject != shooter || !ignoresShooter()));
    }

    @Override
    public String toString() {
        return getPositionAspect().toString();
    }
    
}
