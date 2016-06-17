package net.kiberion.swampmachine.gui.elements.toolbar;

import net.kiberion.swampmachine.entityblocks.api.EntityProvider;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;

/**
 *  This toolbar is used when entities on a toolbar have all the necessary
 * metadata already included on them
 * @author kibertoad
 *
 * @param <T> Class of entities that are displayed on the toolbar
 * @param <M> Source of entity instances that are displayed on the toolbar
 */
public class CommonInstancesToolbar<T extends MetadataHolderBlock, M extends EntityProvider<T>>
        extends AbstractToolbar<T, M> {

    @Override
    protected String getEntityName(T entity) {
        return entity.getMetadata().getName();
    }
}
