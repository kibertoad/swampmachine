package net.kiberion.swampmachine.gui.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty.ElementTransformedProperties;
import net.kiberion.swampmachine.gui.api.ParameterTransformer;

/**
 * Registers conversion that has to be performed for the property with this name
 * 
 * @author kibertoad
 *
 */

@Repeatable(ElementTransformedProperties.class)
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface ElementTransformedProperty {

    public String sourceProperty();
    public Class<? extends ParameterTransformer<?, ?>> targetTransformer();
    
    @Target({ ElementType.TYPE })
    @Retention(RUNTIME)
    public @interface ElementTransformedProperties {
        ElementTransformedProperty[] value();
    }
}
