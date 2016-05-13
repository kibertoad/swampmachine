package net.kiberion.swampmachine.mvcips.states;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.assets.loaders.api.AssetLoader;
import net.kiberion.swampmachine.assets.loaders.api.AsyncAssetLoader;
import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;
import net.kiberion.swampmachine.assets.loaders.util.AssetLoaderSpringExtractor;
import net.kiberion.swampmachine.entities.common.api.Invokable;
import net.kiberion.swampmachine.mvcips.states.annotations.LoadingState;
import net.kiberion.swampmachine.mvcips.states.util.StateManager;
import net.kiberion.swampmachine.mvcips.states.util.StateRegistry;
import net.kiberion.swampmachine.mvcips.view.StateView;
import net.kiberion.swampmachine.mvcips.view.StateViewBase;

/**
 * @author kibertoad
 */
@LoadingState
public class CommonLoadingState extends GameState implements InitializingBean, ApplicationContextAware {

    private static final Logger log = LogManager.getLogger();

    private StateView view = new StateViewBase<Container<Actor>>();

    @Autowired
    private GameConfig gameConfig;

    @Autowired
    private StateRegistry stateRegistry;

    @Autowired
    private StateManager stateManager;

    private ApplicationContext ctx;

    private final List<SyncAssetLoader> syncAssetLoaders = new ArrayList<>();
    private final List<AsyncAssetLoader> asyncAssetLoaders = new ArrayList<>();
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

    public Label label;

    public CommonLoadingState() {
        this("loading");
    }

    public CommonLoadingState(String setID) {
        super(setID);

        label = new Label("Loading... Please wait.", UiManager.instance().skin());
        label.setVisible(true);

        getStage().addActor(label);
        label.setPosition(100, 100);
    }

    @Override
    public void resize(int width, int height) {
        // super.resize(width, height);
        // stage.setViewport(width, height, true);
        getStage().getViewport().setWorldWidth(width);
        getStage().getViewport().setWorldHeight(height);
    }

    // phase 2
    public void initAllStates() {
        Validate.notNull(gameConfig, "Config is null.");

        String path = gameConfig.getPathToResources().resolve("imgpacked/packed.atlas").toString();
        log.info("searching for atlas: " + path);
        UiManager.instance().getAssetManager().load(path, TextureAtlas.class);
    }

    @Override
    public void show() {
        super.show();

        initAllStates();

        log.info("Start loading from Sync asset loaders.");
        for (SyncAssetLoader loader : syncAssetLoaders) {
            loader.load();
        }
        log.info("Done loading from Sync asset loaders.");

        log.info("Start queueing loading from Async asset loaders.");
        for (AsyncAssetLoader loader : asyncAssetLoaders) {
            loader.startAsyncLoading();
        }
        log.info("Done queueing loading from Async asset loaders.");
    }

    @Override
    public void hide() {
    }

    @Override
    public void render(float delta) {

        if (UiManager.instance().getAssetManager().getProgress() < 1f) {

            // Gdx.app.log("debug", "Loadingprogress: "+
            // Float.toString(assetManager.getProgress() * 100));

            // label.setText(Float.toString(assetManager.getProgress() *
            // 100)+"%");
            getStage().act(delta);
            getStage().draw();

            UiManager.instance().getAssetManager().update(100);
        } else {
            
            log.info("Start finishing loading from Async asset loaders.");
            for (AsyncAssetLoader loader : asyncAssetLoaders) {
                loader.finishAsyncLoading();
            }
            log.info("Done finishing loading from Async asset loaders.");

            stateManager.setState(stateRegistry.getStartingState());
        }
    }

    @Override
    public StateView getView() {
        return view;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<AssetLoader> assetLoaders = AssetLoaderSpringExtractor.extractSortedStartupAssetLoadersFromContext(ctx);

        for (AssetLoader assetLoader : assetLoaders) {
            if (assetLoader instanceof AsyncAssetLoader) {
                asyncAssetLoaders.add((AsyncAssetLoader) assetLoader);
            }
            if (assetLoader instanceof SyncAssetLoader) {
                syncAssetLoaders.add((SyncAssetLoader) assetLoader);
            }
        }

    }

}
