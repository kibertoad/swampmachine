package net.kiberion.swampmachine.gui.api;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public interface ParameterTransformer<I, O> {

    public Object transform(Object parameter, Map<String, Object> context);

    public default Object transform(Object parameter) {
        return transform(parameter, Collections.emptyMap());
    }

    public Collection<String> getParameterNames();

}
