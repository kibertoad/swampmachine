package net.kiberion.swampmachine.factories.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import net.kiberion.swampmachine.factories.AfterSpawnListener;
import net.kiberion.swampmachine.factories.entities.DummyFactory.Dummy;

public class DummyAfterSpawnListener extends AfterSpawnListener<Dummy>{

    @Getter
    private final List<Dummy> dummies = new ArrayList<>();

    @Override
    public Class<Dummy> getSupportedClass() {
        return Dummy.class;
    }

    @Override
    protected void processEntityAfterSpawn(Dummy entity) {
        dummies.add(entity);
    }

    
}
