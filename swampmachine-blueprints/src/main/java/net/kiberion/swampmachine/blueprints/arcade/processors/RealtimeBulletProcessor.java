package net.kiberion.swampmachine.blueprints.arcade.processors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.blueprints.arcade.controller.api.CreatureMovementController;
import net.kiberion.swampmachine.blueprints.arcade.entities.Bullet;
import net.kiberion.swampmachine.blueprints.arcade.entityblocks.BulletHolderBlock;
import net.kiberion.swampmachine.processors.TimedProcessor;

public class RealtimeBulletProcessor extends TimedProcessor {

    @Autowired
    private BulletHolderBlock bulletHolder;
    
    @Autowired
    private CreatureMovementController controller;

    private final List<Bullet> cleanupList = new ArrayList<>();

    @Override
    public void invoke() {

        bulletHolder.getBullets().stream().parallel().forEach(bullet -> {
            controller.moveCreature(bullet, bullet.getMovementDelta());
            if (!bullet.isAlive()) {
                cleanupList.add(bullet);
            }

        });

        while (!cleanupList.isEmpty()) {
            Bullet b = cleanupList.get(0);
            bulletHolder.getBullets().remove(b);
            controller.removeEntityFromView(b);
            cleanupList.remove(0);
        }

    }

}
