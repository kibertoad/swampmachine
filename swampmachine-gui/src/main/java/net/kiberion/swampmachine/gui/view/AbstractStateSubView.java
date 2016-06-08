package net.kiberion.swampmachine.gui.view;

public class AbstractStateSubView<T> extends AbstractStateView<T>{

    @Override
    protected void initStage() {
        //Do nothing, subViews get bound to their parent's stage.
    }
    
}
