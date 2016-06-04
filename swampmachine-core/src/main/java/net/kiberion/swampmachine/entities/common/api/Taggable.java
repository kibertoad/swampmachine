package net.kiberion.swampmachine.entities.common.api;

import java.util.Set;

/**
 * @author kibertoad
 */
public interface Taggable {

    default public boolean hasTag(String theTag) {
        return getTags().contains(theTag);
    }

    default public void addTag(String theTag) {
        if (!hasTag(theTag)) {
            getTags().add(theTag);
        }
    }

    public Set<String> getTags();
}
