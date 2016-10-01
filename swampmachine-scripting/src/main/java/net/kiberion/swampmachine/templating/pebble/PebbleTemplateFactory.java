package net.kiberion.swampmachine.templating.pebble;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.StringLoader;

import net.kiberion.swampmachine.api.templating.Template;
import net.kiberion.swampmachine.api.templating.TemplateFactory;

/**
 * Note - Pebble is about 3 times slower on simple use-cases than Mustache, so
 * unless you really need advanced Pebble features - use Mustache
 * 
 * @author kibertoad
 *
 */
public class PebbleTemplateFactory implements TemplateFactory {

    private final PebbleEngine pebble = new PebbleEngine.Builder().loader(new StringLoader()).build();

    @Override
    public Template produceTemplate(String templateBody) {
        try {
            return new PebbleTemplate(pebble.getTemplate(templateBody));
        } catch (PebbleException e) {
            throw new IllegalStateException(e);
        }

    }

}
