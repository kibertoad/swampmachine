package net.kiberion.swampmachine.gui.elements.toolbar;

import java.util.Collection;

import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.registries.UnlockableEntityRegistry;

public class UnlockableEntitiesCommonToolbar<T extends MetadataHolderBlock, R extends UnlockableEntityRegistry<T>> extends CommonInstancesToolbar<T, R>{

    @Override
    protected Collection<T> getAllEntities() {
        return getModel().getAllUnlockedEntities();
    }
    
}
