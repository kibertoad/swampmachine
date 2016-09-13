package net.kiberion.swampmachine.gui.composer;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.Validate;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CompositionLoader {

    public CompositionTree load (Resource source) {
        Validate.notNull(source);
        ObjectMapper mapper = new ObjectMapper();
        
        try (InputStream is = source.getInputStream()){
            return mapper.readValue(is, CompositionTree.class);
        } catch (IOException e) {
            throw new IllegalStateException (e);
        }
    }
    
}
