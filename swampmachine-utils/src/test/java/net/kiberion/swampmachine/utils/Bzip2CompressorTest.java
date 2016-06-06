package net.kiberion.swampmachine.utils;

import net.kiberion.utils.compression.api.Codec;
import net.kiberion.utils.compression.impl.BZip2Codec;

public class Bzip2CompressorTest extends AbstractCompressorTest{

    @Override
    protected int expectedCompressedSize() {
        return 626;
    }
    
    @Override
    protected Codec getCodec() {
        return new BZip2Codec();
    }
    
}
