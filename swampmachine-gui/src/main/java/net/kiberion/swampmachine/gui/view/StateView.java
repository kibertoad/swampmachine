package net.kiberion.swampmachine.gui.view;

import com.badlogic.gdx.scenes.scene2d.Stage;

public interface StateView {

    public void setStage(Stage stage);

    public void act(float delta);

    public void hide();

    public void show();

    public Stage getStage();
    
    /**
     * GUI elements are created and attached to stage inside this method 
     */
    public void initGUIElements ();

}
