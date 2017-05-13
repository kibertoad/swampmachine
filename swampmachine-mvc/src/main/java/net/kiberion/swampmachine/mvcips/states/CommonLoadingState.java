package net.kiberion.swampmachine.mvcips.states;

import net.kiberion.swampmachine.loaders.LoaderHelper;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.gui.view.StateView;
import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.mvcips.states.annotations.LoadingState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.mvcips.states.util.StateManager;
import net.kiberion.swampmachine.mvcips.states.util.StateRegistry;

/**
 * @author kibertoad
 */
@LoadingState
@State (id = "loading")
public class CommonLoadingState extends GameState {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private CommonLoadingView view;

    @Autowired
    private GameConfig gameConfig;

    @Autowired
    private StateRegistry stateRegistry;

    @Autowired
    private StateManager stateManager;

    @Autowired
    private LoaderHelper loaderHelper;
    
    @Autowired
    private AssetManager assetManager;

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
    }

    @Override
    public void resize(int width, int height) {
        // super.resize(width, height);
        // stage.setViewport(width, height, true);
        getView().getMainStage().getViewport().setWorldWidth(width);
        getView().getMainStage().getViewport().setWorldHeight(height);
    }

    // phase 2
    public void initAllStates() {
        Validate.notNull(gameConfig, "Config is null.");
        String path = gameConfig.getPathToResources().resolve("imgpacked/packed.atlas").toString();
        log.info("searching for atlas: " + path);
        assetManager.load(path, TextureAtlas.class);
    }

    @Override
    public void show() {
        super.show();
        initAllStates();
        loaderHelper.startLoading();
    }

    @Override
    public void render(float delta) {
        if (assetManager.getProgress() < 1f) {

            // Gdx.app.log("debug", "Loadingprogress: "+
            // Float.toString(assetManager.getProgress() * 100));

            // label.setText(Float.toString(assetManager.getProgress() *
            // 100)+"%");
            getView().act(delta);
            getView().render();

            assetManager.update(100);
        } else {
            loaderHelper.finishLoading();
            stateManager.setState(stateRegistry.getStartingState());
        }
    }

    @Override
    public StateView getView() {
        return view;
    }

}
