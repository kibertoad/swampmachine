package net.kiberion.blueprint.common.loaders;

import java.nio.file.Path;

import com.google.inject.Inject;

import lombok.Getter;
import net.kiberion.assets.readers.ReaderHelper;

public class AbstractLoader {

    @Inject
    @Getter
    private final ReaderHelper readerHelper;

    public AbstractLoader() {
        readerHelper = new ReaderHelper();
    }

    protected Path getPathToAssets() {
        return readerHelper.getPathToAssets();
    }

    protected boolean fileExists(String directoryName) {
        return readerHelper.fileExists(directoryName);
    }

}
