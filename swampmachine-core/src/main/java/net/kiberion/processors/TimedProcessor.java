package net.kiberion.processors;

import net.kiberion.entities.common.api.DeltaUpdatable;
import net.kiberion.entities.common.api.Invokable;

public abstract class TimedProcessor implements DeltaUpdatable, Invokable{

    private float delayBetweenActs = 0.04f;
    
    
    private final float getDelayBetweenActs() {
        return delayBetweenActs;
    }

    public final void setDelayBetweenActs(float delayBetweenAIActs) {
        this.delayBetweenActs = delayBetweenAIActs;
    }

    private float timeTillActs = delayBetweenActs;

    @Override
    public void update(float delta) {
        timeTillActs -= delta;

        if (timeTillActs <= 0) {
            invoke();
            timeTillActs += getDelayBetweenActs();
        }

    }    
    
}
