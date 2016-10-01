package net.kiberion.swampmachine.templating;

import net.kiberion.swampmachine.api.templating.TemplateFactory;
import net.kiberion.swampmachine.templating.pebble.PebbleTemplateFactory;

public class PebbleTemplateTest extends AbstractTemplateTest{

    @Override
    public TemplateFactory initTemplateFactory() {
        return new PebbleTemplateFactory();
    }
    
    @Override
    public String getHelloTemplate() {
        return "Hello, {{name}}!";
    }
    
}
