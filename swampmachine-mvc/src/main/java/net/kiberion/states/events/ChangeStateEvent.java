package net.kiberion.states.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class ChangeStateEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = 1377301404419621755L;
    @Getter
    private final String stateCode;
    
    public ChangeStateEvent(Object source, String stateCode) {
        super(source);
        this.stateCode = stateCode;
    }
    

}
