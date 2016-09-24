package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.kiberion.swampmachine.gui.api.ParameterTransformer;

public abstract class AbstractTransformer<I, O> implements ParameterTransformer<I, O> {

    @SuppressWarnings("unchecked")
    @Override
    public Object transform(Object parameter) {
        if (parameter instanceof Collection) {
            return transformMultiple((Collection<I>) parameter);
        }

        if (parameter instanceof Object[]) {
            return transformMultiple((I[]) parameter);
        }

        return transformSingle((I) parameter);
    }

    protected abstract O transformSingle(I parameter);

    public List<O> transformMultiple(Collection<I> parameters) {
        List<O> result = new ArrayList<>();

        for (I parameter : parameters) {
            result.add(transformSingle(parameter));
        }

        return result;
    }

    public List<O> transformMultiple(I[] parameters) {
        return transformMultiple(Arrays.asList(parameters));
    }

}
