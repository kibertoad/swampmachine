package net.kiberion.styling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * @author kibertoad
 */
public class ColourGenerator {

    private static ColourGenerator _instance;

    public static ColourGenerator instance (){
        if (_instance == null) {
            _instance = new ColourGenerator();
        }

        return _instance;
    }

    public Color getColour(String colorCode) {


        int r = 0;
        int g = 0;
        int b = 0;
        int a = 255;

        //Log.info("COLORRRR: "+colorCode);

        if (colorCode.equals("black")) {
            r = 0;
            g = 0;
            b = 0;

        } else

        if (colorCode.equals("white") || (colorCode == "default")) {
            r = 255;
            g = 255;
            b = 255;
            //return new TransientColour(255, 150, 50, 50);
            //return new TransientColour(255, 255, 0, 56);
        } else if (colorCode.equals("red")) {
            //return new TransientColour(255, 153, 34, 34);
            r = 153;
            g = 34;
            b = 34;
        } else if (colorCode.equals("blue")) {
            r = 0;
            g = 0;
            b = 205;
            //return new TransientColour(255, 0, 0, 205);
        } else if (colorCode.equals("bluegray")) {
            r = 119;
            g = 136;
            b = 153;
        } else if (colorCode.equals("green")) {
            r = 153;
            g = 204;
            b = 153;
        } else if (colorCode.equals("gray")) {
            //return new TransientColour(255, 119, 136, 153);
            r = 88;
            g = 88;
            b = 88;
        } else if (colorCode.equals("transientgray")) {
            //return new TransientColour(255, 186, 186, 186);
            r = 186;
            g = 186;
            b = 186;
        } else if (colorCode.equals("orange")) {
            r = 255;
            g = 165;
            b = 0;
        } else {
            Gdx.app.error("error", "Unknown colour: "+colorCode);
            return null;
        }


        float aF = a / 255.0f;
        float rF = r / 255.0f;
        float gF = g / 255.0f;
        float bF = b / 255.0f;

        Color color = new Color(rF, gF, bF, aF);

        //Gdx.app.log("debug", "Generated colour: "+color.r+"/"+color.g+"/"+color.b);

        return color;
    }
}
