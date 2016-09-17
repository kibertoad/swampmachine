package net.kiberion.swampmachine.gui.composer.populators;

import java.lang.reflect.Field;
import java.util.Collection;

import org.reflections.ReflectionUtils;

import net.kiberion.swampmachine.gui.annotations.Bound;
import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.utils.predicates.HasAnnotationPredicate;

public class BoundElementsExtractor {

    @SuppressWarnings("unchecked")
    public Collection<Field> extractElementFields(CompositionConsumer consumer) {
        return ReflectionUtils.getAllFields(consumer.getClass(), new HasAnnotationPredicate(Bound.class));
    }

}
