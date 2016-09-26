package net.kiberion.swampmachine.gui.elements.toolbar;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.entityblocks.api.EntityMetadataProvider;
import net.kiberion.swampmachine.entityblocks.api.EntityProvider;
import net.kiberion.swampmachine.entityblocks.api.IdHolderBlock;

/**
 * This toolbar is used when entities on a toolbar rely on external metadata
 * source
 *
 * @author kibertoad
 *
 * @param <T> Class of entities that are displayed on the toolbar
 * @param <M> Source of entity instances that are displayed on the toolbar
 * @param <S> Source of entity metadata
 */
public class CommonModelDescriptorsToolbar<T extends IdHolderBlock, M extends EntityProvider<T>, S extends EntityMetadataProvider<? extends EntityModelDescriptor>>
        extends AbstractToolbar<T, M> {

    @Autowired
    private S metadataSource;

    @Override
    protected String getEntityName(T entity) {
        return metadataSource.getMetadataForEntity(entity.getId()).getName();
    }
}
