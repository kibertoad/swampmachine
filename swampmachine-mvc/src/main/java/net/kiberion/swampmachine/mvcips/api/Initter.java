package net.kiberion.swampmachine.mvcips.api;

/**
 * Kicks in after all prestartup loaders have finished loading
 * @author kibertoad
 *
 */
public interface Initter {

    public void init();
    
}
