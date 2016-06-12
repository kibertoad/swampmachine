package net.kiberion.swampmachine.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Apply this to registries that should be converted to immutable maps after initialization is completed
 * {@see ImmutableRegistryPreparer}
 * @author kibertoad
 *
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface ImmutableRegistry {}
