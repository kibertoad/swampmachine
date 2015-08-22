package pyramide9.map2.aspects.interfaces;

import java.util.Set;

import pyramide9.map2.aspects.holders.MetadataHolderAspect;

public interface SlotAspect<TSlotItem extends Object> extends MetadataHolderAspect{

    
    /**
     * Can given item be inserted into this slot
     * @param item
     * @return
     */
    public boolean isItemCompatible (TSlotItem item);
    
    /**
     * Get list of IDs of entity groups compatible with this slot 
     * @return
     */
    public Set<String> getCompatibleGroups();
    public void addCompatibleGroup(String compatibleGroup);
    
    public TSlotItem getItem ();
    public void setItem (TSlotItem item);
    
    public boolean isEmpty ();

}
