package net.kiberion.swampmachine.gui.elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import net.kiberion.swampmachine.utils.SetUtils;
import net.kiberion.utils.InlineGList;

public class CompositionElementDeserializer extends JsonDeserializer<CompositionElement> {

    private static final Logger log = LogManager.getLogger();

    private static final List<String> supportedTextProperties = new InlineGList<>("id", "type");
    private static final Set<String> mandatoryTextProperties = SetUtils.buildSet("id", "type");

    @Override
    public CompositionElement deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        CompositionElement result = new CompositionElement();
        PropertyAccessor beanAccessor = PropertyAccessorFactory.forDirectFieldAccess(result);

        for (Iterator<Entry<String, JsonNode>> iter = node.fields(); iter.hasNext();) {
            Entry<String, JsonNode> subNode = iter.next();
            if (supportedTextProperties.contains(subNode.getKey())) {
                beanAccessor.setPropertyValue(subNode.getKey(), subNode.getValue().asText());
            } else {
                result.getProperties().put(subNode.getKey(), subNode.getValue().asText());
            }
        }

        /*
         * JsonNode value = node.get(supportedProperty); if (value == null &&
         * mandatoryTextProperties.contains(supportedProperty)) { throw new
         * IllegalArgumentException("Missing mandatory property: " +
         * supportedProperty); } if (value != null) { PropertyAccessor
         * beanAccessor = PropertyAccessorFactory.forDirectFieldAccess(result);
         * beanAccessor.setPropertyValue(supportedProperty, value.asText()); }
         */

        return result;
    }

}
