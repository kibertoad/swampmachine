package net.kiberion.swampmachine.scripting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.utils.FilePathUtils;

public abstract class AbstractScriptParser<T extends SwampScript<? extends SwampBinding, ? extends SwampScriptInvokationResult>> {

    private static final Logger log = LogManager.getLogger();

    protected abstract Set<String> getScriptExtensions();

    protected abstract T parseScript(String script);

    protected abstract T parseScript(Reader script);

    public List<T> parseScriptsFromPath(Path directory) {
        List<T> compiledScrips = new ArrayList<>();

        try {
            List<Path> scriptPaths = FilePathUtils.getListOfFilesByExtension(directory, getScriptExtensions());

            for (Path path : scriptPaths) {
                try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                    compiledScrips.add(parseScript(reader));
                } catch (IOException e) {
                    log.error("IO Exception: ", e);
                    throw new IllegalStateException(e);
                }
            }
        } catch (IOException e) {
            log.error("IO Exception: ", e);
        }

        return compiledScrips;
    }

}
