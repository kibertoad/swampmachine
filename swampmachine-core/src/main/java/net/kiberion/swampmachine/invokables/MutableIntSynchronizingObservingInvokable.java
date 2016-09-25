package net.kiberion.swampmachine.invokables;

import org.apache.commons.lang3.mutable.MutableInt;

/**
 * Sets value of a target variable to the value of observed entity
 * @author kibertoad
 *
 */
public class MutableIntSynchronizingObservingInvokable implements ObservingInvokable<Integer>{

    private final MutableInt target;
    
    public MutableIntSynchronizingObservingInvokable(MutableInt target) {
        this.target = target;
    }
    
    @Override
    public void invoke(Integer value) {
        target.setValue(value);
    }
    
}
