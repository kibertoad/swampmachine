package net.kiberion.swampmachine.processors;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.api.Invokable;
import net.kiberion.swampmachine.entities.common.api.RealtimeUpdatable;

/**
 * Processor that is expected to invoke specific logic periodically
 * @author kibertoad
 *
 */
public abstract class TimedProcessor implements RealtimeUpdatable, Invokable{

    @Setter
    @Getter
    private float delayBetweenActs = 0.04f;
    
    
    private float timeTillInvokation = delayBetweenActs;

    @Override
    public void update(float delta) {
        timeTillInvokation -= delta;

        if (timeTillInvokation <= 0) {
            invoke();
            timeTillInvokation += getDelayBetweenActs();
        }

    }    
    
}
