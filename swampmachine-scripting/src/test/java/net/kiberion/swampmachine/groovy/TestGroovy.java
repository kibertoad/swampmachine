package net.kiberion.swampmachine.groovy;

import java.nio.file.Path;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptTest;
import net.kiberion.swampmachine.utils.common.FilePathUtils;

public class TestGroovy extends AbstractScriptTest {

    private final GroovyEntityFactory factory = new GroovyEntityFactory();

    @Override
    protected ScriptEntityFactory getEntityFactory() {
        return factory;
    }

    @Override
    protected Path getPathToTestResourcres() {
        return FilePathUtils.getResourceRootPath(TestGroovy.class, "test.groovy");
    }


}
