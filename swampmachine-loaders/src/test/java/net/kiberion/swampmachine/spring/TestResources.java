package net.kiberion.swampmachine.spring;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Set;

import net.kiberion.swampmachine.utils.ImmutableRegistryPreparer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesDelta;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesStorage;
import net.kiberion.swampmachine.registries.ResourceRegistry;

public class TestResources extends CoreContextBasedTest {

    @Autowired
    private ResourceRegistry resourceRegistry;

    @Test
    public void testResources() {
        assertEquals(1, resourceRegistry.getResources().size());

        ResourcesDelta delta = resourceRegistry.getNewResourcesDeltaInstance();

        try {
            delta.add("fake", 1);
            fail();
        } catch (IllegalArgumentException e) {
            //expected
        }

        assertNull(delta.getMutableValue("dummyresource"));
        delta.add("dummyresource", 1);
        assertEquals(1, delta.getMutableValue("dummyresource").longValue());
    }

    @Test
    public void testDelta() {
        Set<String> supportedResources = ImmutableSet.of("dummy", "dummy2");
        ResourcesStorage resources = new ResourcesStorage(supportedResources);

        assertEquals(0, resources.getValue("dummy"));
        assertEquals(0, resources.getValue("dummy2"));

        ResourcesDelta delta = new ResourcesDelta(supportedResources);
        delta.add("dummy", 1);
        delta.add("dummy2", -1);

        resources.applyDelta(delta);
        assertEquals(1, resources.getValue("dummy"));
        assertEquals(-1, resources.getValue("dummy2"));
    }

    @Test
    public void testImmutableMapPreparer() {
        assertEquals(HashMap.class, resourceRegistry.getResources().getClass());
        assertEquals(1, resourceRegistry.getResources().size());
        ImmutableRegistryPreparer.invoke(applicationContext);
        assertTrue(resourceRegistry.getResources() instanceof ImmutableMap);
        assertEquals(1, resourceRegistry.getResources().size());
    }
}
