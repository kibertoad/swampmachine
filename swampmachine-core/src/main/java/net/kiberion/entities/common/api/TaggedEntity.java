package net.kiberion.entities.common.api;

import java.util.Set;

/**
 * @author kibertoad
 */
public interface TaggedEntity {

    public boolean hasTag(String theTag);
    public Set<String> getTags(); 
}
