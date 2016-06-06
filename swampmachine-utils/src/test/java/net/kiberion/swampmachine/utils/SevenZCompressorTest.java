package net.kiberion.swampmachine.utils;

import net.kiberion.utils.compression.api.Codec;
import net.kiberion.utils.compression.impl.SevenZCodec;

public class SevenZCompressorTest extends AbstractCompressorTest{

    @Override
    protected int expectedCompressedSize() {
        return 645;
    }
    
    @Override
    protected Codec getCodec() {
        return new SevenZCodec();
    }
    
}
