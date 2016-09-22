package net.kiberion.swampmachine.utils;

import net.kiberion.utils.compression.api.Codec;
import net.kiberion.utils.compression.impl.SnappyCodec;

public class SnappyCompressorTest extends AbstractCompressorTest{

    @Override
    protected Codec getCodec() {
        return new SnappyCodec();
    }
    
}
