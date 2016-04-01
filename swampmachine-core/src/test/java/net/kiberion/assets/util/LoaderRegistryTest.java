package net.kiberion.assets.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import net.kiberion.assets.loaders.api.AssetLoader;
import net.kiberion.assets.registries.LoaderRegistry;
import net.kiberion.blueprint.common.loaders.CommonModelInfoLoader;
import net.kiberion.blueprint.common.loaders.CommonViewInfoLoader;
import net.kiberion.spring.ContextBasedTest;

public class LoaderRegistryTest extends ContextBasedTest{

    @Autowired
    private CommonViewInfoLoader viewLoader;

    @Autowired
    private CommonModelInfoLoader modelLoader;

    
    @Autowired
    private LoaderRegistry loaderRegistry;
    
    @Autowired
    private ApplicationContext ctx;
    
    @Before
    public void assertInit () {
        Assert.assertNotNull (loaderRegistry);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testRegistry () {
        List <AssetLoader> loaders = new ArrayList<AssetLoader>((Collection<? extends AssetLoader>) ctx.getBeansWithAnnotation(LoadOnStartup.class).values());
        assertEquals (2, loaders.size());
        
        loaderRegistry.registerLoaders(loaders);;
        
        int previousPriority = -1;
        for (AssetLoader loader : loaderRegistry.getRegisteredLoaders()) {
            assertTrue (loader.getPriority() > previousPriority);
            previousPriority = loader.getPriority();
        }
    }
    
}
