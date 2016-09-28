package net.kiberion.swampmachine.templating.mustache;

import java.io.StringReader;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import net.kiberion.swampmachine.templating.api.Template;
import net.kiberion.swampmachine.templating.api.TemplateFactory;

public class MustacheTemplateFactory implements TemplateFactory{

    private MustacheFactory mf = new DefaultMustacheFactory();
    
    @Override
    public Template produceTemplate(String templateBody) {
        Mustache mustache = mf.compile(new StringReader(templateBody), null);        
        return new MustacheTemplate(mustache);
    }
    
}
