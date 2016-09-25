package net.kiberion.swampmachine.mvcips.states.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface StateController {

    /**
     * Controller will be bound to script context with this id
     * @return
     */
    public String nameInBinding () default "controller";
    
}
