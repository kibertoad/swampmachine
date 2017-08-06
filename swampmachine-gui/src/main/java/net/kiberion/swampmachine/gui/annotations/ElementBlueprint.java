package net.kiberion.swampmachine.gui.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Defines rules for deserializing this entity
 */
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface ElementBlueprint {

    String id();
    String[] supportedProperties () default {};
    String[] constructorProperties() default {}; 
    
    
}
