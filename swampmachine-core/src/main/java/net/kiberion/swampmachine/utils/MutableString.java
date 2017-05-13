package net.kiberion.swampmachine.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * Used to pass a settable reference to String (mostly for automatic sync between view and model values)
 * 
 * @author kibertoad
 *
 */
public class MutableString {

    @Getter
    @Setter
    private String value;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MutableString) {
            return ObjectUtils.nullSafeEquals(value, ((MutableString) obj).getValue());
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

}
