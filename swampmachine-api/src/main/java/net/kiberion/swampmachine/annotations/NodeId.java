package net.kiberion.swampmachine.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Link to a specific JSON Node
 * When deserializing JSON, described entity will be passed to the method with this annotation
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RUNTIME)
public @interface NodeId {

    String id();
    
}
