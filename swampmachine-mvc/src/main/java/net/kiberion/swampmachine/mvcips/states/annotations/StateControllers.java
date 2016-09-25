package net.kiberion.swampmachine.mvcips.states.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface StateControllers {

    public Class<? extends AbstractStateController>[] controllers ();
    
}
