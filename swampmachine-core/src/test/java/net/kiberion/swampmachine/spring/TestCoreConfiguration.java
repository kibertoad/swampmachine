package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.HeadlessAssetManager;
import com.badlogic.gdx.assets.loaders.HeadlessGl;
import com.badlogic.gdx.backends.headless.HeadlessFiles;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.factories.entities.DummyAfterSpawnListener;
import net.kiberion.swampmachine.factories.entities.DummyFactory;

@Configuration
@Import({ CommonLoaderConfiguration.class, SwampmachineExtrasConfiguration.class })
public class TestCoreConfiguration extends CoreConfiguration {

    public TestCoreConfiguration() {
        GameConfig.config.setPathToResourcesAsString("src/test/resources/");
        Gdx.files = new HeadlessFiles();
        Gdx.gl = new HeadlessGl();
    }

    @Bean
    public DummyFactory dummyFactory() {
        return new DummyFactory();
    }

    @Bean
    public DummyAfterSpawnListener dummyAfterSpawnListener() {
        return new DummyAfterSpawnListener();
    }

    @Override
    protected AssetManager initAssetManager() {
        return new HeadlessAssetManager();
    }  
}
