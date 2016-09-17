package net.kiberion.swampmachine.gui.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RUNTIME)
public @interface Bound {

    String id();
    String composition() default "";
    
}
