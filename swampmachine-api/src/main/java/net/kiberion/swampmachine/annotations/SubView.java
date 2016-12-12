package net.kiberion.swampmachine.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.kiberion.swampmachine.api.view.StateView;


/**
 * Apply this to the state that uses additional overlay view for GUI
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface SubView {
    
    //Used both for events and for script binding
    String id();
    
    //Used for automatic binding to parent view after spring context is initted
    Class<? extends StateView>[] parentViews();
    
    boolean usesOverlayStage() default true;
    
    //Whether this subview should be kept when new shown subview hides previous subviews
    boolean isConstant() default false;
    
    //Priority of being rendered. Higher zIndex means being drawn on top (later)
    int zIndex() default 50;
    
}
