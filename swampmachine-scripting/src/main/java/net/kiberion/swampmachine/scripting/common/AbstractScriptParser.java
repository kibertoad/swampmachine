package net.kiberion.swampmachine.scripting.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.swampmachine.api.scripting.ScriptParser;
import net.kiberion.swampmachine.api.scripting.SwampScript;
import net.kiberion.swampmachine.utils.common.FilePathUtils;

public abstract class AbstractScriptParser implements ScriptParser{

    private static final Logger log = LogManager.getLogger();

    protected abstract Set<String> getScriptExtensions();

    @Override
    public List<SwampScript> parseScriptsFromPath(Path directory) {
        List<SwampScript> compiledScrips = new ArrayList<>();

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
