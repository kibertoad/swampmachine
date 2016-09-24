package net.kiberion.swampmachine.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ConstructableEntity;

@ConstructableEntity(id = "changeState", constructorProperties = {"source", "stateId"})
public class ChangeStateEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = 1377301404419621755L;
    
    @Getter
    private final String stateCode;
    
    public ChangeStateEvent(Object source, String stateId) {
        super(source);
        this.stateCode = stateId;
    }
    

}
