package net.kiberion.swampmachine.styling;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;

import lombok.Getter;
import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.UiManager;

/**
 * @author kibertoad
 */

//ToDo Very old code, probably should be rewritten
@Deprecated
public class StyleFactory {

    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private GameConfig gameConfig;

    private final Map<String, Label.LabelStyle> labelStyles = new HashMap<>();
    private final Map<String, TextButton.TextButtonStyle> buttonStyles = new HashMap<>();
    private final Map<String, Window.WindowStyle> windowStyles = new HashMap<>();

    @Getter
    private final Map<String, Map<Integer, BitmapFont>> fonts = new HashMap<>();

    private BitmapFont produceFont(String fontName, int size) {
        String path = gameConfig.getPathToResourcesAsString() + "/fonts/" + fontName + ".ttf";
        log.info ("Path to fonts: "+path);
        FileHandle fontHandle = Gdx.files.getFileHandle(path, FileType.Internal);

        
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(fontHandle);
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = size;

        BitmapFont font = fontGenerator.generateFont(params);
        fontGenerator.dispose();

        return font;
    }

    public void addLabelFontStyle(String styleName, String fontName, int size) {
        Label.LabelStyle style = new Label.LabelStyle(
                UiManager.instance().getDefaultSkin().get("default", Label.LabelStyle.class));
        style.font = getFont(fontName, size);

        labelStyles.put(styleName, style);
    }

    public void addButtonFontStyle(String styleName, String fontName, int size) {
        addButtonFontStyle(styleName, fontName, size, UiManager.instance().getDefaultSkin());
    }

    public void addButtonFontStyle(String styleName, String fontName, int size, Skin skin) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
                skin.get("default", TextButton.TextButtonStyle.class));
        style.font = getFont(fontName, size);
        buttonStyles.put(styleName, style);
    }

    public void addWindowFontStyle(String styleName, Skin skin) {
        Window.WindowStyle style = new Window.WindowStyle(skin.get("default", Window.WindowStyle.class));
        windowStyles.put(styleName, style);
    }

    public BitmapFont getFont(String fontName, int size) {
        Map<Integer, BitmapFont> fontList;

        if (!fonts.containsKey(fontName)) {
            fontList = new HashMap<>();
            fonts.put(fontName, fontList);
        } else {
            fontList = fonts.get(fontName);
        }

        if (!fontList.containsKey(size)) {
            fontList.put(size, produceFont(fontName, size));
        }

        return fontList.get(size);
    }

    public Label.LabelStyle getLabelStyle(String styleName) {
        if (!labelStyles.containsKey(styleName)) {
            log.error("No style: " + styleName);
            for (String style : labelStyles.keySet()) {
                log.error("Has style: " + style);
            }
        }

        return labelStyles.get(styleName);
    }

    public TextButton.TextButtonStyle getButtonStyle(String styleName) {
        if (!buttonStyles.containsKey(styleName)) {
            log.error("No style: " + styleName);

            for (String style : buttonStyles.keySet()) {
                log.error("Has style: " + style);
            }
        }

        return buttonStyles.get(styleName);
    }

    public void setLabelStyleColour(String styleName, Color color) {
        getLabelStyle(styleName).fontColor = color;
    }

    public void setButtonStyleColour(String styleName, Color color) {
        getButtonStyle(styleName).fontColor = color;
    }

    public WindowStyle getWindowStyle(String style) {
        return windowStyles.get(style);
    }

}
