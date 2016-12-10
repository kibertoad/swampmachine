package net.kiberion.swampmachine.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ConstructableEntity;

@ConstructableEntity(id = "showSubView", constructorProperties = {"source", "subViewId", "hideOther"})
public class ShowSubViewEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = -1684468185933513501L;

    /**
     * Id of a subview to enable
     */
    @Getter
    private final String subViewId;
    
    /**
     * Whether other subview should be hidden after event is processed
     */
    @Getter
    private final boolean hideOther;
    
    public ShowSubViewEvent(Object source, String subViewId, boolean hideOther) {
        super(source);
        this.subViewId = subViewId;
        this.hideOther = hideOther;
    }

}
