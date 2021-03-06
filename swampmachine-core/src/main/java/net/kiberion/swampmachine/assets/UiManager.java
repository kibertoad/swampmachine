package net.kiberion.swampmachine.assets;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.styling.StyleFactory;

/**
 * Ui manager. Currently just initializes skins; Note that this implementation
 * will be replaced with using external one. This class is meant to be used
 * statically, because it stores information that is used by separate UI
 * elements, which wouldn't participate in injection.
 * 
 * @author caryoscelus
 */

//ToDo Very old code that should definitely be reviewed and rewritten
@Deprecated
public class UiManager {

    public static final String BLANK_IMAGE = "BLANK";
    public StyleFactory fontGenerator = new StyleFactory();

    private static UiManager _instance;

    public static UiManager instance() {
        if (_instance == null) {
            _instance = new UiManager();
        }
        return _instance;
    }

    @Getter
    @Setter
    protected Skin defaultSkin;

    @Getter
    @Setter
    protected Skin transparentSkin;

    protected TextureAtlas defaultAtlas;

    /**
     * Load skin from json file.
     * 
     * @param f
     *            FileHandle containing json skin
     */
    public void loadSkin(FileHandle f) {
        Skin skin = new Skin(f);
        defaultSkin = skin;
    }

    public void loadTransparentSkin(FileHandle f) {
        Skin skin = new Skin(f);
        transparentSkin = skin;
    }

    public void setAtlas(TextureAtlas setAtlas) {
        defaultAtlas = setAtlas;
        setFiltering();
    }

    public void setFiltering() {
        // for (Texture texture: defaultAtlas.getTextures()) {
        // texture.setFilter(Texture.TextureFilter.Linear,
        // Texture.TextureFilter.Linear);
        // }
    }

    public TextureAtlas atlas() {
        return defaultAtlas;
    }

    public TextureAtlas.AtlasRegion getImage(String id) {
        if (!id.equals(BLANK_IMAGE)) {
            return atlas().findRegion(id);
        } else {
            return null;
        }
    }

    public static void reset() {
        _instance = null;
    }

}
