package net.kiberion.swampmachine.subscription;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import lombok.Getter;

/**
 * Used to pass a settable reference to String (mostly for automatic sync between view and model values)
 * 
 * @author kibertoad
 *
 */
public class ObservableMutableString extends AbstractObservable<String> {

    @Getter
    private String value;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObservableMutableString) {
            return ObjectUtils.nullSafeEquals(value, ((ObservableMutableString) obj).getValue());
        } else if (obj instanceof String) {
            return StringUtils.equals(value, (String) obj);
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        if (value != null) {
            return value.hashCode();
        }
        return super.hashCode();
    }

    @Override
    protected void setValueInternal(String newValue) {
        this.value = newValue;        
    }

}
