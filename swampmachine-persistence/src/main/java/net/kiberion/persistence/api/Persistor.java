package net.kiberion.persistence.api;


import net.kiberion.swampmachine.assets.loaders.api.SyncLoader;

/**
 * Classes, implementing this interface, are capable of persisting some entities (depending on implementation) in a way that depends on their exact implementation.
 * Persistors are SyncAssetLoaders, and thus their implementations can be annotated with {@link net.kiberion.swampmachine.annotations.LoadOnStartup} to fire them up automatically.
 * @author kibertoad
 *
 */
public interface Persistor extends SyncLoader {

    public void persistEntities();
    
    @Override
    public default void load() {
        persistEntities();
    }
}
