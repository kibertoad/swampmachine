package net.kiberion.tiled.factories.impl.params;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.factories.CommonSpawnParams;

public class TextureMapObjectSpawnParams extends CommonSpawnParams{

    @Getter
    @Setter
    private MetadataHolderAspect modelEntity;
    
}
