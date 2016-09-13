package net.kiberion.swampmachine.gui.elements;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;

@JsonDeserialize(using = CompositionElementDeserializer.class)
public class CompositionElement {

    @Getter
    @Setter
    private String id;
    
    @Getter
    private CompositionElement parent;
    
}
