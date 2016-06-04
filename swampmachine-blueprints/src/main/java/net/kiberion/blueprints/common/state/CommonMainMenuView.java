package net.kiberion.blueprints.common.state;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.gui.elements.SwampTextButton;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.mvcips.view.StateViewBase;

public class CommonMainMenuView extends StateViewBase<Object>{

    private Table buttonTable;

    @Override
    public void initGUIElements() {
        super.initGUIElements();
        
        buttonTable = new Table();
        this.getStage().addActor(buttonTable);
        
        buttonTable.setPosition(100, 100);
        
        SwampTextButton<MetadataHolderBlock> button = new SwampTextButton<>("dummy button");
        buttonTable.add (button);
    }
    

    //Do not autowire
    @Override
    public void setModel(Object model) {
        super.setModel(model);
    }
}
