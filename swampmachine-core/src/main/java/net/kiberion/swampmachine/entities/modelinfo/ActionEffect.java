package net.kiberion.swampmachine.entities.modelinfo;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kibertoad
 */
public class ActionEffect {

    @Getter
    @Setter
    private String effectCode;

    @Getter
    @Setter
    private int value;

    @Getter
    @Setter
    private Map<String, Object> properties;

}
