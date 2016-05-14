package net.kiberion.tiled.factories.impl.params;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.factories.params.CommonSpawnParams;

public class TextureMapObjectSpawnParams extends CommonSpawnParams{

    @Getter
    @Setter
    private MetadataHolderBlock modelEntity;
    
}
