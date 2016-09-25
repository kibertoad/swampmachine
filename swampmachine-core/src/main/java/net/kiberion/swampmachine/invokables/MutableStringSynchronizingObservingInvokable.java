package net.kiberion.swampmachine.invokables;

import net.kiberion.swampmachine.utils.MutableString;

public class MutableStringSynchronizingObservingInvokable implements ObservingInvokable<String>{

    private final MutableString target;
    
    public MutableStringSynchronizingObservingInvokable(MutableString target) {
        this.target = target;
    }
    
    @Override
    public void invoke(String value) {
        target.setValue(value);
    }
    
}
