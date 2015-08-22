package pyramide9.map2.aspects.holders;

import net.kiberion.aspects.impl.GenericMetadataHolderAspect;
import net.kiberion.entities.map.impl.PositionAspect;

public class GenericMapMetadataHolderAspect extends GenericMetadataHolderAspect implements MapMetadataHolderAspect{
    
    private final PositionAspect position = new PositionAspect(0, 0);

    @Override
    public PositionAspect getPositionAspect() {
        return position;
    }
    

}
