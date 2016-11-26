package net.kiberion.swampmachine.api.scripting;

import java.io.Reader;
import java.nio.file.Path;
import java.util.List;

public interface ScriptParser {

    public SwampScript parseScript(String script);

    public SwampScript parseScript(Reader script);

    public List<SwampScript> parseScriptsFromPath(Path directory);
    
}
