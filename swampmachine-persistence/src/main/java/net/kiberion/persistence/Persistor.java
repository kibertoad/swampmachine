package net.kiberion.persistence;

import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;

/**
 * Classes, implementing this interface, are capable of persisting some entities (depending on implementation) in a way that depends on their exact implementation.
 * Persistors are SyncAssetLoaders, and thus their implementations can be annotated with {@link LoadOnStartup} to fire them up automatically.
 * @author kibertoad
 *
 */
public interface Persistor extends SyncAssetLoader{

    public void persistEntities();
    
    @Override
    public default void load() {
        persistEntities();
    }
}
