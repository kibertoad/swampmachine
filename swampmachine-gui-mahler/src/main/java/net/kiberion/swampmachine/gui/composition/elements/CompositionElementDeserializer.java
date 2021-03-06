package net.kiberion.swampmachine.gui.composition.elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
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

import lombok.Setter;
import net.kiberion.swampmachine.api.templating.Template;
import net.kiberion.swampmachine.api.templating.TemplateFactory;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.gui.templates.ElementTemplate;
import net.kiberion.swampmachine.gui.templates.ElementTemplateRegistry;
import net.kiberion.swampmachine.utils.SetUtils;
import net.kiberion.swampmachine.utils.common.InlineGList;

public class CompositionElementDeserializer extends JsonDeserializer<CompositionElement> {

    private static final ObjectMapper mapper = new ObjectMapper();

    // Should be set by template registry itself after it is initialized.
    @Setter
    private static ElementTemplateRegistry templateRegistry;

    @Setter
    private static TemplateFactory templateFactory;

    private static final Logger log = LogManager.getLogger();

    // will be set via setters
    private static final List<String> supportedTextProperties = new InlineGList<>("id", "type");
    private static final List<String> supportedIntProperties = new InlineGList<>("zIndex");

    // processed in a special way
    private static final List<String> consumedProperties = new InlineGList<>("position", "image", "text", "labelValue",
            "labelText", "buttonSource", "labelSource", "selectedIcon", "unselectedIcon", "onClickScript");
    private static final List<String> consumedMapProperties = new InlineGList<>("onClickEvent", "plus", "minus",
            "button", "onClickSound");
    private static final List<String> consumedIntProperties = new InlineGList<>();
    private static final List<String> consumedListProperties = new InlineGList<>("buttons");

    private static final Set<String> mandatoryTextProperties = SetUtils.buildSet("id", "type");

    private void setPosition(CompositionElement result, JsonNode sourceNode) {
        if (sourceNode.size() == 2) {
            result.setPosition(new CommonPosition(sourceNode.get(0).asInt(), sourceNode.get(1).asInt()));
        }
    }

    private Object getValueOrOverride(Object value, Map<String, Object> valueMap) {
        if (value instanceof String) {

            if (MapUtils.isEmpty(valueMap) || templateFactory == null) {
                return value;
            }
            Template valueTemplate = templateFactory.produceTemplate((String) value);
            return valueTemplate.eval(valueMap);
        }

        return value;
    }

    private void processConsumedProperty(CompositionElement result, Entry<String, JsonNode> subNode,
            Map<String, Object> valueMap) throws JsonProcessingException {
        if (subNode.getKey().equals("position")) {
            setPosition(result, subNode.getValue());
        } else if (subNode.getKey().equals("buttons")) {
            result.getProperties().put(subNode.getKey(), mapper.treeToValue(subNode.getValue(), List.class));
        } else {
            result.getProperties().put(subNode.getKey(), getValueOrOverride(subNode.getValue().asText(), valueMap));
        }
    }

    @SuppressWarnings("unchecked")
    private void processMapProperty(CompositionElement result, Entry<String, JsonNode> subNode,
            Map<String, Object> valueMap) throws JsonProcessingException {
        Map<String, Object> deserializedMap = mapper.treeToValue(subNode.getValue(), Map.class);
        for (Entry<String, Object> entry : deserializedMap.entrySet()) {
            deserializedMap.put(entry.getKey(), getValueOrOverride(entry.getValue(), valueMap));
        }
        result.getProperties().put(subNode.getKey(), deserializedMap);
    }

    private CompositionElement deserializeNonTemplate(JsonNode node, Map<String, Object> valueMap)
            throws JsonProcessingException {
        CompositionElement result = new CompositionElement();
        PropertyAccessor beanAccessor = PropertyAccessorFactory.forDirectFieldAccess(result);

        for (Iterator<Entry<String, JsonNode>> iter = node.fields(); iter.hasNext();) {
            Entry<String, JsonNode> subNode = iter.next();

            if (consumedListProperties.contains(subNode.getKey())) {
                result.getProperties().put(subNode.getKey(), mapper.treeToValue(subNode.getValue(), List.class));
            } else if (consumedMapProperties.contains(subNode.getKey())) {
                processMapProperty(result, subNode, valueMap);
            } else if (consumedIntProperties.contains(subNode.getKey())) {
                result.getProperties().put(subNode.getKey(), mapper.treeToValue(subNode.getValue(), Integer.class));
            } else if (consumedProperties.contains(subNode.getKey())) {
                processConsumedProperty(result, subNode, valueMap);
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

    @SuppressWarnings("unchecked")
    private CompositionElement deserializeTemplate(JsonNode node) throws JsonProcessingException {
        String templateId = node.get("templateId").asText();
        Validate.notBlank(templateId);
        Validate.notNull(templateRegistry);
        ElementTemplate template = templateRegistry.getElementTemplates().get(templateId);
        Validate.notNull(template, "Unknown template: " + templateId + ". Known templates: "
                + templateRegistry.getElementTemplates().keySet());
        JsonNode templateNode = template.getTemplate();
        Map<String, Object> params = mapper.treeToValue(node.get("parameters"), Map.class);

        CompositionElement result = deserializeNonTemplate(templateNode, params);
        String id = node.get("id").asText();
        Validate.notBlank(id);
        result.setId(id);

        // ToDo temporary solution until LayoutManager is in place
        JsonNode position = node.get("position");
        Validate.notNull(position);
        setPosition(result, position);
        return result;
    }

    @Override
    public CompositionElement deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        if (!StringUtils.equals(type, "template")) {
            return deserializeNonTemplate(node, null);
        } else {
            log.info("is template");
            return deserializeTemplate(node);
        }

    }

}
