package net.kiberion.swampmachine.gui.composition.elements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;

import lombok.Getter;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;

public class ElementPrototypeRegistry implements InitializingBean {

    private final List<String> packagesToScan;

    @Getter
    private final Map<String, Class<?>> elementMap = new HashMap<>();

    public ElementPrototypeRegistry(String... packagesToScan) {
        Validate.notEmpty(packagesToScan);
        this.packagesToScan = Arrays.asList(packagesToScan);
    }
    
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
            Validate.notNull(prototypeInfo, "No ElementPrototype annotation on class "+clazz.getCanonicalName());
            elementMap.put(prototypeInfo.id(), clazz);
        }
    }

}
