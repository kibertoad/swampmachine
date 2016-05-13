package net.kiberion.swampmachine.assets.util;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Apply this to classes, implementing AssetLoader, to be loaded even before the LoadingState kicks in
 * To be automatically loaded from @LoadingState classes
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RUNTIME)
public @interface LoadBeforeStartup {}
