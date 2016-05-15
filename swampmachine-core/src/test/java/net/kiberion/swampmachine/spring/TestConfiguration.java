package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.HeadlessAssetManager;
import com.badlogic.gdx.assets.loaders.HeadlessGl;
import com.badlogic.gdx.backends.headless.HeadlessFiles;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.UiManager;

@Configuration
@Import({ CommonLoaderConfiguration.class })
public class TestConfiguration extends CoreConfiguration {

    public TestConfiguration() {
        GameConfig.config.setPathToResourcesAsString("src/test/resources/");
        Gdx.files = new HeadlessFiles();
        Gdx.gl = new HeadlessGl();
    }

    @Override
    protected void initAssetManager() {
        AssetManager assetManager = new HeadlessAssetManager();
        UiManager.instance().setAssetManager(assetManager);
    }
}
