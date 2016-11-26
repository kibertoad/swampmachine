package net.kiberion.swampmachine.gui.view;

import java.util.List;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Stage;

import net.kiberion.swampmachine.api.composition.CompositionConsumer;
import net.kiberion.swampmachine.api.scripting.SwampBinding;

public interface StateView extends CompositionConsumer {

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
    
    /**
     * Recursively extracts all subviews from the view. Also includes the view itself
     * @param targetSet
     */
    public void collectAllViews(Set<StateView> targetSet);
    public void setBinding(SwampBinding binding);

}
