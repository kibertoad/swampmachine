package net.kiberion.tiled.factories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.maps.objects.TextureMapObject;

import net.kiberion.factories.EntityFactory;
import net.kiberion.tiled.factories.impl.params.TextureMapObjectSpawnParams;
import net.kiberion.tiled.managers.MapObjectManager;
import net.kiberion.utils.InlineGList;

public class TextureMapObjectFactory implements EntityFactory<TextureMapObject, TextureMapObjectSpawnParams> {

    @Autowired
    private MapObjectManager mapObjectManager;

    @Override
    public List<Class<?>> getSupportedClasses() {
        return new InlineGList<>(TextureMapObject.class);
    }

    @Override
    public TextureMapObject spawnEntity(TextureMapObjectSpawnParams params) {
        return mapObjectManager.addMapObject(params.getModelEntity(), params.getPosition(), params.getId());
    }

}
