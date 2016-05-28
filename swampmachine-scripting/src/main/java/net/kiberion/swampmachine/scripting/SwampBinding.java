package net.kiberion.swampmachine.scripting;

public interface SwampBinding {

    public Object getVariable(String name);
    public void setVariable(String name, Object value);
    
}
