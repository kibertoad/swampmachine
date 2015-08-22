package net.kiberion.entities.common.api;

import java.util.List;

/**
 * @author kibertoad
 */
public interface NodeInterface extends TaggedEntity {

    public int getCustomValue(String valueCode);

    public String getExtendedName();

    public String getName();

    public void outputTags();

    public List<String> getTags();
    
    public int getRating();
}
