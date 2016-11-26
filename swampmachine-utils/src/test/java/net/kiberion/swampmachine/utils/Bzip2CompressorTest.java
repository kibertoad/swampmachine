package net.kiberion.swampmachine.utils;

import net.kiberion.swampmachine.utils.compression.api.Codec;
import net.kiberion.swampmachine.utils.compression.impl.BZip2Codec;

public class Bzip2CompressorTest extends AbstractCompressorTest{

    @Override
    protected Codec getCodec() {
        return new BZip2Codec();
    }
    
}
