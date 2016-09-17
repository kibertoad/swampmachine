package net.kiberion.swampmachine.utils.predicates;

import static com.google.common.base.Preconditions.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

public class HasAnnotationPredicate implements Predicate<Field>{

    private final Class<? extends Annotation> annotation;

    public HasAnnotationPredicate(Class<? extends Annotation> annotation) {
        this.annotation = checkNotNull(annotation);
    }

    @Override
    public boolean apply(Field input) {
        return input.isAnnotationPresent(annotation);
    }

    @Override
    public int hashCode() {
        return annotation.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof HasAnnotationPredicate) {
            HasAnnotationPredicate that = (HasAnnotationPredicate) obj;
            return annotation == that.annotation;
        }
        return false;
    }
    
}
