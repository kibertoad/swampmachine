package net.kiberion.swampmachine.utils;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import net.kiberion.swampmachine.utils.common.StopWatch;
import net.kiberion.swampmachine.utils.compression.api.Archiver;

public abstract class AbstractArchiverTest {

    private static final Logger log = LogManager.getLogger();

    private Resource testDir = new ClassPathResource("archiving");

    protected abstract Archiver getArchiver();

    protected abstract int expectedArchivedSize();

    @Test
    public void archiverTest() throws Exception {
        log.info("Archiver: " + getArchiver().toString());
        Path testDirPath = Paths.get(testDir.getURI());
        assertEquals(4, Files.walk(testDirPath).collect(Collectors.counting()).longValue());

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        StopWatch compressionStopWatch = new StopWatch();
        compressionStopWatch.start();
        Archiver arc = getArchiver();
        arc.archive(testDirPath.toFile(), bos);
        compressionStopWatch.endAndLog("Compression");

        byte[] compressedData = bos.toByteArray();
        assertEquals(expectedArchivedSize(), compressedData.length);

        InputStream bis = new ByteArrayInputStream(compressedData);
        File target = Files.createTempDirectory("arc").toFile();
        try {
            StopWatch decompressionStopWatch = new StopWatch();
            arc.unarchive(bis, target);
            decompressionStopWatch.endAndLog("Decompression");

            // one more than in the beginning, because there is also temp
            // directory itself which holds original root as child directory
            assertEquals(5, Files.walk(target.toPath()).collect(Collectors.counting()).longValue());
        } finally {
            FileUtils.deleteQuietly(target);
        }
    }

}
