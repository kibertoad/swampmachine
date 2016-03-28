package net.kiberion.assets.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import net.kiberion.assets.loaders.AssetLoader;

public class LoaderScanner {

    public List<AssetLoader> scan(Object o) {
        Reflections reflections = new Reflections(o.getClass(), new FieldAnnotationsScanner());
        List<AssetLoader> result = new ArrayList<>();

        for (Field field : reflections.getFieldsAnnotatedWith(LoadOnStartup.class)) {
            try {
                Object fieldValue = field.get(o);

                if (fieldValue == null) {
                    throw new IllegalStateException(field.getName() + " is null.");
                }
                
                if (!(fieldValue instanceof AssetLoader)) {
                    throw new IllegalStateException(fieldValue.getClass()
                            + " does not implement AssetLoader interface but has Loader annotation.");
                }


                result.add((AssetLoader) field.get(o));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }

        return result;
    }

}
