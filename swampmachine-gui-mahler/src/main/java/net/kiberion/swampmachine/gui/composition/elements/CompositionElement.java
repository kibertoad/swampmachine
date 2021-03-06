package net.kiberion.swampmachine.gui.composition.elements;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entityblocks.api.IdHolderBlock;

@JsonDeserialize(using = CompositionElementDeserializer.class)
public class CompositionElement implements IdHolderBlock, Comparable<CompositionElement> {

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Position position;

    @Getter
    private Map<String, Object> properties = new HashMap<>();

    @Getter
    private CompositionElement parent;

    @Getter
    @Setter
    private int zIndex = 50; // The z-index property specifies the stack order
                             // of an element. An element with greater stack
                             // order is always in front of an element with a
                             // lower stack order.

    @Override
    public String toString() {
        return type + ": " + id;
    }

    @Override
    public int compareTo(CompositionElement o) {
        if (o == null) {
            return 1;
        }
        return Integer.compare(zIndex, o.getZIndex());
    }
}
