package net.kiberion.swampmachine.mvcips.states.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.kiberion.swampmachine.gui.view.StateView;

/**
 * Apply this to the state that uses additional overlay view for GUI
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface SubView {
    
    Class<? extends StateView> parentView();
    boolean usesOverlayStage() default true;
    
}
