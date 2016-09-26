package net.kiberion.swampmachine.gui.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.kiberion.swampmachine.annotations.NodeId;

/**
 * Injects transformed node <id> using annotated method
 * Has to be used together with {@link NodeId}} annotation.
 * @author kibertoad
 *
 */

@Target({ ElementType.METHOD })
@Retention(RUNTIME)
public @interface InjectTransformedProperty {

    String transformer() default ""; // identified by IdHolder annotation

}
