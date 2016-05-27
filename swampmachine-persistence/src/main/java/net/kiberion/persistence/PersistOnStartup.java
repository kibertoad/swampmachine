package net.kiberion.persistence;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Apply this to classes, implementing Persistor, to be persisted in the beginning of game
 * To be automatically loaded from @LoadingState classes
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RUNTIME)
public @interface PersistOnStartup {}
