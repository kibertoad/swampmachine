package net.kiberion.swampmachine.gui.view;

import java.util.List;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Stage;

public interface StateView {

    public Stage getMainStage();
    public void setMainStage(Stage stage);
    
    /**
     * Is only supposed to be used to set main stage for subviews of this view
     * @return
     */
    public Stage getOverlayStage();
    public void setOverlayStage(Stage stage);

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
    
    /**
     * Recursively extracts all stages from view and its subviews
     * @param targetSet
     */
    public void collectAllStages(Set<Stage> targetSet);
    

}
