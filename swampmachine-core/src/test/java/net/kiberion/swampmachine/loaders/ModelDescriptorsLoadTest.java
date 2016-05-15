package net.kiberion.swampmachine.loaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.spring.ContextBasedTest;

public class ModelDescriptorsLoadTest extends ContextBasedTest {

    @Autowired
    private CommonViewInfoLoader viewLoader;

    @Autowired
    private LoaderHelper loaderHelper;

    @Autowired
    private CommonModelInfoRegistry modelInfoRegistry;

    @Autowired
    private ApplicationContext ctx;

    @Before
    public void init() {
        viewLoader.setImagesAreMandatory(false);
    }

    @Test
    public void testLoadingCreatures() {
        assertEquals(0, modelInfoRegistry.getCreatures().size());
        loaderHelper.init(ctx);
        loaderHelper.startLoading();
        loaderHelper.finishLoading();
        assertEquals(1, modelInfoRegistry.getCreatures().size());
        CreatureModelInfo creature = modelInfoRegistry.getCreatures().get("dummycreature");
        assertNotNull(creature);
    }

    public void testLoaderOrder() {
        loaderHelper.init(ctx);
        assertEquals(0, loaderHelper.getAsyncAssetLoaders().size());
        assertEquals(2, loaderHelper.getSyncAssetLoaders().size());

        // Test loaders being properly sorted by loading priority
        int previousPriority = -1;
        for (SyncAssetLoader loader : loaderHelper.getSyncAssetLoaders()) {
            assertTrue(loader.getPriority() > previousPriority);
            previousPriority = loader.getPriority();
        }
    }

}
