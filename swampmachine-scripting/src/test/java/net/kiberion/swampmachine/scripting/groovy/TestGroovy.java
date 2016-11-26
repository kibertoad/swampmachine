package net.kiberion.swampmachine.scripting.groovy;

import java.nio.file.Path;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptTest;
import net.kiberion.swampmachine.scripting.groovy.GroovyEntityFactory;
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
