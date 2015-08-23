package net.kiberion.assets;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.entities.modelinfo.CreatureInfoList;

public class ModelInfoProvider {

	@Getter
	@Setter
    private CreatureInfoList creatures;

	/*
    protected void loadCreatureModelInfo() {
        //Gdx.app.log("debug", "Start loading creature info");
        //Objects.requireNonNull(pathToAssets);
        
        if (fileReader.fileExists((getPathToAssets().resolve("model-creature/")))) {
        
        CreatureModelInfoLoader loader = new CreatureModelInfoLoader(pathToAssets+"/model-creature/*", getItems(), getActions());
        setCreatures(loader.load());
        //Gdx.app.log("debug", "Loaded creature info");
        }
    }
    */	
	
	
}
