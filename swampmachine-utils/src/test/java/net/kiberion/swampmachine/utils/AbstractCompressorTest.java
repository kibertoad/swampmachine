package net.kiberion.swampmachine.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import net.kiberion.swampmachine.utils.common.StopWatch;
import net.kiberion.swampmachine.utils.compression.api.Codec;
import net.kiberion.swampmachine.utils.compression.api.Compressor;
import net.kiberion.swampmachine.utils.compression.api.Decompressor;

public abstract class AbstractCompressorTest {

    private static final Logger log = LogManager.getLogger();
    
    private Resource testFile = new ClassPathResource("buildings.yml");

    protected abstract Codec getCodec();
    
    @Test
    public void codecTest() throws Exception {
        log.info("Codec: "+getCodec().toString());
        
        long fileLength = testFile.contentLength();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        StopWatch compressionStopWatch = new StopWatch();
        compressionStopWatch.start();
        Compressor compressor = getCodec();
        try (InputStream is = testFile.getInputStream()) {
            compressor.compress(is, bos);
        }
        compressionStopWatch.endAndLog("Compression");

        byte[] compressedData = bos.toByteArray();
        assertTrue("Compressed file size should be less than original", compressedData.length < fileLength);

        InputStream bis = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();

        StopWatch decompressionStopWatch = new StopWatch();
        decompressionStopWatch.start();
        Decompressor decompressor = getCodec();
        decompressor.decompress(bis, bos2);
        decompressionStopWatch.endAndLog("Decompression");
        
        byte[] decompressedData = bos2.toByteArray();
        assertEquals(decompressedData.length, fileLength);

        try (InputStream is = testFile.getInputStream()) {
            assertTrue(IOUtils.contentEquals(is, new ByteArrayInputStream(bos2.toByteArray())));
        }
    }
    
}
