package net.kiberion.mvc.api;

import com.badlogic.gdx.scenes.scene2d.Stage;

public interface StateView {

    public void setStage(Stage stage);

    public void act(float delta);

    public void hide();

    public void show();

    public Stage getStage();
    
    public void initGUIElements ();

}
