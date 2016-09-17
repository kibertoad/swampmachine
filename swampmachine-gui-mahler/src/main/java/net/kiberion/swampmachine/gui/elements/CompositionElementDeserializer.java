package net.kiberion.swampmachine.gui.elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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

    private static final List<String> supportedTextProperties = new InlineGList<>("id", "type");
    private static final Set<String> mandatoryTextProperties = SetUtils.buildSet("id", "type");

    @Override
    public CompositionElement deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        CompositionElement result = new CompositionElement();

        Iterator<Entry<String, JsonNode>> s = node.fields();
        while (s.hasNext()) {
            Entry<String, JsonNode> entry = s.next();
        }

        for (String supportedProperty : supportedTextProperties) {
            JsonNode value = node.get(supportedProperty);
            if (value == null && mandatoryTextProperties.contains(supportedProperty)) {
                throw new IllegalArgumentException("Missing mandatory property: " + supportedProperty);
            }
            if (value != null) {
                PropertyAccessor beanAccessor = PropertyAccessorFactory.forDirectFieldAccess(result);
                beanAccessor.setPropertyValue(supportedProperty, value.asText());
            }
        }

        return result;
    }

}
