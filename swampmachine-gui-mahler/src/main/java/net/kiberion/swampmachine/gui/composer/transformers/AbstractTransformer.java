package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.kiberion.swampmachine.gui.api.ParameterTransformer;

public abstract class AbstractTransformer<I, O> implements ParameterTransformer<I, O> {

    @SuppressWarnings("unchecked")
    @Override
    public Object transform(Object parameter, Map<String, Object> context) {
        if (parameter instanceof Collection) {
            return transformMultiple((Collection<I>) parameter, context);
        }

        if (parameter instanceof Object[]) {
            return transformMultiple((I[]) parameter, context);
        }

        return transformSingle((I) parameter, context);
    }
    
    

    protected abstract O transformSingle(I parameter, Map<String, Object> context);

    public List<O> transformMultiple(Collection<I> parameters, Map<String, Object> context) {
        List<O> result = new ArrayList<>();

        for (I parameter : parameters) {
            result.add(transformSingle(parameter, context));
        }

        return result;
    }

    public List<O> transformMultiple(I[] parameters, Map<String, Object> context) {
        return transformMultiple(Arrays.asList(parameters), context);
    }

}
