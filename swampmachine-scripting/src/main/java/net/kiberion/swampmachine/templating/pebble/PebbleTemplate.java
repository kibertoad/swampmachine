package net.kiberion.swampmachine.templating.pebble;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.mitchellbosecke.pebble.error.PebbleException;

import net.kiberion.swampmachine.api.templating.Template;

public class PebbleTemplate implements Template {

    private final com.mitchellbosecke.pebble.template.PebbleTemplate template;

    public PebbleTemplate(com.mitchellbosecke.pebble.template.PebbleTemplate template) {
        this.template = template;
    }

    @Override
    public String eval(Map<String, Object> variableMap) {
        try (Writer writer = new StringWriter()) {
            template.evaluate(writer, variableMap);
            writer.flush();
            return writer.toString();
        } catch (IOException | PebbleException e) {
            throw new IllegalStateException(e);
        }

    }

}
