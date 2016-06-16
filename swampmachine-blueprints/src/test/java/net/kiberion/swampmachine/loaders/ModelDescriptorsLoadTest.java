package net.kiberion.swampmachine.loaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import net.kiberion.swampmachine.assets.loaders.api.SyncLoader;
import net.kiberion.swampmachine.blueprints.common.entities.impl.CreatureModelInfo;
import net.kiberion.swampmachine.blueprints.common.loaders.CommonViewInfoLoader;
import net.kiberion.swampmachine.blueprints.common.loaders.CreatureRegistry;
import net.kiberion.swampmachine.blueprints.spring.ContextBasedTest;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;

public class ModelDescriptorsLoadTest extends ContextBasedTest {

    @Autowired
    private CommonViewInfoLoader viewLoader;

    @Autowired
    private LoaderHelper loaderHelper;

    @Autowired
    private CommonModelInfoRegistry modelInfoRegistry;
    
    @Autowired
    private CreatureRegistry creatureRegistry;

    @Autowired
    private ApplicationContext ctx;

    @Before
    public void init() {
        viewLoader.setImagesAreMandatory(false);
    }

    @Test
    public void testLoadingCreatures() {
        assertEquals(0, creatureRegistry.getCreatures().size());
        loaderHelper.startLoading();
        loaderHelper.finishLoading();
        assertEquals(1, creatureRegistry.getCreatures().size());
        CreatureModelInfo creature = creatureRegistry.getCreatures().get("dummycreature");
        assertNotNull(creature);
    }

    @Test
    public void testLoaderOrder() {
        assertEquals(0, loaderHelper.getAsyncAssetLoaders().size());
        assertEquals(3, loaderHelper.getSyncAssetLoaders().size());

        // Test loaders being properly sorted by loading priority
        int previousPriority = -1;
        for (SyncLoader loader : loaderHelper.getSyncAssetLoaders()) {
            assertTrue(loader.getPriority() > previousPriority);
            previousPriority = loader.getPriority();
        }
    }

}
