package net.kiberion.mvc;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;

import lombok.Getter;
import lombok.Setter;

public class StateView extends Container<Actor> implements PostInjectionProcessed{

    @Getter
    @Setter
    private Stage stage;
    
    public void show() {
    	setVisible(true);
    }
    
    public void hide() {
    	setVisible(false);
    }

	@Override
	public void postInjection() {
	}
	
	
	
    @Override
    public void act(float delta) {
    	/*
        for (PyramideAnimation animation : animationList) {
            animation.act(delta);

            if (animation.isCompleted() && (!animation.isLooped())) {
                animationGarbageList.add(animation);
            }
        }

        while (!animationGarbageList.isEmpty()) {
            animationList.remove(animationGarbageList.poll());
        }

        while (!animationAdditionList.isEmpty()) {
            animationList.remove(animationAdditionList.poll());
        }
        */

    }

    /*
    public void addAnimation(PyramideAnimation animation) {
        animationList.add(animation);
    }

    public void addAnimationSafe(PyramideAnimation animation) {
        animationAdditionList.add(animation);
    }
    */	
}
