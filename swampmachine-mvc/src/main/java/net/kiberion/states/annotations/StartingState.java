package net.kiberion.states.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Apply this to the first in-game GameState that is shown after loading is done
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface StartingState {}
