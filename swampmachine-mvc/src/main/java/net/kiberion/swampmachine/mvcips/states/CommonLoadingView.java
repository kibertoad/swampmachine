package net.kiberion.swampmachine.mvcips.states;

import java.util.Collection;
import java.util.Collections;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.gui.view.AbstractStateView;

public class CommonLoadingView extends AbstractStateView<CommonLoadingModel> {

    private Label label;

    @Override
    public void initGUIElements() {
        super.initGUIElements();
        
        label = new Label("Loading... Please wait.", UiManager.instance().getDefaultSkin());
        label.setVisible(true);
        getMainStage().addActor(label);
        label.setPosition(100, 100);
    }
    
    @Override
    protected Collection<AbstractStateSubView<?>> getAutoEnabledSubViews() {
        return Collections.emptyList();
    }
}
