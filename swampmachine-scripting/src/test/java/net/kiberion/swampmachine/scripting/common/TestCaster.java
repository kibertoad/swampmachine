package net.kiberion.swampmachine.scripting.common;

import org.apache.commons.lang3.mutable.MutableInt;

public class TestCaster {

    private int saidMoo;
    
    private MutableInt mutableNumber = new MutableInt();
    
    public void sayMoo () {
        System.out.println("moo");
        saidMoo++;
    }
    
    public MutableInt getMutableNumber() {
        return mutableNumber;
    }
    
    public int getSaidMoo() {
        return saidMoo;
    }
}
