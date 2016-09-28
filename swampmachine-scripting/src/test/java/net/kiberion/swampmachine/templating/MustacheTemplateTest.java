package net.kiberion.swampmachine.templating;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.kiberion.swampmachine.templating.api.Template;
import net.kiberion.swampmachine.templating.api.TemplateFactory;
import net.kiberion.swampmachine.templating.mustache.MustacheTemplateFactory;

public class MustacheTemplateTest {

    @Test
    public void testMustacheTemplate() throws Exception {
        TemplateFactory templateFactory = new MustacheTemplateFactory();
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "Mustache");
        Template template = templateFactory.produceTemplate("Hello, {{name}}!");

        assertEquals("Hello, Mustache!", template.eval(variables));
    }

}
