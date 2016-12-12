package net.kiberion.swampmachine.gui.view;

import java.util.Collection;
import java.util.Collections;

public class AbstractStateSubView<T> extends AbstractStateView<T>{

    @Override
    protected void initStage() {
        //Do nothing, subViews get bound to their parent's stage.
    }
    
    @Override
    protected Collection<AbstractStateSubView<?>> getAutoEnabledSubViews() {
        return Collections.emptyList();
    }
    
}
