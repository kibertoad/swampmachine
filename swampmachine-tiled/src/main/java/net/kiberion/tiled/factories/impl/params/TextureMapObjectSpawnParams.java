package net.kiberion.tiled.factories.impl.params;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.aspects.api.MetadataHolderAspect;
import net.kiberion.swampmachine.factories.params.CommonSpawnParams;

public class TextureMapObjectSpawnParams extends CommonSpawnParams{

    @Getter
    @Setter
    private MetadataHolderAspect modelEntity;
    
}
