package net.kiberion.swampmachine.utils;

import net.kiberion.utils.compression.api.Archiver;
import net.kiberion.utils.compression.impl.TarArchiver;

public class TarArchiverTest extends AbstractArchiverTest{

    @Override
    protected Archiver getArchiver() {
        return new TarArchiver();
    }

    @Override
    protected int expectedArchivedSize() {
        return 10240;
    }
    
}
