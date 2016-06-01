package net.kiberion.swampmachine.resources;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableSet;

import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesDelta;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesStorage;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.spring.ContextBasedTest;

public class TestResources extends ContextBasedTest {

    @Autowired
    private CommonModelInfoRegistry modelRegistry;

    @Test
    public void testResources() {
        assertEquals(0, modelRegistry.getResources().size());
        loadAssets();
        assertEquals(1, modelRegistry.getResources().size());

        ResourcesDelta delta = modelRegistry.getNewResourcesDeltaInstance();

        try {
            delta.add("fake", 1);
            fail();
        } catch (IllegalArgumentException e) {
        }

        delta.add("dummyresource", 1);
        assertEquals (1, delta.getMutableValue("dummyresource").longValue());
    }
    
    public void testDelta() {
        Set<String> supportedResources = ImmutableSet.of ("dummy", "dummy2");
        ResourcesStorage resources = new ResourcesStorage(supportedResources);
        
        assertEquals (0, resources.getValue("dummy"));
        assertEquals (0, resources.getValue("dummy2"));
        
        ResourcesDelta delta = new ResourcesDelta(supportedResources);
        delta.add("dummy", 1);
        delta.add("dummy2", -1);
        
        resources.applyDelta(delta);

        assertEquals (1, resources.getValue("dummy"));
        assertEquals (-1, resources.getValue("dummy2"));
    }
}

