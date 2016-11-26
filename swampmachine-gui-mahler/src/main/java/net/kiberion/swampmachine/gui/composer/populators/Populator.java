package net.kiberion.swampmachine.gui.composer.populators;

import java.util.Collection;

import net.kiberion.swampmachine.api.composition.CompositionConsumer;
import net.kiberion.swampmachine.gui.composer.Composition;

public interface Populator {

    /**
     * Generates UI entities based on a composition
     * @param targetConsumer
     * @param sourceComposition
     */
    public void populate (CompositionConsumer targetConsumer, Collection<Composition> sourceComposition);
    
}
