package net.kiberion.blueprints.common.state;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.gui.elements.SwampTextButton;
import net.kiberion.gui.managers.GuiManager.PositionCode;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.mvcips.states.util.StateRegistry;
import net.kiberion.swampmachine.mvcips.view.StateViewBase;

public class CommonMainMenuView extends StateViewBase<Object> {

    @Autowired
    private StateRegistry stateRegistry;

    @Override
    public void initGUIElements() {
        super.initGUIElements();

        Table table = getGuiManager().addTable(PositionCode.CENTER);
        SwampTextButton<MetadataHolderBlock> button = new SwampTextButton<>("New game");
        button.addInvokable(getInvokablesFactory().createStateChangeInvokable(stateRegistry.getNewGameState().getId()));

        table.add(button);
    }

    // Do not autowire
    @Override
    public void setModel(Object model) {
        super.setModel(model);
    }
}
