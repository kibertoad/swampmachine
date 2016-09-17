package net.kiberion.swampmachine.gui.elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;

public class ElementPrototypeRegistry implements InitializingBean {

    @Setter
    private List<String> packagesToScan;

    @Getter
    private final Map<String, Class<?>> elementMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        for (String packageName : packagesToScan) {
            scanPackage (packageName);
        }
    }

    protected void scanPackage(String packageName) {
        Reflections reflections = new Reflections(packageName);

        Set<Class<?>> elementPrototypes = reflections.getTypesAnnotatedWith(ElementPrototype.class);

        for (Class<?> clazz : elementPrototypes) {
            ElementPrototype prototypeInfo = clazz.getAnnotation(ElementPrototype.class);
            elementMap.put(prototypeInfo.id(), clazz);
        }
    }

}
