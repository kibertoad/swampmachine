package net.kiberion.states.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Apply this to GameState that implements the loading process
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface LoadingState {}
