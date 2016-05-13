package net.kiberion.swampmachine.entities.common.api;

import java.util.Set;

/**
 * @author kibertoad
 */
public interface Taggable {

    public boolean hasTag(String theTag);

    default public void addTag (String theTag) {
        if (!hasTag(theTag)) {
            getTags().add(theTag);
        }
    }
    
    public Set<String> getTags(); 
}
