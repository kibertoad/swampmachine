package net.kiberion.swampmachine.subscription;

import static org.junit.Assert.*;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;

import net.kiberion.swampmachine.invokables.MutableIntSynchronizingObservingInvokable;
import net.kiberion.swampmachine.invokables.ObservingInvokable;

public class SubscriptionTest {

    @Test
    public void testObservableInt() {
        final MutableInt storedValue = new MutableInt(0);
        ObservableInt observable = new ObservableInt();

        ObservingInvokable<Integer> invokable = new MutableIntSynchronizingObservingInvokable(storedValue);

        observable.addObserver(invokable);

        assertEquals(0, storedValue.intValue());
        observable.decrement();
        assertEquals(-1, storedValue.intValue());
        observable.increment();
        assertEquals(0, storedValue.intValue());
        observable.setValue(10);
        assertEquals(10, storedValue.intValue());
    }

}
