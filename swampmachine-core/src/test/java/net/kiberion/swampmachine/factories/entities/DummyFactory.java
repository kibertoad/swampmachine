package net.kiberion.swampmachine.factories.entities;

import java.util.List;

import net.kiberion.swampmachine.factories.EntityFactory;
import net.kiberion.swampmachine.factories.entities.DummyFactory.Dummy;
import net.kiberion.swampmachine.factories.params.CommonSpawnParams;
import net.kiberion.swampmachine.utils.common.InlineGList;

public class DummyFactory implements EntityFactory<Dummy, CommonSpawnParams> {

    public static final String FORCE_NULL_ID = "null";

    @Override
    public List<Class<?>> getSupportedClasses() {
        return new InlineGList<>(Dummy.class);
    }

    @Override
    public Dummy spawnEntity(CommonSpawnParams params) {
        if (FORCE_NULL_ID.equals(params.getId())) {
            return null;
        }
        return new Dummy();
    }

    public static class Dummy {
    }

}
