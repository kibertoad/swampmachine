package net.kiberion.swampmachine.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Metadata for instantiating an entity via reflection
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface ConstructableEntity {

    String id();
    
    //Array of keys for values that will be passed to constructor from the provided map of instantiation values
    String[] constructorProperties() default {};
    
}
