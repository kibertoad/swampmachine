package net.kiberion.swampmachine.assets.loaders.api;

import net.kiberion.swampmachine.api.common.Prioritizable;

/**
 * Classes that implement this interface are aware both of source and target of their loading procedure
 * @author kibertoad
 *
 */
public interface Loader extends Prioritizable {

    public void load();
    
}
