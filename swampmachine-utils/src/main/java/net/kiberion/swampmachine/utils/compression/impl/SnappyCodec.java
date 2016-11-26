package net.kiberion.swampmachine.utils.compression.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.xerial.snappy.SnappyInputStream;
import org.xerial.snappy.SnappyOutputStream;

import net.kiberion.swampmachine.utils.compression.api.Codec;

public class SnappyCodec implements Codec {

    @Override
    public void compress(InputStream source, OutputStream target) {
        Validate.notNull(source);
        Validate.notNull(target);
        try (OutputStream os = new SnappyOutputStream(target)) {
            IOUtils.copy(source, os);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(source);
        }
    }

    @Override
    public void decompress(InputStream source, OutputStream target) {
        Validate.notNull(source);
        Validate.notNull(target);
        try (InputStream is = new SnappyInputStream(source)) {
            IOUtils.copy(is, target);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(target);
        }
    }

}
