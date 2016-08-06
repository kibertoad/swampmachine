package net.kiberion.tiled;

import java.util.List;

import org.springframework.test.context.ContextConfiguration;

import net.kiberion.swampmachine.assets.loaders.api.Loader;
import net.kiberion.swampmachine.assets.loaders.util.LoaderSpringExtractor;
import net.kiberion.swampmachine.spring.CoreContextBasedTest;
import net.kiberion.tiled.spring.TiledTestConfiguration;

@ContextConfiguration(classes = {TiledTestConfiguration.class})
public abstract class MapContextBasedTest extends CoreContextBasedTest{

    protected void loadAssets() {
        List<Loader> assetLoaders = LoaderSpringExtractor
                .extractSortedStartupAssetLoadersFromContext(applicationContext);
        for (Loader loader : assetLoaders) {
            loader.load();
        }
    }
    
    
}
