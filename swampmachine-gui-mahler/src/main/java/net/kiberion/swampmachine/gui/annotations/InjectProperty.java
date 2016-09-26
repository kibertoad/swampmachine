package net.kiberion.swampmachine.gui.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Injects subNodes <methodProperties> from a node <id> using annotated method
 * @author kibertoad
 *
 */

@Target({ ElementType.METHOD })
@Retention(RUNTIME)
public @interface InjectProperty {

    String id();
    String[] methodProperties() default {};
    
}
