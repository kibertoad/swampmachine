package net.kiberion.swampmachine.utils;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import net.kiberion.utils.compression.api.Compressor;
import net.kiberion.utils.compression.api.Decompressor;
import net.kiberion.utils.compression.impl.SnappyCodec;

public class CompressorTest {

    private Resource testFile = new ClassPathResource("buildings.yml");

    @Test
    public void codecTest() throws Exception {
        assertEquals(2927, testFile.contentLength());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        Compressor compressor = new SnappyCodec();
        
        try (InputStream is = testFile.getInputStream()) {
            compressor.compress(is, bos);
        }

        byte[] compressedData = bos.toByteArray();
        assertEquals(827, compressedData.length);

        InputStream bis = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        Decompressor decompressor = new SnappyCodec();
        decompressor.decompress(bis, bos2);
        
        byte[] decompressedData = bos2.toByteArray();
        assertEquals(2927, decompressedData.length);

        try (InputStream is = testFile.getInputStream()) {
            assertTrue(IOUtils.contentEquals(is, new ByteArrayInputStream(bos2.toByteArray())));
        }
    }
}
