package net.kiberion.swampmachine.gui.api;

public interface ParameterTransformer {

    public <I, O> O transform (I parameter);
    
    public Class<?> getInputClass();
    public Class<?> getOutputClass();
    
    public String getParameterName();
    
}
