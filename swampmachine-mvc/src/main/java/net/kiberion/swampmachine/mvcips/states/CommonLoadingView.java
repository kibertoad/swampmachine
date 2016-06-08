package net.kiberion.swampmachine.mvcips.states;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import net.kiberion.swampmachine.assets.UiManager;
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
}
