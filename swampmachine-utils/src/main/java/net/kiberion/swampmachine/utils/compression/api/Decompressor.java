package net.kiberion.swampmachine.utils.compression.api;

import java.io.InputStream;
import java.io.OutputStream;

public interface Decompressor {

    public void decompress (InputStream source, OutputStream target);
    
}
