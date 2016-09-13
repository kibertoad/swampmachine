package net.kiberion.swampmachine.gui.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.composer.Composition;

public class SpringCompositionConsumerExtractor {

    private SpringCompositionConsumerExtractor() {
    }

    public List<Composition> extractCompositionsForConsumer(Map<String, Composition> compositionMap,
            CompositionConsumer consumer) {
        List<Composition> result = new ArrayList<>();
        BoundCompositions compositions = consumer.getClass().getAnnotation(BoundCompositions.class);
        Validate.notNull(compositions, consumer.getClass()
                + " does not have BoundCompositions annotation but implements CompositionConsumer.");
        for (String compositionId : compositions.compositions()) {
            result.add(compositionMap.get(compositionId));
        }
        return result;
    }

}
