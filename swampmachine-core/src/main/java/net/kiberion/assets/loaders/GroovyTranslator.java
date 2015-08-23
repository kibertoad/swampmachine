package net.kiberion.assets.loaders;

import com.badlogic.gdx.Gdx;

/**
 * @author: kibertoad
 */
public class GroovyTranslator {

    public String convertToken(String token) {
        String result = token;

        if (result.startsWith("stat.")) {
            result = result.replace("stat.", "agent.getStat(\"");
            result = result.concat("\")");
        }

        if (result.startsWith("diff")) {
            result = result.replace("diff", "processor.difficulty");
            //result = result.concat("\")");
        }

        result = result.concat(" ");


        //result = token;

        Gdx.app.log("debug", "Conversion result: '" + result + "'");

        return result;
    }

    public String convertString(String groovyScript) {
        String[] tokens = groovyScript.split(" ");
        StringBuilder result = new StringBuilder();

        for (String s : tokens) {
            if (s.startsWith("$")) {
                result.append(convertToken(s.replace("$", "")));
            } else {
                result.append(s);
            }
        }


        return result.toString();
    }
}
