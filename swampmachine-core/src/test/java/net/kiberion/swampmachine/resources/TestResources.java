package net.kiberion.swampmachine.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesDelta;
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
            delta.put("fake", 1);
            fail();
        } catch (IllegalArgumentException e) {
        }

        delta.put("dummyresource", 1);                
    }
}
