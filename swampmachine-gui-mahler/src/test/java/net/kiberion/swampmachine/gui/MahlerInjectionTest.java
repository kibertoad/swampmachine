package net.kiberion.swampmachine.gui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.gui.composer.CompositionLoader;
import net.kiberion.swampmachine.gui.spring.CompositionInjector;
import net.kiberion.swampmachine.gui.spring.MahlerContextBasedTest;
import net.kiberion.swampmachine.gui.spring.TestState;

public class MahlerInjectionTest extends MahlerContextBasedTest{

    @Autowired
    private CompositionLoader loader;
    
    @Autowired
    private CompositionInjector injector;
    
    @Autowired
    private TestState state;
    
    @Test
    public void testStuff () {
        loader.load();
        injector.inject();

        assertEquals ("dummy", state.getElement());
    }
    
}
