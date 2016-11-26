package net.kiberion.swampmachine.utils.compression.api;

import java.io.InputStream;
import java.io.OutputStream;

public interface Compressor {

    public void compress (InputStream source, OutputStream target);
    
    
}
