package net.kiberion.swampmachine.gui.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface ElementPrototype {

    String id();
    String[] supportedProperties () default {};
}
