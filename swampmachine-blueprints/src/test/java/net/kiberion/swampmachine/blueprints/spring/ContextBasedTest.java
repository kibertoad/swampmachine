package net.kiberion.swampmachine.blueprints.spring;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.kiberion.swampmachine.assets.loaders.api.Loader;
import net.kiberion.swampmachine.assets.loaders.util.LoaderSpringExtractor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public abstract class ContextBasedTest implements ApplicationContextAware {

    protected ApplicationContext applicationContext;

    protected void loadAssets() {
        List<Loader> assetLoaders = LoaderSpringExtractor
                .extractSortedStartupAssetLoadersFromContext(applicationContext);
        for (Loader loader : assetLoaders) {
            loader.load();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
