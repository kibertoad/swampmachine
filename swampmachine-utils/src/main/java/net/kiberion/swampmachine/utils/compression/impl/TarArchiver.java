package net.kiberion.swampmachine.utils.compression.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import net.kiberion.swampmachine.utils.compression.api.Archiver;

public class TarArchiver implements Archiver {

    @Override
    public void archive(File source, OutputStream target) {
        try (TarArchiveOutputStream tOut = new TarArchiveOutputStream(target)) {
            addFileToTar(tOut, source, "");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void addFileToTar(TarArchiveOutputStream tOut, File f, String base) throws IOException {
        String entryName = base + f.getName();
        TarArchiveEntry tarEntry = new TarArchiveEntry(f, entryName);
        tOut.putArchiveEntry(tarEntry);

        if (f.isFile()) {
            try (InputStream is = new FileInputStream(f)) {
                IOUtils.copy(is, tOut);
                tOut.closeArchiveEntry();
            }
        } else {
            tOut.closeArchiveEntry();
            File[] children = f.listFiles();
            if (children != null) {
                for (File child : children) {
                    addFileToTar(tOut, child, entryName + "/");
                }
            }
        }
    }

    @Override
    public void unarchive(InputStream source, File target) {
        try (TarArchiveInputStream tIn = new TarArchiveInputStream(source)) {

            TarArchiveEntry entry = tIn.getNextTarEntry();
            while (entry != null) {
                if (entry.isDirectory()) {
                    entry = tIn.getNextTarEntry();
                    continue;
                }
                File curfile = new File(target, entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                try (OutputStream out = new FileOutputStream(curfile)) {
                    IOUtils.copy(tIn, out);

                    entry = tIn.getNextTarEntry();
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
