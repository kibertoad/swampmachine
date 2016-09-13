package net.kiberion.swampmachine.gui.composer.populators;

import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.composer.Composition;

public interface Populator {

    /**
     * Generates UI entities based on a composition
     * @param targetConsumer
     * @param sourceComposition
     */
    public void populate (CompositionConsumer targetConsumer, Composition sourceComposition);
    
}
