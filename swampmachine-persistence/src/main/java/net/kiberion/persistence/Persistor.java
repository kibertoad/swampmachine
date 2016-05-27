package net.kiberion.persistence;

import net.kiberion.swampmachine.assets.loaders.api.Prioritizable;

public interface Persistor extends Prioritizable{

    
    public void persistEntities();
    
}
