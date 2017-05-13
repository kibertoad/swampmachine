package net.kiberion.swampmachine.factories;

import static org.junit.Assert.*;

import net.kiberion.swampmachine.BasicContextBasedTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import net.kiberion.swampmachine.factories.entities.DummyAfterSpawnListener;
import net.kiberion.swampmachine.factories.entities.DummyFactory;
import net.kiberion.swampmachine.factories.events.SpawnEntityEvent;
import net.kiberion.swampmachine.factories.params.CommonSpawnParams;

public class MetaFactoryTest extends BasicContextBasedTest{

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private DummyAfterSpawnListener afterSpawnListener;

    @Test
    public void testBuildEntity() {
        assertEquals(0, afterSpawnListener.getDummies().size());
        eventPublisher.publishEvent(new SpawnEntityEvent(this, DummyFactory.Dummy.class, new CommonSpawnParams()));
        assertEquals(1, afterSpawnListener.getDummies().size());
    }

    @Test
    public void testErrors() {
        try {
            eventPublisher.publishEvent(new SpawnEntityEvent(this, null, new CommonSpawnParams()));
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().startsWith("EntityClass should be specified"));
        }

        try {
            eventPublisher.publishEvent(new SpawnEntityEvent(this, Object.class, new CommonSpawnParams()));
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().startsWith("No factory"));
        }

        try {
            // "null" id will force DummyFactory to produce null
            eventPublisher.publishEvent(new SpawnEntityEvent(this, DummyFactory.Dummy.class,
                    new CommonSpawnParams(DummyFactory.FORCE_NULL_ID, 0, 0)));
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().startsWith("Null was the result of entity spawn"));
        }
    }

}
