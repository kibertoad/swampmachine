package net.kiberion.swampmachine.blueprints.common.state;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.gui.view.AbstractStateView;
import net.kiberion.swampmachine.mvcips.states.util.StateRegistry;

public class CommonMainMenuView extends AbstractStateView<Object> {

    @Autowired
    private StateRegistry stateRegistry;

    // Do not autowire
    @Override
    public void setModel(Object model) {
        super.setModel(model);
    }
    
    @Override
    protected Collection<AbstractStateSubView<?>> getAutoEnabledSubViews() {
        return Collections.emptyList();
    }
}
