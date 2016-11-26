package net.kiberion.swampmachine.utils;

import net.kiberion.swampmachine.utils.compression.api.Codec;
import net.kiberion.swampmachine.utils.compression.impl.SnappyCodec;

public class SnappyCompressorTest extends AbstractCompressorTest{

    @Override
    protected Codec getCodec() {
        return new SnappyCodec();
    }
    
}
