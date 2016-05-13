package net.kiberion.tiled;

import org.springframework.test.context.ContextConfiguration;

import net.kiberion.spring.ContextBasedTest;
import net.kiberion.tiled.spring.TiledTestConfiguration;

@ContextConfiguration(classes = {TiledTestConfiguration.class})
public abstract class MapContextBasedTest extends ContextBasedTest{

}
