package net.kiberion.swampmachine.templating.mustache;

import java.io.StringReader;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import net.kiberion.swampmachine.api.templating.Template;
import net.kiberion.swampmachine.api.templating.TemplateFactory;

public class MustacheTemplateFactory implements TemplateFactory{

    private final MustacheFactory mf = new DefaultMustacheFactory();
    
    @Override
    public Template produceTemplate(String templateBody) {
        Mustache mustache = mf.compile(new StringReader(templateBody), null);        
        return new MustacheTemplate(mustache);
    }
    
}
