package net.kiberion.states;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.entities.common.api.DeltaUpdatable;
import net.kiberion.mvc.StateView;

public class GameState<V extends StateView> implements Screen{

    @Getter
    private final String key;
    
    @Getter
    @Setter
    private Stage stage;

    @Getter
    @Setter
    private Stage overlayStage;
    
    
    @Getter
    private V view;
    
    public List<DeltaUpdatable> entitiesForUpdate = new ArrayList<>();
    private List<StateView> subViews = new ArrayList<>();

    public GameState(String key) {
        super();
        this.key = key;
        this.stage = new Stage();
        
    }
    
    public void addSubView (StateView view) {
        view.setStage(stage);
    }

    public void postInjection() {
		if (view != null) {
			view.postInjection();
		}
    	
    	for (StateView subView : subViews) {
    		subView.postInjection();
    	}
    }
    
    protected void setView (V view) {
    	this.view = view;
    	view.setStage(getStage());
    }

	@Override
	public void show() {
		view.show();
		
	}

	@Override
	public void render(float delta) {
        for (DeltaUpdatable entity : entitiesForUpdate) {
            entity.update(delta);
        }
        
        if (getStage() != null) {
            getStage().act(delta);
            getStage().draw();
        }

        if (getOverlayStage() != null) {
            getOverlayStage().act(delta);
            getOverlayStage().draw();
        }

        for (StateView view : subViews) {
            view.act(delta);
        }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
        view.hide();
        
        for (StateView subView : subViews) {
            subView.hide();
        }
	}

	@Override
	public void dispose() {
	}
	
}
