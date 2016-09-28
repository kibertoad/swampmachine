package net.kiberion.swampmachine.subscription;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.kiberion.swampmachine.api.templating.Template;

public class ObservableTemplatedString extends AbstractObservable<Map<String, Object>, String>{

    private String value = StringUtils.EMPTY;
    private final Template template;
    
    public ObservableTemplatedString(Template template) {
        this.template = template;
    }
    
    @Override
    public String getValue() {
        return value;
    }

    @Override
    protected void setValueInternal(Map<String, Object> newValues) {
        value = template.eval(newValues);
    }
    
}
