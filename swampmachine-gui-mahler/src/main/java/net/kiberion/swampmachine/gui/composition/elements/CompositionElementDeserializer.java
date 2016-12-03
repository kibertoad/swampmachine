package net.kiberion.swampmachine.gui.composition.elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.utils.SetUtils;
import net.kiberion.swampmachine.utils.common.InlineGList;

public class CompositionElementDeserializer extends JsonDeserializer<CompositionElement> {

    private static final Logger log = LogManager.getLogger();

    // will be set via setters
    private static final List<String> supportedTextProperties = new InlineGList<>("id", "type");
    private static final List<String> supportedIntProperties = new InlineGList<>("zIndex");

    private static final List<String> consumedProperties = new InlineGList<>("position", "image", "text", "labelValue",
            "labelText", "buttonSource");
    private static final List<String> consumedMapProperties = new InlineGList<>("onClickEvent", "plus", "minus",
            "button");
    private static final List<String> consumedIntProperties = new InlineGList<>();
    private static final List<String> consumedListProperties = new InlineGList<>("buttons");

    private static final Set<String> mandatoryTextProperties = SetUtils.buildSet("id", "type");

    private void setPosition(CompositionElement result, Entry<String, JsonNode> sourceNode) {
        if (sourceNode.getValue().size() == 2) {
            result.setPosition(
                    new CommonPosition(sourceNode.getValue().get(0).asInt(), sourceNode.getValue().get(1).asInt()));
        }
    }

    @Override
    public CompositionElement deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = jp.getCodec().readTree(jp);
        CompositionElement result = new CompositionElement();
        PropertyAccessor beanAccessor = PropertyAccessorFactory.forDirectFieldAccess(result);

        for (Iterator<Entry<String, JsonNode>> iter = node.fields(); iter.hasNext();) {
            Entry<String, JsonNode> subNode = iter.next();

            if (consumedListProperties.contains(subNode.getKey())) {
                result.getProperties().put(subNode.getKey(), mapper.treeToValue(subNode.getValue(), List.class));
            } else if (consumedMapProperties.contains(subNode.getKey())) {
                result.getProperties().put(subNode.getKey(), mapper.treeToValue(subNode.getValue(), Map.class));
            } else if (consumedIntProperties.contains(subNode.getKey())) {
                result.getProperties().put(subNode.getKey(), mapper.treeToValue(subNode.getValue(), Integer.class));
            } else if (consumedProperties.contains(subNode.getKey())) {
                if (subNode.getKey().equals("position")) {
                    setPosition(result, subNode);
                } else if (subNode.getKey().equals("buttons")) {
                    result.getProperties().put(subNode.getKey(), mapper.treeToValue(subNode.getValue(), List.class));
                } else {
                    result.getProperties().put(subNode.getKey(), subNode.getValue().asText());
                }
            } else if (supportedTextProperties.contains(subNode.getKey())) {
                beanAccessor.setPropertyValue(subNode.getKey(), subNode.getValue().asText());
            } else if (supportedIntProperties.contains(subNode.getKey())) {
                beanAccessor.setPropertyValue(subNode.getKey(), subNode.getValue().asInt());
            } else {
                throw new IllegalArgumentException("Unknown property: " + subNode.getKey());
                // result.getProperties().put(subNode.getKey(),
                // subNode.getValue().asText());
            }
        }

        return result;
    }

}
