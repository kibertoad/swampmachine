package net.kiberion.swampmachine.gui.invokables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.subscription.ObservableObject;

public class SetObservableObjectInvokable<T> implements Invokable{

    private static final Logger log = LogManager.getLogger();
    
    private final ObservableObject<T> observableToSet;
    private final T valueToSetTo;
    
    public SetObservableObjectInvokable(ObservableObject<T> observableToSet, T valueToSetTo) {
        super();
        this.observableToSet = observableToSet;
        this.valueToSetTo = valueToSetTo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T invoke() {
        observableToSet.setValue(valueToSetTo);
        log.info("Selected value: "+valueToSetTo);
        return valueToSetTo;
    }

}
