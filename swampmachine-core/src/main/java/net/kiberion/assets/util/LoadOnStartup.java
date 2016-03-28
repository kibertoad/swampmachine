package net.kiberion.assets.util;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Apply this to classes, implementing AssetLoader, to be loaded in the beginning of game
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RUNTIME)
public @interface LoadOnStartup {}
