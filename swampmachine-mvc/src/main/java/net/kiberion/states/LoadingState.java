package net.kiberion.states;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.kiberion.assets.UiManager;
import net.kiberion.entities.common.api.Invokable;
import net.kiberion.entities.common.api.InvokableWithEntity;

/**
 * @author kibertoad
 */
@SuppressWarnings("rawtypes")
@Singleton
public class LoadingState extends GameState {

    private static final Logger log = LogManager.getLogger();

    @Inject
    private StateRegistry stateRegistry;
    
    protected GameState firstState;
    protected Invokable gameInceptionProvider;
    
    public Invokable getGameInceptionProvider() {
        return gameInceptionProvider;
    }

    public void setGameInceptionProvider(Invokable gameInceptionProvider) {
        this.gameInceptionProvider = gameInceptionProvider;
    }

    public void setFirstState(GameState firstState) {
        if (gameInceptionProvider != null) {
            gameInceptionProvider.invoke();
        }
		this.firstState = firstState;
	}

	protected GameApplication game;
    public void setGame(GameApplication game) {
		this.game = game;
	}

	protected AssetManager assetManager;
    public Stage stage;

    public Label label;

    public LoadingState() {
    	super("loading");
        stage = new Stage();

        label = new Label("Loading... Please wait.", UiManager.instance().skin());

        stage.addActor(label);
        label.setPosition(100, 100);

        assetManager = new AssetManager();
    }
    
    public LoadingState(String setID, GameApplication setGame, GameState setFirstState) {
        super(setID);

        stage = new Stage();
        game = setGame;

        label = new Label("Loading... Please wait.", UiManager.instance().skin());

        stage.addActor(label);
        label.setPosition(100, 100);

        assetManager = new AssetManager();
        firstState = setFirstState;
    }

    @Override
    public void resize(int width, int height) {
        //super.resize(width, height);
        //stage.setViewport(width, height, true);
        stage.getViewport().setWorldWidth(width);
        stage.getViewport().setWorldHeight(height);
    }

    //phase 2
    public void initAllStates() {
        //new File("data/imgpacked/").mkdirs();
        //new File("data/tilespacked/").mkdirs();

        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        UiManager.instance().setAssets(assetManager);

        log.info("searching for atlas: "+game.dataDirectory+"/imgpacked/packed.atlas");
        assetManager.load(game.dataDirectory+"/imgpacked/packed.atlas", TextureAtlas.class);
        //invokeOnAllStates ( (AbstractPyramideState pstate) -> pstate.queueAssets());
    }

    public void afterAssetsLoaded() {
        // Stage 3 -> actual initialization state, using assets that were loaded
        //invokeOnAllStates((GameState pstate) -> pstate.onAssetsLoaded());
    }

    @Override
    public void show() {
        super.show();

        initAllStates();
    }

    @Override
    public void hide() {
    }

    @Override
    public void render(float delta) {

        if (assetManager.getProgress() < 1f) {

            //Gdx.app.log("debug", "Loadingprogress: "+ Float.toString(assetManager.getProgress() * 100));
            

            //label.setText(Float.toString(assetManager.getProgress() * 100)+"%");
            stage.act(delta);
            stage.draw();

            assetManager.update(100);
        } else {
            //game.loadAllData();
            afterAssetsLoaded();

            game.setScreen(firstState);
        }
    }


    
    private void invokeOnAllStates(InvokableWithEntity<GameState> invokable) {
        for (Map.Entry<String, GameState> stateSet : stateRegistry.getStates().entrySet()) {
            GameState state = stateSet.getValue();
            invokable.invoke(state);
        }
    }
    
}
