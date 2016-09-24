package net.kiberion.swampmachine.gui.api;

public interface ParameterTransformer<I, O> {

    public Object transform(Object parameter);

    public String getParameterName();

}
