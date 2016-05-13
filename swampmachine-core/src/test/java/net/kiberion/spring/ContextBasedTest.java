package net.kiberion.spring;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.kiberion.swampmachine.assets.loaders.api.AssetLoader;
import net.kiberion.swampmachine.assets.loaders.util.AssetLoaderSpringExtractor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public abstract class ContextBasedTest implements ApplicationContextAware {

    protected ApplicationContext applicationContext;

    protected void loadAssets() {
        List<AssetLoader> assetLoaders = AssetLoaderSpringExtractor
                .extractSortedStartupAssetLoadersFromContext(applicationContext);
        for (AssetLoader loader : assetLoaders) {
            loader.load();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
