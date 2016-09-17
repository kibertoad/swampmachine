package net.kiberion.swampmachine.utils.predicates;

import static com.google.common.base.Preconditions.*;

import java.lang.reflect.Field;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

public class AssignableFromPredicate implements Predicate<Field> {
    private final Class<?> clazz;

    public AssignableFromPredicate(Class<?> clazz) {
        this.clazz = checkNotNull(clazz);
    }

    @Override
    public boolean apply(Field input) {
        return clazz.isAssignableFrom(input.getType());
    }

    @Override
    public int hashCode() {
        return clazz.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof AssignableFromPredicate) {
            AssignableFromPredicate that = (AssignableFromPredicate) obj;
            return clazz == that.clazz;
        }
        return false;
    }
}
