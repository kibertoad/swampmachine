package net.kiberion.arcade.factories;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;
import net.kiberion.arcade.aspects.BulletHolderAspect;
import net.kiberion.arcade.entities.Bullet;
import net.kiberion.entities.map.api.Position;
import net.kiberion.tiled.aspects.api.CollidableAspect;

public class BulletFactory {

    @Autowired
    @Setter
    private BulletHolderAspect bulletHolder;

    public Bullet produceBullet (Position startingPosition, Position targetPosition, float speed, CollidableAspect shooter) {
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
