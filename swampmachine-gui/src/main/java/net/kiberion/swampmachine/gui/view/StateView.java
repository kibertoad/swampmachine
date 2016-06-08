package net.kiberion.swampmachine.gui.view;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;

public interface StateView {

    public Stage getStage();

    public void render();
    public void act(float delta);

    public void hide();
    public void show();

    
    /**
     * GUI elements are created and attached to stage inside this method 
     */
    public void initGUIElements ();

    public void addSubView(StateView subView);
    public List<StateView> getSubViews();

}
