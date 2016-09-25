package net.kiberion.swampmachine.subscription;

import org.apache.commons.lang3.StringUtils;

public class ObservableString extends AbstractObservable<String>{

    private String value = StringUtils.EMPTY;
    
    @Override
    public String getValue() {
        return value;
    }

    @Override
    protected void setValueInternal(String newValue) {
        value = newValue;
    }
    
}
