package net.kiberion.states.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.kiberion.processors.TimedProcessor;

/**
 * Automatically set realtime processors for states from StateSpringLoader
 * @author igors
 *
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface RealtimeProcessors {

    /**
     * Beans of what classes should be attempted to be autowired
     * @return
     */
    Class<? extends TimedProcessor>[] beansOfClasses() default {};
    
}
