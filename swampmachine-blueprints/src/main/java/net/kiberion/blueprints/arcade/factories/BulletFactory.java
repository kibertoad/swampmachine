package net.kiberion.blueprints.arcade.factories;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;
import net.kiberion.blueprints.arcade.entities.Bullet;
import net.kiberion.blueprints.arcade.entityblocks.BulletHolderBlock;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.tiled.entityblocks.api.CollidableBlock;

public class BulletFactory {

    @Autowired
    @Setter
    private BulletHolderBlock bulletHolder;

    public Bullet produceBullet (Position startingPosition, Position targetPosition, float speed, CollidableBlock shooter) {
        Bullet bullet = new Bullet();
        bullet.getPositionAspect().copyFrom(startingPosition);
        
        float distance = startingPosition.distanceTo(targetPosition);
        
        float deltaX = (targetPosition.getX() - startingPosition.getX()) / distance * speed;
        float deltaY = (targetPosition.getY() - startingPosition.getY()) / distance * speed;
        
        bullet.getMovementDelta().setX(deltaX);
        bullet.getMovementDelta().setY(deltaY);
        bullet.getFormAspect().produceRectangle(bullet.getPositionAspect());
        bullet.setShooter(shooter);

        bulletHolder.getBullets().add(bullet);
        return bullet;
    }
}
