package net.kiberion.swampmachine.entities.common.impl;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.api.EntityInstanceModel;
import net.kiberion.swampmachine.entityblocks.api.EntityInstanceMetadataBlock;
import net.kiberion.swampmachine.entityblocks.impl.CommonMetadataBlock;

public class CommonEntityInstanceModel implements EntityInstanceModel{

    @Getter
    @Setter
    private EntityInstanceMetadataBlock metadata = new CommonMetadataBlock();

}
