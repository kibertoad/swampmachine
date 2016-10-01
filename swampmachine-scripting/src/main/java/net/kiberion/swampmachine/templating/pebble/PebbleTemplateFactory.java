package net.kiberion.swampmachine.templating.pebble;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.StringLoader;

import net.kiberion.swampmachine.api.templating.Template;
import net.kiberion.swampmachine.api.templating.TemplateFactory;

public class PebbleTemplateFactory implements TemplateFactory{

    private final PebbleEngine pebble = new PebbleEngine.Builder().loader(new StringLoader()).build();
    
    @Override
    public Template produceTemplate(String templateBody) {
        try {
            return new PebbleTemplate (pebble.getTemplate(templateBody));
        } catch (PebbleException e) {
            throw new IllegalStateException (e);
        }
        
    }

}
