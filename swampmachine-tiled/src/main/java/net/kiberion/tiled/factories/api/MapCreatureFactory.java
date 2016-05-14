package net.kiberion.tiled.factories.api;

import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;

public interface MapCreatureFactory <T extends MetadataHolderBlock> {

    public T produceCreature(String creatureTypeID, int x, int y);

}
