package net.kiberion.swampmachine.templating;

import net.kiberion.swampmachine.api.templating.TemplateFactory;
import net.kiberion.swampmachine.templating.mustache.MustacheTemplateFactory;

public class MustacheTemplateTest extends AbstractTemplateTest{

    @Override
    public TemplateFactory initTemplateFactory() {
        return new MustacheTemplateFactory();
    }
    
    @Override
    public String getHelloTemplate() {
        return "Hello, {{name}}!";
    }
    
}
