package net.kiberion.utils.compression.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.utils.IOUtils;

import net.kiberion.utils.compression.api.Codec;

public class SevenZCodec implements Codec {

    @Override
    public void compress(InputStream source, OutputStream target) {
        try {
            File targetFile = File.createTempFile("7ztrgt", "tmp");
            File sourceFile = File.createTempFile("7zsrc", "tmp");

            try (FileOutputStream fos = new FileOutputStream(sourceFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                IOUtils.copy(source, bos);
            }

            try (SevenZOutputFile sevenZOutput = new SevenZOutputFile(targetFile)) {
                SevenZArchiveEntry entry = sevenZOutput.createArchiveEntry(sourceFile, "entry");
                sevenZOutput.putArchiveEntry(entry);

                sevenZOutput.write(Files.readAllBytes(sourceFile.toPath()));
                sevenZOutput.closeArchiveEntry();
            }

            try (InputStream is = new BufferedInputStream(new FileInputStream(targetFile))) {
                IOUtils.copy(is, target);
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(source);
        }

    }

    @Override
    public void decompress(InputStream source, OutputStream target) {
        try {
            File sourceFile = File.createTempFile("7zsrc", "tmp");

            try (FileOutputStream fos = new FileOutputStream(sourceFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                IOUtils.copy(source, bos);
            }

            SevenZFile sevenZFile = new SevenZFile(sourceFile);
            try {
                SevenZArchiveEntry entry = sevenZFile.getNextEntry();
                byte[] contents = new byte[(int) entry.getSize()];
                int off = 0;
                while ((off < contents.length)) {
                    int bytesRead = sevenZFile.read(contents, off, contents.length - off);
                    assert (bytesRead >= 0);
                    off += bytesRead;
                }
                target.write(contents);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            } finally {
                IOUtils.closeQuietly(sevenZFile);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(source);
            IOUtils.closeQuietly(target);
        }
    }

}
