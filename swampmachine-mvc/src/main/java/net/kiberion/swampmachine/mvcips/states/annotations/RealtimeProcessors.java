package net.kiberion.swampmachine.mvcips.states.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.kiberion.swampmachine.processors.AbstractTimedProcessor;

/**
 * This annotation should be applied to states that have realtime processors.
 * Processors listed in this annotation will be automatically injected for processing from the state
 * Injection is performed from StateSpringLoader class
 * @author igors
 *
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface RealtimeProcessors {

    /**
     * Beans of what classes should be autowired as realtime processors for annotated state
     * @return
     */
    Class<? extends AbstractTimedProcessor>[] beansOfClasses() default {};
    
}
