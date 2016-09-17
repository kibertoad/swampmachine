package net.kiberion.swampmachine.gui.elements;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entityblocks.api.IdHolderBlock;

@JsonDeserialize(using = CompositionElementDeserializer.class)
public class CompositionElement implements IdHolderBlock{

    @Getter
    @Setter
    private String type;
    
    @Getter
    @Setter
    private String id;
    
    @Getter
    private CompositionElement parent;
    
}
