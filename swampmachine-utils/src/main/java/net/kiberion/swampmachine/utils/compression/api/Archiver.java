package net.kiberion.swampmachine.utils.compression.api;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Archiver {

    public void archive (File source, OutputStream target);
    
    public void unarchive (InputStream source, File target);
    
}
