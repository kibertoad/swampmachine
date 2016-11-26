package net.kiberion.swampmachine.gui.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.swampmachine.api.composition.CompositionConsumer;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.composer.Composition;

public class SpringBoundCompositionsExtractor {

    private static final Logger log = LogManager.getLogger();
    
    public List<Composition> extractCompositionsForConsumer(Map<String, Composition> compositionMap,
            CompositionConsumer consumer) {
        List<Composition> result = new ArrayList<>();
        BoundCompositions compositions = consumer.getClass().getAnnotation(BoundCompositions.class);
        log.info("Compositions: " + compositionMap.keySet());
        Validate.notNull(compositions, consumer.getClass()
                + " does not have BoundCompositions annotation but implements CompositionConsumer.");
        for (String compositionId : compositions.compositions()) {
            Composition composition = compositionMap.get(compositionId);
            Validate.notNull(composition, "Unknown composition: "+compositionId);
            result.add(composition);
        }
        return result;
    }
    
}

