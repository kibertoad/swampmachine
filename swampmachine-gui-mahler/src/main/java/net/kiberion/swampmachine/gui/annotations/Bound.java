package net.kiberion.swampmachine.gui.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Populator will inject fields marked with this annotation with references to elements instantiated from JSON element with
 * specified id from specified composition. If no composition is specified, any will do
 * @author kibertoad
 *
 */
@Target({ ElementType.FIELD })
@Retention(RUNTIME)
public @interface Bound {

    String id();
    String composition() default "";
    
}
