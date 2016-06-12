package net.kiberion.preflight;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Packs separate images into Texture Atlases
 * 
 * @author kibertoad
 *
 */
public class Preflight {

    private static final Logger log = LogManager.getLogger();

    /**
     * Generate atlases from images. Should be used only when images are updated
     */
    private static void regenerateAtlases(String dataDir) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;

        try {
            log.info("Packing files at: " + Paths.get(dataDir).toAbsolutePath().toString());
            TexturePacker.process(settings, dataDir + "/img", dataDir + "/imgpacked", "packed");
        } catch (RuntimeException e) {
            log.error("Error while generating atlas: ", e);
            throw e;
        }
    }

    /**
     * Run preflight prep, using the given data directory.
     * 
     * @param dataDir
     * @throws IOException
     */
    public static void run(String dataDir) throws IOException {
        regenerateAtlases(dataDir);
    }

    public static void main(String[] args) throws IOException {
        // run("data");
        run("src/main/resources");
        System.out.println("Done.");
    }
}
