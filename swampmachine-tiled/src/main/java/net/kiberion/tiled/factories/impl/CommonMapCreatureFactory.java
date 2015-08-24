package net.kiberion.tiled.factories.impl;

import net.kiberion.tiled.entities.impl.CommonCreatureModel;
import net.kiberion.tiled.factories.api.MapCreatureFactory;

public class CommonMapCreatureFactory implements MapCreatureFactory<CommonCreatureModel>{

    @Override
    public CommonCreatureModel produceCreature(String creatureTypeID, int x, int y) {
        
        CommonCreatureModel creature = new CommonCreatureModel();
        creature.getMetadata().setId(creatureTypeID);
        creature.getPositionAspect().setX(x);
        creature.getPositionAspect().setY(y);
        
        return creature;
    }

}
