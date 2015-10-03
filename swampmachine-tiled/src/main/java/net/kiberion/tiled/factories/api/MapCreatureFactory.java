package net.kiberion.tiled.factories.api;

import net.kiberion.aspects.api.MetadataHolderAspect;

public interface MapCreatureFactory <T extends MetadataHolderAspect> {

    public T produceCreature(String creatureTypeID, int x, int y);

}