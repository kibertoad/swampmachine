package net.kiberion.swampmachine.gui.elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class CompositionElementDeserializer extends JsonDeserializer<CompositionElement>{

    @Override
    public CompositionElement deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        CompositionElement result = new CompositionElement();
        
        Iterator<Entry<String, JsonNode>> s = node.fields();
        while (s.hasNext()) {
            Entry<String, JsonNode> entry = s.next();
        }

        result.setId(node.get("id").asText());
        return result;
    }

}
