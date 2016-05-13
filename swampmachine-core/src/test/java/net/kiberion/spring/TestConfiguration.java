package net.kiberion.spring;

import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.headless.HeadlessFiles;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.spring.CoreConfiguration;

@Configuration
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
